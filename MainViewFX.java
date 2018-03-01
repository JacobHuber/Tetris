
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * For future use when we need to use a GUI.
 *
 * @author kell-gigabyte
 */
public class MainViewFX extends Application {

    // Specific panes
    private FlowPane tetrisPane;
    private FlowPane colorPane;

    // Pre-set color codes for easy modification
    private final String hexHbox = "4A444B";
    private final String hexButtonBox = "BA0101";
    private final String hexColorBox = "FFFFF0";
    private final String hexDelayBox = "78866B";
    private final String hexSliderBox = "90AFFF";
    private final String tetronimoDefaultColor = "FFFFFF";
    private final String tetronimoBackgroundColor = "80BFFF";

    // Width and height of tetris grid
    private static int width, height;

    // Main game object
    public static Game myGame;

    // Main Scene object
    private Scene mainScene;

    // Rectangle representation of the tetris grid
    private Rectangle[][] tetronimos;

    private static long autoFall, lastFall;

    /**
     * Launches the GUI window.
     *
     * @param args
     * @param width
     * @param height
     * @param autoFall
     */
    public void init(String[] args, int width, int height, int autoFall) {
        myGame = new Game(width, height);
        MainViewFX.autoFall = autoFall;
        MainViewFX.width = width;
        MainViewFX.height = height;
        launch(args);

    }

    /**
     * Starts the JavaFX application window, creating the panes for view
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                int i = 0;
                while (true) {
                    final int finalI = i;
                    Platform.runLater(() -> {
                        //System.out.println("AutoFall!");
                        myGame.tick(false, -1);
                        updateRectangles();
                    });
                    i++;
                    Thread.sleep(MainViewFX.autoFall);
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

        primaryStage.setTitle("Tetris V01");

        // The root pane, has a basic layout allowing other panes to go on top
        BorderPane root = new BorderPane();

        // Sets panes on top of the root pane
        root.setRight(addScorePane(null));
        root.setLeft(addNextBlockPane());
        root.setCenter(addTetrisPane());

        // Creates a scene, which is what is actually displayed. Uses the root pane.
        mainScene = new Scene(root);
        

        //setupKeyboard(mainScene);
        mainScene.setOnKeyPressed(keyPressed);

        // Sets the scene, and shows it to the user.
        primaryStage.setScene(mainScene);
        
        primaryStage.show();
    }

    /**
     * Generates the flowPane acting as the tetris window
     */
    private FlowPane addTetrisPane() {
        // Creates a new, empty pane
        FlowPane tetrisPane = new FlowPane();
        // Sets the packground color
        tetrisPane.setStyle("-fx-background-color: #" + this.tetronimoBackgroundColor + ";");

        // Sets the spacing for the Rectangle objects within
        tetrisPane.setPadding(new Insets(5, 10, 5, 10));
        tetrisPane.setVgap(4);
        tetrisPane.setHgap(4);
        tetrisPane.setPrefWrapLength(MainViewFX.width * 79); // preferred width allows for two columns

        //System.out.println(this.height + " " + this.width);
        this.tetronimos = new Rectangle[MainViewFX.height][MainViewFX.width];

        for (int outer = 0; outer < this.tetronimos.length; outer++) {
            for (int inner = 0; inner < this.tetronimos[outer].length; inner++) {
                Rectangle rect = new Rectangle(75, 75);
                rect.setFill(Color.web(this.tetronimoDefaultColor));
                tetrisPane.getChildren().add(rect);
                this.tetronimos[outer][inner] = rect;
            }
        }

        return tetrisPane;
    }

    /**
     * Generates a pane for displaying the next 3 tetronimos
     *
     * @return Vbox pane
     */
    private VBox addNextBlockPane() {
        return new VBox();
    }

    /**
     * Generates a pane with a scoreboard for high scores, displaying top 10,
     * and current score. Can either be given null, for no saved high scores, or
     * given a file location as a string to load scores from.
     *
     * @return Flowpane pane
     */
    private FlowPane addScorePane(String highScoreFile) {
        if (highScoreFile != null) {

        }
        return new FlowPane();
    }

    /**
     * Passes the players move input to the game logic. Mapping of moves is as
     * follows: Move down: 0 Move Left: 1 Move Right: 2 Rotate Left: 3 Rotate
     * Right: 4
     *
     * @param move
     * @deprecated
     */
    private void passMove(boolean isDropdown, int move) {
        //System.out.println("Game tick");
        myGame.tick(isDropdown, move);
    }

    /**
     * Gets the last time is system milliseconds that the block was moved down.
     *
     * @return
     * @deprecated
     */
    private long getLastDownTime() {
        return this.lastFall;
    }

    /**
     * Sets the last system time that the block was moved down, either from user
     * input or the auto timer.
     *
     * @param time
     * @deprecated
     */
    private void setLastDownTime(long time) {
        this.lastFall = time;
    }

    /**
     * Returns the delay between the block falling currently.
     *
     * @return delay
     * @deprecated
     */
    private long getDownDelay() {
        return this.autoFall;
    }

    /**
     * Listens to live keyboard input, in order to move the active tetronimo.
     * Original code:
     * https://stackoverflow.com/questions/37472273/detect-single-key-press-in-javafx
     * Original author: Lisek from stackoverflow
     */
    private void setupKeyboard(Scene scene) {

        scene.setOnKeyPressed(this.keyPressed);

        AnimationTimer rectUpdater = new AnimationTimer() {
            private Rectangle[][] tetronimos = this.tetronimos;

            @Override
            public void handle(long l) {
                Block[] blocks = MainViewFX.myGame.getArrayBlocks();

                int counter = 0;

                for (Rectangle[] rectArr : this.tetronimos) {
                    for (Rectangle rect : rectArr) {
                        rect.setFill(blocks[counter].getColor());
                    }
                }
            }

        };
        rectUpdater.start();
    }

    /**
     * Calls the tick method of the Game object to move down the current block.
     */
    public void drop() {
        System.out.println("Block Drop!");
        myGame.tick(false, 0);
        setLastDownTime(System.currentTimeMillis());
    }

    public void updateRectangles() {
        //System.out.println("Rectangle Update!");
        Block[] blocks = MainViewFX.myGame.getArrayBlocks();

        for (Rectangle[] rectArr : this.tetronimos) {
            for (Rectangle rect : rectArr) {
                rect.setFill(Color.web(this.tetronimoDefaultColor));
            }
        }

        for (Block block : blocks) {
            if (block != null) {
                this.tetronimos[block.getPositionY()][block.getPositionX()].setFill(block.getColor());
            }
        }

    }

    private EventHandler<KeyEvent> keyPressed = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            //System.out.println(myGame.toString());
            String keyName = event.getCode().getName();
            //System.out.println(event.getCode().getName());

            if (keyName.equals("S")) {
                //System.out.println("mov down");
                myGame.tick(false, 0);
                setLastDownTime(System.currentTimeMillis());
                updateRectangles();
            }

            if (keyName.equals("A")) {
                //System.out.println("mov left");
                myGame.tick(true, 1);
                updateRectangles();
            }

            if (keyName.equals("D")) {
                //System.out.println("mov right");
                myGame.tick(true, 2);
                updateRectangles();
            }

            if (keyName.equals("Q")) {
                //System.out.println("rot left");
                myGame.tick(true, 3);
                updateRectangles();
            }

            if (keyName.equals("E")) {
                //System.out.println("rot right");
                myGame.tick(true, 4);
                updateRectangles();
            }
        }
    };

    private Runnable autoDropper = new Runnable() {
        @Override
        public void run() {
            myGame.tick(false, 0);
            //setLastDownTime(System.currentTimeMillis());
        }

    };
}
