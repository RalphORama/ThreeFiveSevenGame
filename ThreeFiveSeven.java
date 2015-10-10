package ThreeFiveSeven;
import ThreeFiveSeven.XButton;

// TODO: Implement turn system

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import javax.swing.border.*;

public class ThreeFiveSeven extends JFrame
  implements ActionListener
{
  // content pane, panel, gridbag, and checkbox are all public variables of this class,
  // this makes modifying their properties later quite easy.
  Container c = getContentPane();
  JPanel p = new JPanel();
  
  GridBagConstraints constraints = new GridBagConstraints();
  
  // create arrays of buttons so we can easily procedually add them to
  // the GridBag later
  XButton[] threeColumn = new XButton[3];
  XButton[] fiveColumn  = new XButton[5];
  XButton[] sevenColumn = new XButton[7];
  JButton   reset       = new JButton("Reset");
  JCheckBox selectMultipleBox = new JCheckBox("Select Multiple", null, false);
  JPanel    turnPanel   = new JPanel();
  JLabel    turnLabel   = new JLabel("Player 1's turn");
  
  boolean buttonRemoved = false;
  
  // private fields
  private boolean multiSelectState = false;
  
  public ThreeFiveSeven()
  {
    super("Three Five Seven game");
  }
  
  public void setup()
  {
    // set up content pane
    c.setBackground(Color.white);
    
    // create a gridbaglayout to easily organize all our buttons, etc
    p.setLayout(new GridBagLayout());
    // set gridx and gridy so the first item added is added to the top left
    constraints.gridx = 0;
    constraints.gridy = 0;
    // set the margin between elements to 3px in all directions
    constraints.insets = new Insets(3, 3, 3, 3);
    // weightx/weighty make it so the elements take up most of the window, rather than
    // clumping together in the center of the pane.
    constraints.weightx = 1;
    constraints.weighty = 1;
    
    // add all the buttons we created earlier to the gridbaglayout
    for ( int i = 0; i < 3; i++ )
    {
      // set the title to the number (will probably change, just for debugging
      threeColumn[i] = new XButton( String.valueOf(i + 1), 3 );
      // add actionlisteners so we can tell if the user's clicked a button
      threeColumn[i].addActionListener(this);
      // make it so there's no dotted border around the button when it's clicked
      threeColumn[i].setFocusPainted(false);
      // make it so the button isn't default capable, isn't auto selected
      threeColumn[i].setDefaultCapable(false);
      
      // add to the left column
      constraints.gridx = 0;
      // add to each row, but pad so all buttons are aligned to the bottom of the grid,
      // rather than the top
      constraints.gridy = 4 + i;
      constraints.gridwidth  = 1;
      constraints.gridheight = 1;
      // fill all available area
      constraints.fill = GridBagConstraints.BOTH;
      p.add(threeColumn[i], constraints);
      threeColumn[i].setPreferredSize(new Dimension(100, 50));
    }
    for ( int i = 0; i < 5; i++ )
    {
      fiveColumn[i] = new XButton( String.valueOf(i + 1), 5 );
      fiveColumn[i].addActionListener(this);
      fiveColumn[i].setFocusPainted(false);
      fiveColumn[i].setDefaultCapable(false);
      
      constraints.gridx = 1;
      constraints.gridy = 2 + i;
      constraints.fill = GridBagConstraints.BOTH;
      p.add(fiveColumn[i], constraints);
      fiveColumn[i].setPreferredSize(new Dimension(100, 50));
    }
    for ( int i = 0; i < 7; i++ )
    {
      sevenColumn[i] = new XButton( String.valueOf(i + 1), 7 );
      sevenColumn[i].addActionListener(this);
      sevenColumn[i].setFocusPainted(false);
      sevenColumn[i].setDefaultCapable(false);
      
      constraints.gridx = 2;
      constraints.gridy = i;
      constraints.fill = GridBagConstraints.BOTH;
      p.add(sevenColumn[i], constraints);
      sevenColumn[i].setPreferredSize(new Dimension(100, 50));
    }
    
    // add the checkbox for selecting multiple boxes at a time
    constraints.gridwidth = 2;
    constraints.gridx = 0;
    constraints.gridy = 8;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    p.add(selectMultipleBox, constraints);
    // add an actionlistener to the checkbox
    selectMultipleBox.addActionListener(this);
    
    // add the reset button
    reset.addActionListener(this);
    constraints.gridwidth = 1;
    constraints.gridx = 2;
    constraints.gridy = 8;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weightx = 0;
    constraints.weighty = 0;
    p.add(reset, constraints);
    
    // add the turn indicator and label
    constraints.gridwidth = 3;
    constraints.gridx = 0;
    constraints.gridy = 9;
    constraints.weightx = 1;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.BOTH;
    p.add(turnPanel, constraints);
    turnPanel.add(turnLabel, BorderLayout.CENTER);
  }
  
  public void start(ThreeFiveSeven game)
  {
    // add the container to the pane, in the center of it.
    c.add(p, BorderLayout.CENTER);
    
    // set the size of the game window
    game.setSize(600, 900);
    // make it so people can't make the window too small
    game.setMinimumSize( new Dimension(350, 500) );
    // make the game quit when the player closes the window
    game.setDefaultCloseOperation(EXIT_ON_CLOSE);
    // since gridbag is flexible, the player can resize the window all they want
    game.setResizable(true);
    // put everything together
    game.pack();
    // center the window on the screen
    game.setLocationRelativeTo(null);
    // show the window
    game.setVisible(true);
  }
  
  public void restart()
  {
    this.dispose();
    
    String[] args = {};
      
      try {
        this.main(args);
      }
      catch (Exception ex)
      {
        System.out.println(ex);
      }
  }
  
  public void actionPerformed(ActionEvent e)
  {
    
    if ( e.getActionCommand() == "Reset" )
    {
      this.restart();
    }
    
    if ( e.getActionCommand() == "Select Multiple" )
    {
      // fires when the checkbox to select multiple is clicked
      AbstractButton abutton = (AbstractButton) e.getSource();
      this.multiSelectState = abutton.getModel().isSelected();
      
      if ( multiSelectState == false )
      {
        toggleColumns( 3, true );
        toggleColumns( 7, true );
        
        if (this.buttonRemoved)
        {
          this.buttonRemoved = false;
          turnChange();
        }
      }
    }
    
    // if one of the numbered buttons is clicked
    if ( e.getActionCommand() != "Select Multiple" && e.getActionCommand() != "Reset" )
    {
      // if a button is clicked and multiple select is enabled
      if ( multiSelectState )
      {
        // get what column the XButton is in, then disable all the other buttons
        XButton b = (XButton)e.getSource();
        toggleColumns( b.getColumn(), false );
        addPlaceHolderForButton( b, p );
        this.buttonRemoved = true;
      }
      
      // if a button is clicked and multi-select isn't enabled
      else if ( !multiSelectState )
      {
        // get the button that was clicked
        XButton button = (XButton)e.getSource();
        
        addPlaceHolderForButton( button, p );
        turnChange();
      }
    }
  }
  
  public void toggleColumns( int activeColumn, boolean state )
  {
    if ( activeColumn == 3 )
    {
      // toggle the selection option for buttons not in the parent column
      for ( int i = 0; i < 5; i++ )
        fiveColumn[i].setEnabled(state);
      
      for ( int i = 0; i < 7; i++ )
        sevenColumn[i].setEnabled(state);
    }
    else if ( activeColumn == 5 )
    {
      for ( int i = 0; i < 3; i++ )
        threeColumn[i].setEnabled(state);
      
      for ( int i = 0; i < 7; i++ )
        sevenColumn[i].setEnabled(state);
    }
    else if ( activeColumn == 7 )
    {
      for ( int i = 0; i < 3; i++ )
        threeColumn[i].setEnabled(state);
      
      for ( int i = 0; i < 5; i++ )
        fiveColumn[i].setEnabled(state);
    }
  }
  
  public void addPlaceHolderForButton( XButton button, JPanel mainPanel )
  {
    // create the JPanel that will be taking the place of the button in the GBLayout
    JPanel placeHolder = new JPanel();
    // size the panel to the size of the button
    placeHolder.setPreferredSize( button.getPreferredSize() );
    // get the layout of the main panel
    GridBagLayout placeHolderLayout = (GridBagLayout)mainPanel.getLayout();
    // use the constraints of the original button for the new JPanel
    GridBagConstraints gbc = placeHolderLayout.getConstraints( button );
    
    // get rid of the button
    mainPanel.remove(button);
    // add the panel in its place
    mainPanel.add(placeHolder, gbc);
    // redraw the window
    mainPanel.revalidate();
    mainPanel.repaint();
  }
  
  public void turnChange()
  {
    if ( turnLabel.getText() == "Player 1's turn" )
      turnLabel.setText("Player 2's turn");
    else
      turnLabel.setText("Player 1's turn");
  }
  
  public static void main(String[] args) throws Exception
  {
    // make the game look at home
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    
    // create a new instance of the game
    ThreeFiveSeven game = new ThreeFiveSeven();
    game.setup();
    game.start(game);
  }
}