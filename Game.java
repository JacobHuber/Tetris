
import javafx.scene.paint.Color;

/**
 * Controls the game logic of Tetris via Block objects.
 *
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

    // Used to control the next color iterated over for the blocks.
    private int colorInt = 0;

    /**
     * Prints to terminal with the game grid each turn if true.
     */
    public final boolean PRINT_TO_TERMINAL = false;

    //Getters for the width, height, blocks, spawn coordinates, player and running the game.
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
        return this.blockSpawnY;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean getGameRunning() {
        return this.gameRunning;
    }

    // Setters for the spawn coordinates, player and running the game.
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
        this.player = player;
    }

    /**
     * Creates the game with an inputted grid, height, and milliseconds per tick
     * the block fall automatically.
     */
    public Game(int width, int height) {
        //System.out.println(width + "   " + height);
        this.gridWidth = width;
        this.gridHeight = height;

        this.player = new Player();

        this.blockSpawnX = this.gridWidth / 2;
        this.blockSpawnY = 0;

        this.arrayBlocks = new Block[this.gridWidth * this.gridHeight];
    }

    /**
     * Creates a new block, and checks if there is a block already existing in
     * the block creation position to tell whether the game has ended or not.
     */
    public void createBlock(Color c) {
        //System.out.println("New block created");
        
        // Temporary block creation (Should be randomized in the future.)
        Block[] blocks = {new Block(this, c, 5, 0), new Block(this, c, 4, 0), new Block(this, c, 6, 0), new Block(this, c, 5, 1)};
        this.player.tetrominoFalling = new Tetromino(blocks, false);

        for (Block block : this.player.tetrominoFalling.getBlocks()) {
            updateBlock(block);
        }
    }

    /**
     * Steps the game, creating a block if needed, printing the game screen,
     * getting user input to move the falling block or set the block in place,
     * then moving it down. Pass the isUserInput boolean as true if it is a user
     * move, and pass the mapped int as a move. Otherwise pass as false for a
     * falldown tick.
     *
     * @param isUserInput
     * @param userInput
     */
    public void tick(boolean isUserInput, int userInput) {
        System.out.println("Tick!");
        // If no falling block exists or the current falling block has stopped falling (Collided), create a new block
        if (this.player.tetrominoFalling == null || !this.player.tetrominoFalling.getFalling()) {
            this.createBlock(getNextColor());
        }

        // Clear the reference from the previous array spot to the falling block)
        for (Block block : this.player.tetrominoFalling.getBlocks()) {
            removeBlock(block);
        }
        
        if (!isUserInput || userInput == 0) {
            this.player.tetrominoFalling.move(0, 1);
        }

        // If the method was called with user input, parse it and then do the respective move.
        if (isUserInput) {
            System.out.println("User input detected: " + userInput);
            switch (userInput) {
                case 1:
                    this.player.tetrominoFalling.move(-1, 0);
                    break;
                case 2:
                    this.player.tetrominoFalling.move(1, 0);
                    break;
                case 3:
                    // CCW
                    this.player.tetrominoFalling.rotate(false);
                    break;
                case 4:
                    // CW
                    this.player.tetrominoFalling.rotate(true);
                    break;
            }
        }

        // Set a new reference to the falling block in its new position
        for (Block block : this.player.tetrominoFalling.getBlocks()) {
            updateBlock(block);
        }
        
        // If true, call the printScreen method. Used for debugging.
        if (this.PRINT_TO_TERMINAL) {
            this.printScreen();
        }
    }
    
    public void updateBlock(Block block) {
        if (block != null) {
            this.arrayBlocks[block.getPositionX() + (this.getGridWidth()*block.getPositionY())] = block;
        }
    }

    public void removeBlock(Block block) {
        if (block != null) {
            this.arrayBlocks[block.getPositionX() + (this.getGridWidth()*block.getPositionY())] = null;
        }
    }

    /**
     * Prints a representation of the current game board.
     */
    public void printScreen() {
        String screen = "";

        int count = 0;
        for (Block block : this.getArrayBlocks()) {
            if (block == null) {
                screen += ".";
            } else {
                screen += "x";
            }
            count += 1;
            if (count == this.getGridWidth()) {
                count = 0;
                screen += "\n";
            }
        }

        System.out.println(screen);
    }

    /**
     * Returns the width and height assigned to this game object.
     * @return 
     */
    @Override
    public String toString() {
        return this.gridHeight + "  " + this.gridWidth;
    }

    /**
     * Used to iterate over 7 different colors in a sequential fashion. Returns a javaFX color object.
     * @return next Color
     */
    public Color getNextColor() {
        switch (this.colorInt) {
            case 0:
                this.colorInt++;
                return Color.CYAN;
            case 1:
                this.colorInt++;
                return Color.BLUE;
            case 2:
                this.colorInt++;
                return Color.ORANGE;
            case 3:
                this.colorInt++;
                return Color.YELLOW;
            case 4:
                this.colorInt++;
                return Color.LIME;
            case 5:
                this.colorInt++;
                return Color.MAGENTA;
            case 6:
                this.colorInt = 0;
                return Color.RED;
        }
        System.err.println("COLOR ERROR!");
        return null;

    }
}
