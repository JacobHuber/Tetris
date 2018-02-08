
import javafx.scene.paint.Color;

/**
 *  Controls the game logic of Tetris via Block objects.
 * @see Block.java
 *
 */
public class Game {

    // Width of game field
    public int gridWidth;
    // Height of game field
    public int gridHeight;
    // The array of blocks representing the backend of the tetris grid
    private Block[] arrayBlocks;
    //Score and timer integers for keeping score
    private int score = 0;
    private int time = 0;
    // The center block for the falling tetronimo, used for rotations
    private Block centerBlock;
    
    private Player player;
    
    private int blockSpawnY;
    private int blockSpawnX;

    /**
     * Creates the game with a standard 8 wide, 24 tall grid.
     */
    public Game() {
        this.gridWidth = 8;
        this.gridHeight = 24;
        
        this.player = new Player();
        
        this.blockSpawnX = this.gridHeight - 2;
        this.blockSpawnX = this.gridWidth / 2;
        
        this.arrayBlocks = new Block[this.gridWidth * this.gridHeight];
    }

    /**
     * Creates the game with user entered values. Minimum 6 by 6 grid.
     * @param width Has to be minimum 6
     * @param height Has to be minimum 6
     */
    
    /*
    public Game(int width, int height) {
        this.gridWidth = width;
        this.gridHeight = height;
        
                this.blockSpawnX = this.gridHeight - 2;
        this.blockSpawnX = this.gridWidth / 2;
        
        this.arrayBlocks = new Block[this.gridWidth][this.gridHeight];
          for(Block[] line : this.arrayBlocks){
            for(Block b : line){
                b.setFalling(false);
            }
        }
    }
*/
    /**
     * Returns the Color array for the tetris field, to repaint the GUI.
     * @return the Color array for display
     * @throws java.lang.Exception 
     */
    public Color[][] getColorArr() throws Exception {
        throw new Exception();
        //return this.arrayBlocks;
    }

    private void rotateBlock(){
        
    }
    
    private void moveBlockDown(){
        this.centerBlock.moveDown();
    }
    
    /**
     * Creates a new, randomly generated block. Never creates the same block twice in a row.
     */
    public void createBlock() {
        this.player.blockFalling = new Block(this);
        this.player.blockFalling.placeBlock();
    }

    /**
     * Steps the game, gets user input to rotate/move the falling block, and move the block down one tile.
     */
    public void tick() {
        if(this.player.blockFalling == null){
            this.createBlock();
        }
        this.player.getUserInput();
        this.player.blockFalling.moveDown();
    }

    public Block[] getArrayBlocks() {
        return this.arrayBlocks;
    }

    public void printScreen() {
        String screen = "";
        for (int col = 0; col < this.gridHeight; col++) {
            for (int row = 0; row < this.gridWidth; row++) {
                /*
				for (Block[] selectedBlock : this.getArrayBlocks()) {
					if (col == selectedBlock.getPositionX() && row == selectedBlock.getPositionY()) {
					}
				}
                 */

            }
        }

    }
}
