/**
 * Main class. Runs all the necessary components for the game.
 */
public class Tetris {
    /** 
     * Creates a new game and runs it.
     */
    public static void main(String[] args) {
        Game game = new Game();
        
        // Runs the game.
        while (game.getGameRunning()) {
            game.tick();
        
        }
        // If the game isn't running or the endgame condition is met, it will print "Game Over!".
        System.out.println("Game Over!");

    }

}
