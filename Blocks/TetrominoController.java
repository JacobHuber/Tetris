package Blocks;

public class TetrominoController extends TetrominoModel {

    public TetrominoController(Block[] blocks, boolean isStraight) {
        super(blocks, isStraight);
    }

    public TetrominoController(TetrominoModel tetromino) {
        super(tetromino);
    }
	
    public void takeInput(String keyboardInput) {
        switch (keyboardInput) {
            case "S":
                this.move(0, 1);
                break;
            case "A":
                this.move(-1, 0);
                break;
            case "D":
                this.move(1, 0);
                break;
            case "Q":
                this.rotate(false);
                break;
            case "E":
                this.rotate(true);
                break;
            default:
                break;
        }
    }
}