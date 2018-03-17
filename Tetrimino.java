
import java.awt.*;

/**
 * @author ejuduokpanachi
 * @param <Block>
 * @param <COLOR>
 *
 */
public class Tetrimino {

    public static final int BLOCK = 4;

    private Block[] blocks;
    private Color color;
    private TetriminoShape shape;
    private int rotation;

    /**
     * @param blocks
     * @param color
     */
    public Tetrimino(TetriminoShape shape, Color color, int rotationDegrees) {
        Block[] arrBlock = new Block[BLOCK];
        this.blocks = arrBlock;
        this.shape = shape;
        this.color = color;
        this.rotation = rotationDegrees / 90;
        //startingBlocks(shape, arrBlock);              TODO
    }

    /* Getters and setters */
    public Block[] getBlocks() {
        return blocks;
    }

    /**
     * @param blocks the blocks to set
     */
    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @param shape the shape to set
     */
    public void setTetriminoShape(TetriminoShape shape) {
        this.shape = shape;
    }

    /**
     * @return the block
     */
    public static int getBlock() {
        return BLOCK;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the shape
     */
    public TetriminoShape getShape() {
        return shape;
    }

    /* End of getters and setters */

    /**
     * Initializes code blocks attribute
     *
     * @param minoshape
     * @param bloc
     */
    private void StartingBlocks(TetriminoShape minoShape, Block[] bloc) {
        // iterate through the Point array of this TetriminoShape and create 	//four blocks

        for (int i = 0; i < minoShape.getTetrisLocations().length; i++) {
           // bloc[i] = new Block(minoShape.getColor(),     TODO
               //     minoShape.getTetrisLocations()[i]);       TODO
        }
    }

    public boolean moveDown() {
        return true;
    }

    public void placeTetrimino() {
        return;
    }

    public boolean moveHorizontal() {
        return true;
    }

    public void rotateClockWise() {
        rotation = (rotation + 1) % 4;
    }
}
