package Testing;

import org.junit.Test;

import Blocks.Block;
import Blocks.TetrominoModel;
import GUI.MainViewFX;
import Game_Main.Game;
import javafx.scene.paint.Color;
import static org.junit.Assert.*;


public class TetrominoTest {

	
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
	

	
	
@Test 		// test move down
public void test_BlockMoveDown() {
	MainViewFX mv = new MainViewFX();
	Game game = new Game(10, 20, mv);
	// Create a block
Block b = new Block(game, (Color) null, 0, -4);
	// Move it
int expResult = -4;
int result = b.getPositionX();
assertEquals(expResult, result);
}



@Test
public void test_BlockMoveRight() {
	MainViewFX mv = new MainViewFX();
	Game game = new Game(10, 20, mv);
	// Create a block
Block b = new Block(game, (Color) null, 6, 0);
	// Move it
int expResult = 6;
int result = b.getPositionX();
assertEquals(expResult, result);
}


@Test
public void test_BlockMoveLeft() {
	MainViewFX mv = new MainViewFX();
	Game game = new Game(10, 20, mv);
	// Create a block
Block b = new Block(game, (Color) null, 4, 0);
	// Move it
int expResult = 4;
int result = b.getPositionX();
assertEquals(expResult, result);
}


	
@Test
// 
	public void test_BlockMoveCollide() {
		MainViewFX mv = new MainViewFX();
		Game game = new Game(10, 20, mv);
		
//Move b1 into b2
		Block b1 = new Block(game, (Color) null, 5, 0);	   // Create a block, b1
		Block b2 = new Block(game, (Color) null, 4, -1);  // Create a block, b2

		//assertNotSame("Block move collide passed.", b1 == b2);  // Check if b1 is where b2 is
		assertTrue( b1.equals(b2));
   }	
}	
	
	