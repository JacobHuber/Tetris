public class Block {
	private int positionX;
	private int positionY;

	private boolean falling = true;
	
	// Creates a new game.
	private Game game;



	// Constructor that creates a new block.
	public Block(Game game) {
		this.game = game;
		this.setPositionX(this.game.getBlockSpawnX());
		this.setPositionY(this.game.getBlockSpawnY());
	}


	// Getters for getting the game, x and y coordinates and if it's falling. 
	public int getPositionX() {
		return this.positionX;
	}
	public int getPositionY() {
		return this.positionY;
	}
	public boolean getFalling() {
		return this.falling;
	}
	public Game getGame() {
		return this.game;
	}


	// Setters for setting the x and y positions.
	public boolean setPositionX(int positionX) {
		if (positionX >= 0 && positionX < game.gridWidth) {
			if (!this.checkColliding(positionX, this.getPositionY())) {
				this.positionX = positionX;
				return true;
			}
		}
		return false;
	}
	public boolean setPositionY(int positionY) {
		if (positionY >= 0 && positionY < game.gridHeight) {
			if (!this.checkColliding(this.getPositionX(), positionY)) {
				this.positionY = positionY;
	
			    return true;
			}
		}
		return false;
	}
	// Setter that set if the block is falling
	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	// Movement methods.
	public void moveDown() {
		if(!this.setPositionY(this.getPositionY() + 1)) {
			this.setFalling(false);
		}
	}
	public void moveLeft() {
		this.setPositionX(this.getPositionX() - 1);
	}
	public void moveRight() {
		this.setPositionX(this.getPositionX() + 1);
	}

	// Placing the block.
	public void placeBlock() {
		while (this.getFalling()) {
			this.moveDown();
		}
	}
	
	// Checks if there is a collision with blocks in the coordinates in the parameter.
	public boolean checkColliding(int positionX, int positionY) {
		Block[] blocks = this.game.getArrayBlocks();
		for (int blockIndex = 0; blockIndex < blocks.length; blockIndex++) {
			Block otherBlock = blocks[blockIndex];
			if (otherBlock != this && otherBlock != null) {
				if (positionX == otherBlock.getPositionX() && positionY == otherBlock.getPositionY()) {
					return true;
				}
			}
		}
		return false;
	}


}
