public class Game {
	public final static int gridWidth = 8;
	public final static int gridHeight = 24;

	private Block[] arrayBlocks = new Block[8*24];


	// 2D Array of grid spots
	// 1D Array of all created blocks

	private Player player = new Player();


	private int score = 0;
	private int time = 0;


	public void createBlock() {
		// Create a new block at the top centre of the screen
	}


	public void tick() {
		// Get user input, and move block down 1 space
	}

	public Block[] getArrayBlocks() {
		return this.arrayBlocks;
	}

	public void printScreen() {
		String screen = "";
		for (int col = 0; col < game.gridHeight; col++) {
			for (int row = 0; row <  game.gridWidth; row++) {
				
				for (Block selectedBlock : game.getArrayBlocks()) {
					if (col == selectedBlock.getPositionX() && row == selectedBlock.getPositionY()) {
						screen
					}
				}

			}
		}

	}
}