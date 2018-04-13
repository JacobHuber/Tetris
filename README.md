# Tetris
CPSC 233 T03-2 (2017-2018 Winter) Final Project

## How to Run/Play:

### Running
WINDOWS:
1. To run the game, simply run Tetris.bat

OTHER:
(It is recommended to delete any .class files in any of the packages but no necessarily required)

1. In the root folder, run the command: javac Tetris_Main/Tetris.java
2. Next, run the command: java Tetris_Main/Tetris

## Playing
When the game has started up you will be brought to an input window requesting a width, height, speed (ms), and a highscore file (.txt)
Recommended settings are a length of 10, width of 20, and speed of 1000.

To move a pieces around use A, S, D, Q and E.
* A: Left
* S: Down
* D: Right
* Q: Rotate CCW
* E: Rotate CW

* Shift: Hold/Store

To store/hold a block, press Shift. This will swap your current block with the one that is currently stored.
If you do not have a block currently stored then it stores the current one and creates a new one.

The objective of the game is to place your blocks in the most compact way.
Once you fill a horizontal line up on the grid space that line will disappear.

The more lines you clear at a time the more points you will get.
Though, no matter how many lines you clear at a time, the speed will increase by 10%.

To get the best score you will want to clear multiple lines at a time.

## Testing:

### Unit Testing
WINDOWS:
1. To run the unit tests simply run 'runUnitTests.bat'

OTHER:
1. In the testing package, delete any .class files.
2. In the root folder, run the command: javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar Testing/*.java
3. Next, run the command: java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore Testing.TestLogic

### Manual Testing
In the testing folder there is a .txt file with instructions on how to do this.

