package Blocks;
import GUI.MainViewFX;
import javafx.scene.shape.Rectangle;

import javafx.scene.paint.Color;


/**
 * Handles the GUI drawing (showing each block on the screen) of a Tetromino
 *
 */
public class TetrominoView extends TetrominoController {

	/**
     * Constructor to define a TetrominoView with the provided Block[] and whether the block is straight or not.
     * Calls super constructor.
     *
     * @param blocks
     * @param isStraight
     */
	public TetrominoView(Block[] blocks, boolean isStraight) {
        super(blocks, isStraight);
    }

    /**
     * A copy constructor for a TetrominoController.
     * Calls super constructor.
     *
     * @param tetromino
     */
    public TetrominoView(TetrominoModel tetromino) {
        super(tetromino);
    }

    /**
     * Removes the fill (of the square) of each block apart of this TetrominoView.
     *
     * @param mv
     */
    public void clearFill(MainViewFX mv) {
    	for (Block block : this.getBlocks()) {
			if (block != null) {
				mv.getTetrominos()[block.getPositionY()][block.getPositionX()].setFill(Color.web(mv.tetrominoDefaultColor));
			}
		}
    }

    /**
     * Sets the fill (of the square) of each block apart of this TetrominoView.
     *
     * @param mv
     */
	public void draw(MainViewFX mv) {
		for (Block block : this.getBlocks()) {
			if (block != null) {
				mv.getTetrominos()[block.getPositionY()][block.getPositionX()].setFill(block.getColor());
			}
		}
	}
}