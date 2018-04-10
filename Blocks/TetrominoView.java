package Blocks;
import GUI.MainViewFX;
import javafx.scene.shape.Rectangle;

import javafx.scene.paint.Color;

public class TetrominoView extends TetrominoController {

	public TetrominoView(Block[] blocks, boolean isStraight) {
        super(blocks, isStraight);
    }

    public TetrominoView(TetrominoModel tetromino) {
        super(tetromino);
    }

    public void clearFill(MainViewFX mv) {
    	for (Block block : this.getBlocks()) {
			if (block != null) {
				mv.getTetrominos()[block.getPositionY()][block.getPositionX()].setFill(Color.web(mv.tetrominoDefaultColor));
			}
		}
    }

	public void draw(MainViewFX mv) {
		for (Block block : this.getBlocks()) {
			if (block != null) {
				mv.getTetrominos()[block.getPositionY()][block.getPositionX()].setFill(block.getColor());
			}
		}
	}
}