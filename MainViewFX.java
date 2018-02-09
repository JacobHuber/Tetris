import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * For future use when we need to use a GUI.
 * @author kell-gigabyte
 */
public class MainViewFX extends Application {

    // Specific panes
    private FlowPane tetrisPane;
    private FlowPane colorPane;

    // The rectangles representing the Tetris grid
    private Rectangle[][] rectangles;

    // Pre-set color codes for easy modification
    private final String hexHbox = "4A444B";
    private final String hexButtonBox = "BA0101";
    private final String hexColorBox = "FFFFF0";
    private final String hexDelayBox = "78866B";
    private final String hexSliderBox = "90AFFF";

    /**
     * Launches the GUI window.
     * @param args 
     */
    public static void init(String[] args) {
        launch(args);
    }

    /**
    * Starts the JavaFX application window, creating the panes for view
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
        tetrisPane.setStyle("-fx-background-color: #" + this.hexColorBox + ";");
        
        // Sets the spacing for the Rectangle objects within
        tetrisPane.setPadding(new Insets(5, 10, 5, 10));
        tetrisPane.setVgap(4);
        tetrisPane.setHgap(4);
        tetrisPane.setPrefWrapLength(900); // preferred width allows for two columns
        
        
        // Temp code, for testing. Generates 11 rectangles that should autofill the tetris grid, or to test it anyways.
            Rectangle rect1 = new Rectangle(75, 75);
            rect1.setFill(Color.CORNFLOWERBLUE);
            Rectangle rect2 = new Rectangle(75, 75);
            rect2.setFill(Color.GREENYELLOW);
            // Creates new rectangle, 75 by 75 pixel size.
            Rectangle rect3 = new Rectangle(75, 75);
            // Sets the rectangle a certain color.
            rect3.setFill(Color.DARKVIOLET);
            Rectangle rect4 = new Rectangle(75, 75);
            rect4.setFill(Color.BURLYWOOD);
            Rectangle rect5 = new Rectangle(75, 75);
            rect5.setFill(Color.WHITE);
            Rectangle rect6 = new Rectangle(75, 75);
            rect6.setFill(Color.PEACHPUFF);
            Rectangle rect7 = new Rectangle(75, 75);
            rect7.setFill(Color.BLUE);
            Rectangle rect8 = new Rectangle(75, 75);
            rect8.setFill(Color.CADETBLUE);
            Rectangle rect9 = new Rectangle(75, 75);
            rect9.setFill(Color.SILVER);
            Rectangle rect10 = new Rectangle(75, 75);
            rect10.setFill(Color.DARKRED);
            Rectangle rect11 = new Rectangle(75, 75);
            rect11.setFill(Color.GRAY);
            
            tetrisPane.getChildren().addAll(rect1, rect2, rect3, rect4, rect5, rect6, rect7, rect8, rect9, rect10, rect11);
            
        return tetrisPane;
    }
    
    /**
     *  Generates a pane for displaying the next 3 tetronimos 
     * @return Vbox pane 
     */
    private VBox addNextBlockPane(){
        return new VBox();
    }
    
    /**
     * Generates a pane with a scoreboard for high scores, displaying top 10, and current score.
     * Can either be given null, for no saved high scores, or given a file location as a string to load scores from.
     * @return Flowpane pane
     */
    private FlowPane addScorePane(String highScoreFile){
        if(highScoreFile != null){
            
        }
        return new FlowPane();
    }

}
