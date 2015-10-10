# ThreeFiveSevenGame v1.1   
####A small mathematical game made in Java.
![Main Game Window](http://i.imgur.com/pjAW08l.gif)
###[Download Here!](https://github.com/RalphORama/ThreeFiveSevenGame/releases/latest)

####The rules are simple:
- Two players per game.
- Players can flip a coin, play Scissors/Paper/Rock, etc. to determine who goes first.
- During a player's turn, they can erase any amount of squares in a column.
- Players cannot erase squares from more than one column per turn.
- The goal is to erase squares in such a way that your opponent is left with one square when their turn begins.

####Roadmap:
These are the features that I'd like to add (in order of importance).

- [x] Turn system.
- [x] Confirmation prompt when 'restart' button is clicked.
- [x] Automatic detection of when a game is done (i.e. only one square left).
- [ ] Disable tabbing through squares.
- [ ] Make window respawn in same position (rather than center of screen) when reset button is clicked.
- [ ] Custom names for each player, rather than just "Player 1" and "Player 2"

####Compiling:
```
mkdir ThreeFiveSeven && cd ThreeFiveSeven
git clone https://github.com/RalphORama/ThreeFiveSevenGame.git .
javac ThreeFiveSeven.java XButton.java
jar -cef ThreeFiveSeven.ThreeFiveSeven ThreeFiveSeven.jar -C . .
java -jar ThreeFiveSeven.jar
```
