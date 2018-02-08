public class Block {
	private int positionX;
	private int positionY;

	private boolean falling = true;

	private Game game;




	public Block(Game game) {
		this.game = game;
		this.setPositionX(this.game.gridWidth/2);
		this.setPositionY(0);
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


	// Setters

	public boolean setPositionX(int positionX) {
		if (positionX >= 0 && positionX < game.gridWidth) {
			if (this.checkColliding(positionX, this.getPositionX())) {
				this.positionX = positionX;
				return true;
			}
		}
		return false;
	}

	public boolean setPositionY(int positionY) {
		if (positionY >= 0 && positionY < game.gridHeight) {
			if (this.checkColliding(this.getPositionY(), positionY)) {
				this.positionY = positionY;
				return true;
			}
		}
		return false;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public void moveDown() {
		if !(this.setPositionY(this.getPositionY() + 1)) {
			this.setFalling(false);
		}
	}

	public void moveLeft() {
		this.setPositionX(this.getPositionX() - 1);
	}

	public void moveRight() {
		this.setPositionX(this.getPositionX() + 1);
	}

	public void placeBlock() {
		while (this.getFalling()) {
			this.moveDown();
		}
	}

	public boolean checkColliding(int positionX, int positionY) {
		Block[] blocks = this.game.getArrayBlocks();
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