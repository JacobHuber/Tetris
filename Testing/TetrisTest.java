package Testing;

import static org.junit.Assert.*;

import javafx.scene.paint.Color;

import Blocks.Block;
import Game_Main.Game;
import GUI.MainViewFX;

import Blocks.TetrominoModel;

import org.junit.Test;

public class TetrisTest {

	@Test
	public void test_BlockConstructor() {
		MainViewFX mv = new MainViewFX();
		Game game = new Game(10, 20, mv);

		int expectedXValue = 1;
		int expectedYValue = 1;

		Block b = new Block(game, (Color) null, expectedXValue, expectedYValue);

		int xValue = b.getPositionX();
		int yValue = b.getPositionY();

		assertEquals("Testing Constructor of Block, X value not properly set.", expectedXValue, xValue);

		assertEquals("Testing Constructor of Block, Y value not properly set.", expectedYValue, yValue);
	}	

	@Test
	public void test_TetrominoBlocksPrivacy() {
		MainViewFX mv = new MainViewFX();
		Game game = new Game(10, 20, mv);

		Block[] blocks = {new Block(game, (Color) null, 5, 0), new Block(game, (Color) null, 6, 0), new Block(game, (Color) null, 4, 0), new Block(game, (Color) null, 5, 1)};

		TetrominoModel t = new TetrominoModel(blocks, false);

		Block[] value = t.getBlocks();

		assertNotSame("Testing Tetromino Blocks Privacy, does not copy array.", blocks, value);
	}

	@Test
	public void test_BlockCopyConstructor() {
		MainViewFX mv = new MainViewFX();
		Game game = new Game(10, 20, mv);

		Block b1 = new Block(game, (Color) null, 0, 0);

		Block b2 = new Block(b1);

		assertNotSame("Block copy constructor failed.", b1, b2);
	}
}