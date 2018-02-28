
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    private static int autoFall;
    private long lastFall;
    
    // Width and height of tetris grid
    private static int width;
    private static int height;
    
    public static Game myGame;
    
    // Rectangle representation of the tetris grid
    private Rectangle[][] teronimos;

    /**
     * Launches the GUI window.
     *
     * @param args
     * @param width
     * @param height
     * @param autoFall
     */
    public void init(String[] args, int width, int height, int autoFall) {
        this.autoFall = autoFall;
        myGame = new Game(width, height);
        this.width = width;
        this.height = height;
        System.out.println("Game launched");
        System.out.println("Game started");
        launch(args);

    }

    /**
     * Starts the JavaFX application window, creating the panes for view
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Tetris V01");

        // The root pane, has a basic layout allowing other panes to go on top
        BorderPane root = new BorderPane();

        // Sets panes on top of the root pane
        root.setRight(addScorePane(null));
        root.setLeft(addNextBlockPane());
        root.setCenter(addTetrisPane());

        // Creates a scene, which is what is actually displayed. Uses the root pane.
        Scene scene = new Scene(root);

        setupKeyboard(scene);

        // Sets the scene, and shows it to the user.
        primaryStage.setScene(scene);
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
       this.teronimos = new Rectangle[MainViewFX.height][MainViewFX.width];
        
        for(Rectangle[] rectLine : this.teronimos){
            for(Rectangle rect : rectLine){
                rect = new Rectangle(75, 75);
                rect.setFill(Color.web(this.tetronimoDefaultColor));
                tetrisPane.getChildren().add(rect);
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
        System.out.println("Game tick");
        myGame.tick(true, isDropdown, move);
    }

    /**
     * Gets the last time is system milliseconds that the block was moved down.
     * @return 
     */
    private long getLastDownTime() {
        return this.lastFall;
    }

    /**
     * Sets the last system time that the block was moved down, either from user input or the auto timer.
     * @param time 
     */
    private void setLastDownTime(long time) {
        this.lastFall = time;
    }

    /**
     * Returns the delay between the block falling currently.
     * @return delay
     * @deprecated 
     */
    private int getDownDelay() {
        return this.autoFall;
    }

    /**
     * Listens to live keyboard input, in order to move the active tetronimo.
     * Original code:
     * https://stackoverflow.com/questions/37472273/detect-single-key-press-in-javafx
     * Original author: Lisek from stackoverflow
     */
    private void setupKeyboard(Scene scene) {

        scene.setOnKeyPressed(event -> {
            System.out.println(myGame.toString());
            String keyName = event.getCode().getName();
            System.out.println(event.getCode().getName());

            if (keyName.equals("S")) {
                System.out.println("mov down");
                myGame.tick(true, false, 0);
                setLastDownTime(System.currentTimeMillis());
            }

            if (keyName.equals("A")) {
                System.out.println("mov left");
                myGame.tick(true, true, 1);
            }

            if (keyName.equals("D")) {
                System.out.println("mov right");
                myGame.tick(true, true, 2);
            }

            if (keyName.equals("Q")) {
                System.out.println("rot left");
                myGame.tick(true, true, 3);
            }

            if (keyName.equals("E")) {
                System.out.println("rot right");
                myGame.tick(true, true, 4);
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                ae -> drop()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Calls the tick method of the Game object to move down the current block.
     */
    public void drop() {
        myGame.tick(true, false, 0);
        setLastDownTime(System.currentTimeMillis());
    }
    
    public void updateRectangles(){
        
    }
}
