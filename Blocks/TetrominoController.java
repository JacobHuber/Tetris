package Blocks;

/**
 * Handles all the controls for a TetrominoModel
 */
public class TetrominoController extends TetrominoModel {

    /**
     * Constructor to define a TetrominoController with the provided Block[] and whether the block is straight or not.
     * Calls super constructor.
     *
     * @param blocks
     * @param isStraight
     */
    public TetrominoController(Block[] blocks, boolean isStraight) {
        super(blocks, isStraight);
    }

    /**
     * A copy constructor for a TetrominoController.
     * Calls super constructor.
     *
     * @param tetromino
     */
    public TetrominoController(TetrominoModel tetromino) {
        super(tetromino);
    }
	
    /**
     * Takes the keyboard input (A string of which key was pressed) and handles whether it is a key to move the Tetromino
     * or a key that the game should handle.
     *
     * @param keyboardInput
     */
    public String handleInput(String keyboardInput) {
        String msg = "";
        switch (keyboardInput) {
            case "S":
            case "A":
            case "D":
            case "Q":
            case "E":
                this.movePiece(keyboardInput);
                break;
            case "Shift":
                msg = "Hold";
                break;
            default:
                break;
        }
        return msg;
    }

    /**
     * Takes the keyboard input (A string of which key was pressed) and handles which way the TetrominoController should move.
     *
     * @param keyboardInput
     */
    public void movePiece(String keyboardInput) {
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
                // Counter-Clockwise
                this.rotate(false);
                break;
            case "E":
                // Clockwise
                this.rotate(true);
                break;
            default:
                break;
        }
    }
}