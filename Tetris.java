//Main class. Runs all the necessary methods for the game.

public class Tetris {
    //Creates a new game using methods from the "Game" class
    public static void main(String[] args) {
        Game game = new Game();
        
        //Runs the game
        while (game.getGameRunning()) {
            game.tick();
        
        }//If the game isnt running or the endgame condition is met, it will print "Game Over!"
        System.out.println("Game Over!");

    }

}
