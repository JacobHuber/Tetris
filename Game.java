
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
        this.player.blockFalling = new Block(this, c);

        // If there is an empty space at the block creation position set the block there in the array, otherwise end the game.
        if (this.arrayBlocks[this.player.blockFalling.getPositionX() * this.player.blockFalling.getPositionY()] == null) {
            this.arrayBlocks[this.player.blockFalling.getPositionX() * this.player.blockFalling.getPositionY()] = this.player.blockFalling;
        } else {
            this.gameRunning = false;
        }
    }

    /**
     * Steps the game, creating a block if needed, printing the game screen,
     * getting user input to move the falling block or set the block in place,
     * then moving it down. Pass the isUserInput boolean as true if it is a user
     * move, and pass the mapped int as a move. Otherwise pass as false for a
     * falldown tick.
     *
     * @param shouldTerminalPrint
     * @param isUserInput
     * @param userInput
     */
    public void tick(boolean shouldTerminalPrint, boolean isUserInput, int userInput) {
        System.out.println("Tick!");
        // If no falling block exists or the current falling block has stopped falling (Collided), create a new block
        if (this.player.blockFalling == null || !this.player.blockFalling.getFalling()) {
            this.createBlock(getNextColor());
        }

        if (!isUserInput || userInput == 0) {
            // Clear the reference from the previous array spot to the falling block
            this.arrayBlocks[this.player.blockFalling.getPositionX() * this.player.blockFalling.getPositionY()] = null;

            this.player.blockFalling.moveDown();

            // Set a new reference to the falling block in its new position
            this.arrayBlocks[this.player.blockFalling.getPositionX() * this.player.blockFalling.getPositionY()] = this.player.blockFalling;
        }

        if (isUserInput) {
            System.out.println("User input detected: " + userInput);
            switch (userInput) {
                case 1:
                    this.player.blockFalling.moveLeft();
                    break;
                case 2:
                    this.player.blockFalling.moveRight();
                    break;
                case 3:
                    System.out.println("Not yet implemented");
                    break;
                case 4:
                    System.out.println("Not yet implemented");
                    break;
            }
        }

        if (shouldTerminalPrint) {
            this.printScreen();
        }
    }

    /**
     * Prints a representation of the current game board.
     */
    public void printScreen() {
        String screen = "";
        for (int col = 0; col < this.gridHeight; col++) {
            for (int row = 0; row < this.gridWidth; row++) {
                boolean blockFound = false;

                for (Block selectedBlock : this.getArrayBlocks()) {
                    if (selectedBlock != null) {
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

    @Override
    public String toString() {
        return this.gridHeight + "  " + this.gridWidth;
    }

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
