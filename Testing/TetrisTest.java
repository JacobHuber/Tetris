package Testing;

import static org.junit.Assert.*;
import org.junit.Test;

import javafx.scene.paint.Color;

import Blocks.Block;
import Game_Main.Game;
import GUI.MainViewFX;

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
	
}