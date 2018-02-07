import Models.GameLogic;
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

    private GameLogic logic;

    private FlowPane tetrisPane;
    private FlowPane colorPane;

    private Rectangle[][] rectangles;

    private final String hexHbox = "4A444B";
    private final String hexButtonBox = "BA0101";
    private final String hexColorBox = "FFFFF0";
    private final String hexDelayBox = "78866B";
    private final String hexSliderBox = "90AFFF";

    public static void init(String[] args) {
        launch(args);
    }

    /**
    * Starts the JavaFX application window, creating the panes for view
    */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tetris V01");

        BorderPane root = new BorderPane();

        root.setRight(addScorePane());
        root.setLeft(addNextBlockPane());
        root.setCenter(addTetrisPane());

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
    * Generates the flowPane acting as the tetris window
    */
    private FlowPane addTetrisPane() {
        FlowPane tetrisPane = new FlowPane();
        tetrisPane.setStyle("-fx-background-color: #" + this.hexColorBox + ";");
        tetrisPane.setPadding(new Insets(5, 10, 5, 10));
        tetrisPane.setVgap(4);
        tetrisPane.setHgap(4);
        tetrisPane.setPrefWrapLength(900); // preferred width allows for two columns
        
            Rectangle rect1 = new Rectangle(75, 75);
            rect1.setFill(Color.CORNFLOWERBLUE);
            Rectangle rect2 = new Rectangle(75, 75);
            rect2.setFill(Color.GREENYELLOW);
            Rectangle rect3 = new Rectangle(75, 75);
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
    
    private VBox addNextBlockPane(){
        return new VBox();
    }
    
    private FlowPane addScorePane(){
        return new FlowPane();
    }

}
