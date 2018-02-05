# Tetris
CPSC 233 T03-2 (2017-2018 Winter) Final Project


Main Class
	- Runs App
	- Sets Up a Game

	Intance Variables
		- Game variable

	Methods
		- Create Game


Block Class
	- Block Dimensions?
	- Position of Self

	Instance Variables
		- Position X (int)
		- Position Y (int)
		- Some way to represent shape
		- Boolean Falling/Set (Maybe?)

	Constructor
		- two int parameters for pos x and pos y
		- falling = true;

	Methods
		- Get X
			- no parameters
			- return int
		- Get Y
			- no parameters
			- return int
		- Set X
			- int parameter
			- no return
		- Set Y
			- int parameter
			- no return

		- Move Down (1 space)
			- no parameters
			- no return
		- Move Left (1 space)
			- no parameters
			- no return
		- Move Right (1 space)
			- no parameters
			- no return

		- Collision Checking
			- no parameters
			- returns boolean

		- Set (Place the block down to the lower ground below it)
			- no parameters
			- no return


Game Class
	- Board 
	- Score 
	- Create Blocks (Timer and method)
	- Etc...

	Instance Variables
		- GridSpace (Probably a 2d array)
		- Blocks (1d array of blocks)
		- Score (Don't really need for first demo but eventually yeah)
		- Time (Timer for spawning blocks)

	Methods
		- Create Block (Random block, placed at top centre, )
			- no parameters
			- no return
		- Tick
			- no parameters
			- no return




Player Class
	- Current Block
	- Controls
	- Hold Slot

	Instance Variables
		- Current Block (The block that is currently falling)
		- Hold Block (The block that is currently stored)

	Methods
		- Get Input (and apply it)
			- no parameters
			- no return



What we need for the Demo One:

	- Text Based Version of Game
	- Class Diagram (An actual one that isn't this shitty one, teacher recommends draw.io)






Set up a basic structure for our classes to get us started for Demo 1. Most things will be super simple to setup but others will take a bit more time so I'm not too sure how to split up the work yet.

I'm thinking for now we'll leave blocks as just 1x1 bricks, and for the representation of the gridspace well have a 2d array, the inner one being a boolean array showing whether it is occupied or not. i.e: [ [0, 1, 1, 0, 0], [1, 0 0 0 0], [0, 0, 1, 1, 0] ]

We can figure out the actual size of the grid space later but for now it can just be 8x24

Let me know any problems we have
