package GUI;

import Blocks.Block;
import Blocks.TetrominoView;
import Game_Main.Debug.Kaizen_85;
import Game_Main.Game;
import Game_Main.SaverLoader;
import java.awt.Dimension;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
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
    public final String tetrominoDefaultColor = "000";
    private final String tetrominoBackgroundColor = "0F0F0F";

    // Width and height of tetris grid
    private static int width, height;

    // Main game object
    public static Game myGame;

    // Score counting stuff
    private static int lastKnownScore = 0;
    private Label scoreLabel;

    // Main Scene object
    private Scene mainScene;

    // Rectangle representation of the tetris grid
    private Rectangle[][] tetrominos;
    private final int RECTANGLE_SIZE = 16;

    private static long autoFall;
    private static long fallChange;
    private final boolean DIFFICULTY_INCREASE = true;
    
    private boolean hasEnded = false;

    /**
     * Launches the GUI window.
     *
     * @param args
     */
    public void init(String[] args) { //, int width, int height, int autoFall) {
        launch(args);

    }

    /**
     * Starts the JavaFX application window, creating the panes for view
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        try{
        // Creates and runs the initialization window
        InitPopup init = new InitPopup();
        init.run();

        //System.out.println("Width: " + init.getTetrisGridDimensions().width + "     Height: " + init.getTetrisGridDimensions().height + "        Fall TImer: " + init.getAutoFall());
        // Gets values from the initialization window
        MainViewFX.height = init.getTetrisGridDimensions().height;
        MainViewFX.width = init.getTetrisGridDimensions().width;
        MainViewFX.myGame = new Game(init.getTetrisGridDimensions().width, init.getTetrisGridDimensions().height, this);
        MainViewFX.autoFall = init.getAutoFall();
        }catch(Exception e){
            System.err.println("Init Failure");
            Platform.exit();
        }
        // Task to update the GUI and also cause the block to fall down every [MainViewFX.autoFall] Milliseconds.
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                int i = 0;
                while (true) {
                    final int finalI = i;
                    Platform.runLater(() -> {
                        keyboardInput("S");
                        updateRectangles();
                    });
                    i++;
                    Thread.sleep(MainViewFX.autoFall);
                    Kaizen_85.newEvent("Drop speed for step: " + MainViewFX.autoFall);
                    System.out.println(MainViewFX.autoFall);
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
        //root.setRight(addHighScorePane());        //TODO
        root.setLeft(addNextBlockPane());
        root.setCenter(addTetrisPane());
        root.setTop(addScorePane());

        // Creates a scene, which is what is actually displayed. Uses the root pane.
        mainScene = new Scene(root);

        //setupKeyboard(mainScene);
        KeyboardHandler keyPressed = new KeyboardHandler(this);
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
        tetrisPane.setStyle("-fx-background-color: #" + this.tetrominoBackgroundColor + ";");

        // Sets the spacing for the Rectangle objects within
        tetrisPane.setPadding(new Insets(5, 10, 5, 10));
        tetrisPane.setVgap(4);
        tetrisPane.setHgap(4);
        tetrisPane.setPrefWrapLength(MainViewFX.width * (this.RECTANGLE_SIZE + 4)); // preferred width allows for two columns

        //System.out.println(this.height + " " + this.width);
        this.tetrominos = new Rectangle[MainViewFX.height][MainViewFX.width];

        // Generates the Rectangle Matrix with default colors
        for (int outer = 0; outer < this.tetrominos.length; outer++) {
            for (int inner = 0; inner < this.tetrominos[outer].length; inner++) {
                Rectangle rect = new Rectangle(this.RECTANGLE_SIZE, this.RECTANGLE_SIZE);
                rect.setFill(Color.web(this.tetrominoDefaultColor));
                tetrisPane.getChildren().add(rect);
                this.tetrominos[outer][inner] = rect;
            }
        }

        return tetrisPane;
    }

    /**
     * Generates a pane for displaying the next 3 tetrominos
     *
     * @return Vbox pane
     */
    private VBox addNextBlockPane() {
        return new VBox();
    }

    private HBox addScorePane() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #" + this.hexDelayBox + ";");

        this.scoreLabel = new Label("0");
        this.scoreLabel.setMinWidth(100);
        hbox.getChildren().add(this.scoreLabel);

        Button SaveBtn = new Button("Save Score");
        SaveBtn.setOnAction((ActionEvent event) -> {
            // Update scores before saving
            MainViewFX.lastKnownScore = MainViewFX.myGame.getScore();
            SaverLoader.newScore(MainViewFX.lastKnownScore);

            // Saves the scores, prints true/false to show success/failure.
            System.out.println(SaverLoader.SaveScores());
        });

        hbox.getChildren().add(SaveBtn);

        return hbox;
    }

    /**
     * Generates a pane with a scoreboard for high scores, displaying top 10,
     * and current score. Can either be given null, for no saved high scores, or
     * given a file location as a string to load scores from.
     *
     * @return Flowpane pane
     */
    private FlowPane addHighScorePane() {
        boolean success = SaverLoader.LoadScores();
        if (success) {
            System.out.println(SaverLoader.getScoreList());

        } else {
            System.err.println("Fail loading score file");
        }
        return new FlowPane();
    }

    public void updateRectangles() {

        if (MainViewFX.myGame.getGameRunning()) {
            checkScore();
        }else{
            if(!hasEnded){
                AlertBox endGame = new AlertBox(new Dimension(400,100),"Game Over", "Game Over!");
                endGame.display();
                hasEnded = true;
            }
        }

    }

    /**
     * Called whenever a new game is created, if previous game is detected then
     * save the score for that game.
     */
    private void newGame() {

    }

    public Rectangle[][] getTetrominos() {
        return this.tetrominos;
    }

    /**
     * Checks if score has changed between ticks.
     */
    private void checkScore() {
        if (MainViewFX.lastKnownScore != MainViewFX.myGame.getScore()) {
            MainViewFX.lastKnownScore = MainViewFX.myGame.getScore();
            if (DIFFICULTY_INCREASE) {
                MainViewFX.fallChange = MainViewFX.autoFall / 3;
                MainViewFX.autoFall -= MainViewFX.fallChange;
            }

            this.scoreLabel.setText("" + MainViewFX.myGame.getScore());
        }
    }

    public void clearScreen() {
        // Sets every block space to empty (tetrominoDefaultColor)
        for (Rectangle[] rectArr : this.tetrominos) {
            for (Rectangle rect : rectArr) {
                if (!rect.getFill().toString().equals(("0x" + this.tetrominoDefaultColor + "FF").toLowerCase())) {
                    rect.setFill(Color.web(this.tetrominoDefaultColor));
                }
            }
        }

        // Add any Block colors to the Rectangle Array
        for (Block block : myGame.getArrayBlocks()) {
            if (block != null) {
                if (this.tetrominos[block.getPositionY()][block.getPositionX()].getFill() != Color.web(this.tetrominoDefaultColor)) {
                    // Error printout broken, do not uncomment, it'll print even if nothing is wrong
                    // System.err.println("Block overlap detected! " + block.getPositionX() + "X, " + block.getPositionY() + "Y");
                }
                this.tetrominos[block.getPositionY()][block.getPositionX()].setFill(block.getColor());
            }
        }
    }

    public void keyboardInput(String keyName) {
        myGame.tick(keyName);
        updateRectangles();
    }
}
