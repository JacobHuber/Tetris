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

    private int blockSpawnX;
    private int blockSpawnY;
    
    private Player player;
    

    private boolean gameRunning = true;


    public int getGridWidth() {
        return this.gridWidth;
    }

    public int getGridHeight() {
        return this.gridHeight;
    }

    public Block[] getArrayBlocks() {
        return this.arrayBlocks;
    }

    public int getBlockSpawnX() {
        return this.blockSpawnX;
    }

    public int getBlockSpawnY() {
        return this.blockSpawnY();
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean getGameRunning() {
        return this.gameRunning;
    }

    public void setBlockSpawnX(int blockSpawnX) {
        if (blockSpawnX >= 0 && blockSpawnX < this.getGridWidth()) {
            this.blockSpawnX = blockSpawnX;
        }
    }

    public void setBlockSpawnY(int blockSpawnY) {
        if (blockSpawnY >= 0 && blockSpawnY < this.getGridHeight()) {
            this.blockSpawnY = blockSpawnY;
        }
    }

    public void setPlayer(Player player) {
        this.player = Player;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }


    /**
     * Creates the game with a standard 10 wide, 20 tall grid.
     */
    public Game() {
        this.gridWidth = 10;
        this.gridHeight = 20;
        
        this.player = new Player();
        
        this.blockSpawnX = this.gridWidth / 2;
        this.blockSpawnY = 0;
        
        this.arrayBlocks = new Block[this.gridWidth * this.gridHeight];
    }
    
    /**
     * Creates a new block, and checks iff there is a block already existing 
     * in the block creation position to tell whether the game has ended or not.
     */
    public void createBlock() {
        this.player.blockFalling = new Block(this);
        if (this.arrayBlocks[this.player.blockFalling.getPositionX() * this.player.blockFalling.getPositionY()] == null) {
            this.arrayBlocks[this.player.blockFalling.getPositionX() * this.player.blockFalling.getPositionY()] = this.player.blockFalling;
        
        //This is the endgame condition.
        } else {
            this.setGameRunning(false);
        }
    }

    /**
     * Steps the game, gets user input to rotate/move the falling block, and move the block down one tile.
     */
    public void tick() {
        if(this.player.blockFalling == null || !this.player.blockFalling.getFalling()){
            this.createBlock();
        }
        this.printScreen();
        this.arrayBlocks[this.player.blockFalling.getPositionX() * this.player.blockFalling.getPositionY()] = null;
        this.player.getUserInput();
        this.arrayBlocks[this.player.blockFalling.getPositionX() * this.player.blockFalling.getPositionY()] = this.player.blockFalling;
        this.player.blockFalling.moveDown();
    }

    /**
     * Prints a representation of the current game board.
     */
    public void printScreen() {
        String screen = "";
        for (int col = 0; col < this.gridHeight; col++) {
            for (int row = 0; row <  this.gridWidth; row++) {
                boolean blockFound = false;

                for (Block selectedBlock : this.getArrayBlocks()) {
                    if (selectedBlock != null){
                        if (row == selectedBlock.getPositionX() && col == selectedBlock.getPositionY()) {
                            screen += "x";
                            blockFound = true;
                            break;
                        }
                    }
                }
                
                if (!blockFound) {
                    screen += "o";
                }

            }
            screen += "\n";
        }
        System.out.println(screen);

    }
}
