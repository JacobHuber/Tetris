package Testing;

import static org.junit.Assert.*;

import javafx.scene.paint.Color;

import Blocks.Block;
import Game_Main.Game;
import GUI.MainViewFX;

import Blocks.TetrominoModel;

import org.junit.Test;

public class TestLogic {

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

	@Test
	public void test_BlockMoveDown() {
		MainViewFX mv = new MainViewFX();
		Game game = new Game(10, 20, mv);

		Block b1 = new Block(game, (Color) null, 0, 0);

		b1.moveDown();

		int expectedYValue = 1;

		assertEquals("Block move down failed.", b1.getPositionY(), expectedYValue);
	}

	@Test
	public void test_BlockMoveLeft() {
		MainViewFX mv = new MainViewFX();
		Game game = new Game(10, 20, mv);

		Block b1 = new Block(game, (Color) null, 1, 0);

		b1.moveLeft();

		int expectedXValue = 0;

		assertEquals("Block move left failed.", b1.getPositionX(), expectedXValue);
	}

	@Test
	public void test_BlockMoveRight() {
		MainViewFX mv = new MainViewFX();
		Game game = new Game(10, 20, mv);

		Block b1 = new Block(game, (Color) null, 0, 0);

		b1.moveRight();

		int expectedXValue = 1;

		assertEquals("Block move down failed.", b1.getPositionX(), expectedXValue);
	}

	@Test
	public void test_BlockMoveCollide() {
		MainViewFX mv = new MainViewFX();
		Game game = new Game(10, 20, mv);

		Block b1 = new Block(game, (Color) null, 5, 0);
		Block b2 = new Block(game, (Color) null, 6, 0);

		game.updateBlock(b1, game.getArrayBlocks());
		game.updateBlock(b2, game.getArrayBlocks());

		b2.moveLeft();

		int expectedB2Value = 6;

		assertEquals("Block move collide FAILED.", b2.getPositionX(), expectedB2Value);
	}

	@Test
	public void test_TetrominoRotate() {
		MainViewFX mv = new MainViewFX();
		Game game = new Game(10, 20, mv);

		Block[] blocks = {new Block(game, (Color) null, 5, 3), new Block(game, (Color) null, 4, 3), new Block(game, (Color) null, 6, 3), new Block(game, (Color) null, 5, 4)};
		//. . .
		//# # #
		//. # .

		//. # .
		//# # .
		//. # .

		TetrominoModel t = new TetrominoModel(blocks, false);

		t.rotate(true);

		blocks = t.getBlocks();

		int ex1 = 5;
		assertEquals("Rotation test Failed for T BLOCK blockx1.", ex1, blocks[0].getPositionX());
		int ey1 = 3;
		assertEquals("Rotation test Failed for T BLOCK blocky1.", ey1, blocks[0].getPositionY());

		int ex2 = 5;
		assertEquals("Rotation test Failed for T BLOCK blockx2.", ex2, blocks[1].getPositionX());
		int ey2 = 2;
		assertEquals("Rotation test Failed for T BLOCK blocky2.", ey2, blocks[1].getPositionY());

		int ex3 = 5;
		assertEquals("Rotation test Failed for T BLOCK blockx3.", ex3, blocks[2].getPositionX());
		int ey3 = 4;
		assertEquals("Rotation test Failed for T BLOCK blocky3.", ey3, blocks[2].getPositionY());

		int ex4 = 4;
		assertEquals("Rotation test Failed for T BLOCK blockx4.", ex4, blocks[3].getPositionX());
		int ey4 = 3;
		assertEquals("Rotation test Failed for T BLOCK blocky4.", ey4, blocks[3].getPositionY());
	}

	@Test
	public void test_TetrominoTestCollision() {
		MainViewFX mv = new MainViewFX();
		Game game = new Game(10, 20, mv);

		Block[] blocks1 = {new Block(game, (Color) null, 0, 0), new Block(game, (Color) null, 0, 1), new Block(game, (Color) null, 0, 2), new Block(game, (Color) null, 0, 3)};
		Block[] blocks2 = {new Block(game, (Color) null, 1, 0), new Block(game, (Color) null, 1, 1), new Block(game, (Color) null, 1, 2), new Block(game, (Color) null, 1, 3)};

		TetrominoModel t1 = new TetrominoModel(blocks1, true);
		TetrominoModel t2 = new TetrominoModel(blocks2, true);

		game.updateTetromino(t1);
		game.updateTetromino(t2);

		t1.move(1,0);
		t2.move(-1,0);

		
		blocks1 = t1.getBlocks();
		for (int i = 0; i < 4; i++) {
			assertEquals("Tetromino Collide Test Failed, X not same.", blocks1[i].getPositionX(), 0);
		}
		int[] y1 = {0,1,2,3};
		for (int i = 0; i < 4; i++) {
			assertEquals("Tetromino Collide Test Failed, Y not same.", blocks1[i].getPositionY(), y1[i]);
		}

		blocks2 = t2.getBlocks();
		for (int i = 0; i < 4; i++) {
			assertEquals("Tetromino Collide Test Failed, X not same.", blocks2[i].getPositionX(), 1);
		}
		int[] y2 = {0,1,2,3};
		for (int i = 0; i < 4; i++) {
			assertEquals("Tetromino Collide Test Failed, Y not same.", blocks2[i].getPositionY(), y2[i]);
		}

	}
}