# ThreeFiveSevenGame v1.0   
####A small mathematical game made in Java.
![Main Game Window](http://i.imgur.com/C18on2b.png)

####The rules are simple:
- Two players per game.
- Players can flip a coin, play Scissors/Paper/Rock, etc. to determine who goes first.
- During a player's turn, they can erase any amount of squares in a column.
- Players cannot erase squares from more than one column per turn.
- The goal is to erase squares in such a way that your opponent is left with one square when their turn begins.

####Compiling:
```
mkdir ThreeFiveSeven && cd ThreeFiveSeven
git clone -b v1.0 https://github.com/RalphORama/ThreeFiveSevenGame.git .
javac ThreeFiveSeven.java XButton.java
jar -cef ThreeFiveSeven.ThreeFiveSeven ThreeFiveSeven.jar -C . .
java -jar ThreeFiveSeven.jar
```
