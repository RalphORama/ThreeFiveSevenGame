import javax.swing.JButton;

public class XButton extends JButton
{
  private int column;
  //private boolean canDelete;
  
  public XButton(String name, int column)
  {
    super(name);
    
    this.column = column;
    //this.canDelete = true;
  }
  
  public int getColumn()
  {
    return column;
  }
  
  public void setColumn(int column)
  {
    this.column = column;
  }
}