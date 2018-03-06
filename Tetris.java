
/**
 * Main class. Runs all the necessary components for the game.
 */
public class Tetris {

    /**
     * Creates a new game and runs it.
     */
    public static void main(String[] args) {
        try {
            MainViewFX main = new MainViewFX();
            InitPopup initpopup = new InitPopup();
            initpopup.run();
            main.init(args, initpopup.getTetrisGridDimensions().width, initpopup.getTetrisGridDimensions().height, initpopup.getAutoFall());
        } catch (Exception e) {
            Kaizen_85.panic();
        }
    }

}
