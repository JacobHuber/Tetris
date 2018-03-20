package GUI;

import java.io.File;

import Game_Main.SaverLoader;
import Game_Main.Debug.Kaizen_85;

import java.awt.Dimension;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

/**
 * This class is meant to be called in order to create a dialogue window, which
 * has several text boxes to get info from the user.
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

    private void setLogFolder(File s) {
        Kaizen_85.setLogPath(s);
    }

    private TextField HeightStrText = new TextField();
    private TextField WidthStrText = new TextField();

    private TextField AutoFallStrText = new TextField();
    private Slider AutoFallSlider = new Slider();

    private boolean isDebug = false;

    private Node confirmButton;

    private Dimension tetrisDimensions;

    private int autoFall = 0;

    private File logFile;

    private File scoreFile;

    private void createStage() {         // creates the GUI for the popup
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Tetris!");
        //dialog.setHeaderText("Hello, I'd just like you to let me know about your LED strip.");

        ButtonType confirmButtonType = new ButtonType("Enter", ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType);
        dialog.getDialogPane().setStyle("-fx-background-color: #" + this.hexGrey1 + ";");

        ToggleButton DebugBtn = new ToggleButton("Debug mode");

        Button LogBtn = new Button("Log Folder");
        Button LoadScoreBtn = new Button("High Score Folder");

        DebugBtn.setOnAction((ActionEvent event) -> {
            this.isDebug = !this.isDebug;
            checkData();
        });

        LogBtn.setOnAction((ActionEvent event) -> {
            FileChooser saveChooser = new FileChooser();
            saveChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File selectedFolder = saveChooser.showSaveDialog(dialog.getOwner());
            if (selectedFolder == null) {
                AlertBox alert = new AlertBox(new Dimension(400, 100), "Folder Error", "Error selecting folder. Try again.");
                Kaizen_85.newEvent("A folder was selected as null");
                alert.display();
            } else {
                this.logFile = selectedFolder;
                checkData();
            }
        });

        LoadScoreBtn.setOnAction((ActionEvent event) -> {
            FileChooser saveChooser = new FileChooser();
            saveChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File selectedFolder = saveChooser.showSaveDialog(dialog.getOwner());
            if (selectedFolder == null) {
                AlertBox alert = new AlertBox(new Dimension(400, 100), "Folder Error", "Error selecting folder. Try again.");
                Kaizen_85.newEvent("A folder was selected as null");
                alert.display();
            } else {
                this.scoreFile = selectedFolder;
                checkData();
            }
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

        this.AutoFallStrText.setPromptText("Fall Delay");
        this.AutoFallStrText.setMaxWidth(120);
        this.AutoFallStrText.setText("" + this.autoFall);

        this.AutoFallSlider.setMin(0);
        this.AutoFallSlider.setMax(3000);
        this.AutoFallSlider.setValue(this.autoFall);

        grid.add(new Label("Length and width of the Tetris Grid:"), 0, 0);
        grid.add(this.HeightStrText, 1, 0);
        grid.add(this.WidthStrText, 2, 0);
        grid.add(new Label("Beginning Drop speed:"), 0, 1);
        grid.add(this.AutoFallStrText, 1, 1);
        grid.add(this.AutoFallSlider, 2, 1);
        grid.add(new Label("Please choose a file for high scores:"), 0, 3);
        grid.add(LoadScoreBtn, 1, 3);
        grid.add(new Label("Please choose a file for the logs:"), 0, 4);
        grid.add(LogBtn, 1, 4);
        grid.add(DebugBtn, 0, 5);
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
        this.AutoFallSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateText();
            checkData();
        });
        this.AutoFallStrText.textProperty().addListener((observable, oldValue, newValue) -> {
            updateSlider();
            checkData();
        });
        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> HeightStrText.requestFocus());
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
            if ((int) this.AutoFallSlider.getValue() == this.autoFall) {
                this.autoFall = Integer.parseInt(this.AutoFallStrText.getText());
            } else {
                this.autoFall = (int) Math.round(this.AutoFallSlider.getValue());
            }

            int height = Integer.parseInt(this.HeightStrText.getText());
            int width = Integer.parseInt(this.WidthStrText.getText());
            int autoFall = (int) this.AutoFallSlider.getValue();
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
        SaverLoader.setHighScoreFolder(this.scoreFile);
        Kaizen_85.setLogPath(this.logFile);
        //this.confirmButton.setDisable(!(SaverLoader.checkScorePath() && Kaizen_85.checkLogPath()));
    }

    public Dimension getTetrisGridDimensions() {
        return this.tetrisDimensions;
    }

    public int getAutoFall() {
        return this.autoFall;
    }

    private void updateSlider() { // updates sliders from text fields
        //Kaizen_85.newEvent("Values dupated from text flields to sliders.");
        int red, green, blue;
        if (this.AutoFallStrText.getText().equals("")) {
            red = 0;
        } else {
            try{
            red = Integer.parseInt(this.AutoFallStrText.getText());
            }catch(NumberFormatException e){
                red = -1;
            }
        }

        if (red > 3000) {
            red = 3000;
        } else if (red < 0) {
            red = 0;
        }

        this.AutoFallSlider.setValue(red);
    }

    private void updateText() { // updates text fields from sliders
        //Kaizen_85.newEvent("Values dupated from sliders to text fields.");
        int red = (int) Math.round(this.AutoFallSlider.getValue());
        if (red > 3000) {
            red = 3000;
        } else if (red < 0) {
            red = 0;
        }

        String r = "" + red;

        this.AutoFallStrText.setText(r);

    }

}
