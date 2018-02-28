/**
 * The unified entity of four blocks put together into a shape.
 * Used to move or rotate all of the blocks togehter.
 *
 * @author jacob-huber
 */
public class Tetromino {
	// Block list consisting of the four blocks a part of a Tetromino
	private Block[] blocks;

	// Boolean whether the block is a straight piece or not (Rotation is different)
	private boolean isStraight;

	/**
	 * Creates a new Tetromino with the given blocks and also sets
	 * whether the block should be treated as if it's straight.
	 *
	 * @param blocks
	 * @param isStraight
	 */
	public Tetromino(Block[] blocks, boolean isStraight) {
		this.setBlocks(blocks);
		this.isStraight = isStraight;

		for (Block block : this.blocks) {
			block.setTetromino(this);
		}
	}

	/**
	 * Returns a shallow copy of the instance's blocks
	 *
	 * @return Block[]
	 */
	public Block[] getBlocks() {
		Block[] returnBlocks = new Block[4];

		for (int i = 0; i < 4; i++) {
			returnBlocks[i] = new Block(this.blocks[i]);
		}
		return returnBlocks;
	}

	/**
	 * Sets the list of blocks to be used by this instance
	 */
	public void setBlocks(Block[] blocks) {
		this.blocks = blocks;
	}

	/**
	 * Checks whether moving any of the blocks to a given position will have them collide with anything
	 * returning a Block[] with the blocks in the new position or null if a collision was detected.
	 *
	 * @return Block[] 
	 *
	 * @param horDist
	 * @param verDist
	 */
	public Block[] checkCollideMove(int horDist, int verDist) {
		Block[] manipBlocks = this.getBlocks();

		int[] newXArray = new int[4];
		int[] newYArray = new int[4];

		for (int i = 0; i < 4; i++) {
			newXArray[i] = manipBlocks[i].getPositionX() + horDist;
			newYArray[i] = manipBlocks[i].getPositionY() + verDist;
		}

		int count = 0;
		while (count != 4) {
			for (int i = 0; i < 4; i++) {
				Block outcome = manipBlocks[i].checkColliding(newXArray[i], newYArray[i]);
				if (outcome == null) {
					if (manipBlocks[i].getPositionX() != newXArray[i] || manipBlocks[i].getPositionY() != newYArray[i]) {
						count += 1;
						manipBlocks[i].setPositionX(newXArray[i]);
						manipBlocks[i].setPositionY(newYArray[i]);
					}
				} else if (outcome.getTetromino() != this) {
					return null;
				}
			}
		}

		return manipBlocks;
	}

	/**
	 * Moves the instance's blocks a given distance.
	 *
	 * @return boolean
	 *
	 * @param horDist
	 * @param verDist
	 */
	public boolean move(int horDist, int verDist) {
		Block[] outcome = this.checkCollideMove(horDist, verDist);
		if (outcome != null) {
			this.setBlocks(outcome);
			return true;
		}
		return false;
	}

	/**
	 * Moves the instance down until it collides.
	 */
	public void placeTetromino() {
		while (this.move(0, 1));
		this.setFalling(false);
	}

	/**
	 * Checks if the instance's blocks will collide with anything if it is rotated in the given direction.
	 * If there is no collision returns a Block[] where the blocks have been rotated around index zero.
	 * Otherwise null is returned.
	 *
	 * IMPORTANT:
	 * 
	 * Straight block rotation is not implemented yet. Also this may not work because some blocks may try 
	 * to be moved into the position of other blocks of the same Tetromino. (Checks need to be added)
	 *
	 *
	 * @return Block[]
	 *
	 * @param turnClockwise
	 */
	public Block[] checkCollideRotate(boolean turnClockwise) {
		Block[] manipBlocks = this.getBlocks();

		// Skip i = 0 because that's the center block (When rotating that block will not move)
		for (int i = 1; i < 4; i++) {
			// Get block x and y relative to the center block of the Tetromino
			int bX = manipBlocks[i].getPositionX() - manipBlocks[0].getPositionX();
			int bY = manipBlocks[i].getPositionY() - manipBlocks[0].getPositionX();
			
			if (turnClockwise) {
				int tempX = bX;
				bX = bY;
				bY = tempX;
			}

			// Corner Piece
			if (bX != 0 && bY != 0) {
				if (bX == bY) {
					bY *= -1;
				} else {
					bX = bY;
				}
			// Edge Piece
			} else {
				if (bX != 0) {
					bY = bX * -1;
					bX = 0;
				} else {
					bX = bY;
					bY = 0;
				}
			}

			if (turnClockwise) {
				int tempX = bX;
				bX = bY;
				bY = tempX;
			}

			// Change x, y back to a proper space on the grid now that they've been rotated around center x, y.
			bX += manipBlocks[0].getPositionX();
			bY += manipBlocks[0].getPositionY();
			
			if (!(manipBlocks[i].setPositionX(bX) && manipBlocks[i].setPositionY(bY))) {
				manipBlocks = null;
				break;
			}
		}
		
		return manipBlocks;
	}

	/**
	 * Rotates the instance in the given direction returning whether it was successful or not.
	 *
	 * @return boolean
	 *
	 * @param turnClockwise
	 */
	public boolean rotate(boolean turnClockwise) {
		Block[] outcome = this.checkCollideRotate(turnClockwise);
		if (outcome != null) {
			this.setBlocks(outcome);
			return true;
		} 
		return false;
	}


	/**
	 * Sets all blocks of this instance to be falling or not 
	 *
	 * @param falling
	 */
	public void setFalling(boolean falling) {
		for (Block block : this.blocks) {
			block.setFalling(falling);
		}
	}
}