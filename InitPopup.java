
import java.io.File;
import java.awt.Dimension;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

/**
 * This class is meant to be called in order to create a dialogue window, which
 * has several text boxes to get info from the user, like the size of the LED
 * strip, and if it is standard RGB or has a extra led, like the RBGW strips.
 * Should also detect if it cannot properly format the data entered, and ask for
 * it again, with the incorrect text boxes being cleared/highlighted.
 *
 *
 * @author kell-gigabyte
 */
public class InitPopup {

    private boolean isComplete = false;

    private final String hexGrey1 = "4A444B";
    private final String hexRed = "BA0101";
    private final String hexOffWhite = "FFFFF0";
    private final String hexGrey2 = "78866B";
    private final String hexBlue = "90AFFF";

    public InitPopup() {
    }

    public void run() {
        createStage();
    }

    protected boolean isComplete() {
        return this.isComplete;
    }

    private void setLogFolder(String s) {
        Kaizen_85.setLogPath(s);
    }

    private TextField HeightStrText = new TextField();
    private TextField WidthStrText = new TextField();
    private TextField AutoFallStrText = new TextField();
    
    private Button ContinueBtn = new Button("Continue");

    private boolean isDebug = false;

    private Node confirmButton;

    private Dimension tetrisDimensions;
    
    private int autoFall;
    
    private void createStage() {         // creates the GUI for the popup
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Holonyak V1.0: PanelClear");
        //dialog.setHeaderText("Hello, I'd just like you to let me know about your LED strip.");

        ButtonType confirmButtonType = new ButtonType("Enter", ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType);
        dialog.getDialogPane().setStyle("-fx-background-color: #" + this.hexGrey1 + ";");

        String logFolderStr = "Log Folder";

        ToggleButton DebugBtn = new ToggleButton("Debug mode");

        Button LogBtn = new Button(logFolderStr);
        Button LoadScoreBtn = new Button("Load high scores from file");

        this.ContinueBtn.setDisable(true);

        LogBtn.setOnAction((ActionEvent event) -> {
            FileChooser saveChooser = new FileChooser();
            saveChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File selectedFolder = saveChooser.showSaveDialog(dialog.getOwner());
            if (selectedFolder == null) {
            } else {

                setLogFolder(selectedFolder.getAbsolutePath());
                //System.out.println(selectedFolder.getAbsolutePath());
                checkData();
            }
        });

        DebugBtn.setOnAction((ActionEvent event) -> {
            this.isDebug = !this.isDebug;
            checkData();
        });

        ContinueBtn.setOnAction((ActionEvent event) -> {
            String folder;
            String saveString;
            FileChooser saveChooser = new FileChooser();
            saveChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File selectedFolder = saveChooser.showSaveDialog(dialog.getOwner());
            if (selectedFolder == null) {
                AlertBox alert = new AlertBox(new Dimension(400, 100), "Folder Error", "Error selecting folder. Try again.");
                Kaizen_85.newEvent("A folder was selected as null");
                alert.display();
            } else {

            }
        });

        LoadScoreBtn.setOnAction((ActionEvent event) -> {
            String folder;
            String loadString;
            FileChooser loadChooser = new FileChooser();
            loadChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File selectedFolder = loadChooser.showOpenDialog(dialog.getOwner());

        });

        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: #" + this.hexBlue + ";");
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        this.HeightStrText.setPromptText("Length");
        this.HeightStrText.setMaxWidth(90);

        this.WidthStrText.setPromptText("Width");
        this.WidthStrText.setMaxWidth(90);
        this.WidthStrText.setDisable(true);

        grid.add(new Label("Length and width of the Tetris Grid:"), 0, 0);
        grid.add(this.HeightStrText, 1, 0);
        grid.add(this.WidthStrText, 2, 0);
        grid.add(new Label("Please choose a file for high scores:"), 0, 3);
        grid.add(LoadScoreBtn, 1, 3);
        grid.add(new Label("Please choose a file for the logs:"), 0, 4);
        grid.add(LogBtn, 1, 4);
        grid.add(ContinueBtn, 1, 5);
        grid.add(DebugBtn, 0, 6);
        grid.setGridLinesVisible(false);

        this.confirmButton = dialog.getDialogPane().lookupButton(confirmButtonType);
        confirmButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        this.HeightStrText.textProperty().addListener((observable, oldValue, newValue) -> {
            checkData();
        });
        this.WidthStrText.textProperty().addListener((observable, oldValue, newValue) -> {
            checkData();
        });
        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        //Platform.runLater(() -> HeightStrText.requestFocus());
        Kaizen_85.newEvent("Initialization window created, showing.");
        dialog.showAndWait();
    }

    /**
     * Ensures the user enters certain data before they can continue
     */
    private void checkData() {    // makes sure the data entered is formattable
        Kaizen_85.newEvent("Data check for init dialog, path folder is " + Kaizen_85.getLogPath() + " and the current Tetris Grid dimensions are " + HeightStrText.getText() + " high by " + WidthStrText.getText() + " Wide.");
        boolean intParsable = false;

        try {
            int height = Integer.parseInt(this.HeightStrText.getText());
            int width = Integer.parseInt(this.WidthStrText.getText());
            this.tetrisDimensions = new Dimension(height, width);
            
            intParsable = true;
        } catch (NumberFormatException e) {
            //System.err.println("Number entered on init panel is invalid, please try again.");
        }

        if (this.isDebug) {
        } else if (intParsable) {

        } else {
            this.confirmButton.setDisable(true);
        }

        this.ContinueBtn.setDisable(!(SaverLoader.checkScorePath() && Kaizen_85.checkLogPath()));
    }
    
    public Dimension getTetrisGridDimensions(){
        return this.tetrisDimensions;
    }
    
    public int getAutoFall(){
        return this.autoFall;
    }
            
}
