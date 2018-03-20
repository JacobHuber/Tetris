package Game_Main;

import GUI.MainViewFX;

/**
 * Main class. Runs all the necessary components for the game.
 */
public class Tetris {
    /** 
     * Creates a new game and runs it.
     */
    public static void main(String[] args) {
        MainViewFX main = new MainViewFX();
        main.init(args);
    }

}
