public class Block {
	private int positionX;
	private int positionY;

	private boolean falling = true;

	private Game game;




	public Block(Game game) {
		this.game = game;
	}


	// Getters

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


	public boolean setPositionX(int positionX) {
		if (positionX >= 0 && positionX < game.gridWidth) {
			// Add collision checking with other blocks
			this.positionX = positionX;
			return true;
		}
		return false;
	}

	public boolean setPositionY(int positionY) {
		if (positionY >= 0 && positionY < game.gridHeight) {
			// Add collision checking with other blocks
			this.positionY = positionY;
			return true;
		}
		return false;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public void moveDown() {
		if !(this.setPositionY(this.positionY + 1)) {
			this.setFalling(false);
		}
	}

	public void moveLeft() {
		this.setPositionX(this.positionX - 1);
	}

	public void moveRight() {
		this.setPositionX(this.positionX + 1);
	}

	public void checkColliding(int positionX, int positionY) {
		blocks = this.game.getArrayBlocks();
		for (int blockIndex = 0; blockIndex < blocks.length(); blockIndex++) {
			otherBlock = blocks[blockIndex];
			if (otherBlock != this) {
				if (positionX == otherBlock.getPositionX() && positionY == otherBlock.getPositionY()) {
					return true;
				}
			}
		}
		return false;
	}


}