package GUI;

import java.io.File;

import Game_Main.SaverLoader;

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
 * @author T03-2
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

    private TextField HeightStrText = new TextField("10");
    private TextField WidthStrText = new TextField("20");
    private int gridMin = 10;
    private int gridMax = 100;

    private Label AutoFallStrText = new Label();
    private Slider AutoFallSlider = new Slider();

    private Node confirmButton;

    private Dimension tetrisDimensions;

    private int autoFallMin = 100;
    private int autoFallMax = 5000;
    private int autoFall = 1000;

    private File scoreFile;

    /**
     * Creates a new window that will create a new game with settings as specified by the user.
     */
    private void createStage() {
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Tetris!");

        ButtonType confirmButtonType = new ButtonType("Enter", ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType);
        dialog.getDialogPane().setStyle("-fx-background-color: #" + this.hexGrey1 + ";");

        Button LoadScoreBtn = new Button("High Score Folder");

        LoadScoreBtn.setOnAction((ActionEvent event) -> {
            FileChooser saveChooser = new FileChooser();
            String path = System.getProperty("user.dir");
            saveChooser.setInitialDirectory(new File(path));
            File selectedFolder = saveChooser.showSaveDialog(dialog.getOwner());
            if (selectedFolder == null) {
                AlertBox alert = new AlertBox(new Dimension(400, 100), "Folder Error", "Error selecting folder. Try again.");
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

        this.HeightStrText.setPromptText("Width");
        this.HeightStrText.setMaxWidth(90);

        this.WidthStrText.setPromptText("Height");
        this.WidthStrText.setMaxWidth(90);

        this.AutoFallStrText.setText(String.valueOf(this.autoFall));

        this.AutoFallSlider.setMin(this.autoFallMin);
        this.AutoFallSlider.setMax(this.autoFallMax);
        this.AutoFallSlider.setValue(this.autoFall);

        this.AutoFallSlider.setMajorTickUnit(200);
        this.AutoFallSlider.setMinorTickCount(1);
        this.AutoFallSlider.setSnapToTicks(true);
        this.AutoFallSlider.setShowTickMarks(true);

        grid.add(new Label("Width and Height of the Tetris Grid:"), 0, 0);
        grid.add(this.HeightStrText, 1, 0);
        grid.add(this.WidthStrText, 2, 0);
        grid.add(new Label("Beginning Drop speed:"), 0, 1);
        grid.add(this.AutoFallStrText, 1, 1);
        grid.add(this.AutoFallSlider, 2, 1);
        grid.add(new Label("Please choose a file for high scores:"), 0, 3);
        grid.add(LoadScoreBtn, 1, 3);
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
            checkData();
            this.AutoFallStrText.setText(String.valueOf((int) Math.round(this.AutoFallSlider.getValue())));
        });
        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> HeightStrText.requestFocus());
        dialog.showAndWait();
    }

    /**
     * Ensures the user enters certain data before they can continue
     */
    private void checkData() {    // makes sure the data entered is formattable
        boolean intParsable = false;

        try {
            int height = Integer.parseInt(this.HeightStrText.getText());
            int width = Integer.parseInt(this.WidthStrText.getText());
            int autoFall = (int) this.AutoFallSlider.getValue();

            if (height < 4 || width < 4) {
                throw new NumberFormatException();
            }

            this.tetrisDimensions = new Dimension(height, width);

            intParsable = true;
        } catch (NumberFormatException e) {
            intParsable = false;
            //System.err.println("Number entered on init panel is invalid, please try again.");
        }

        if (intParsable) {
            SaverLoader.setHighScoreFolder(this.scoreFile);
            if (SaverLoader.checkScorePath()) {
                this.confirmButton.setDisable(false);
                this.isComplete = true;
            }
        } else {
            this.isComplete = false;
            this.confirmButton.setDisable(true);
        }
        
    }

    public Dimension getTetrisGridDimensions() {
        return this.tetrisDimensions;
    }

    public int getAutoFall() {
        return this.autoFall;
    }


    private void updateSlider() { // updates sliders from text fields
        validateText();

        int val = Integer.valueOf(this.AutoFallStrText.getText());
        this.AutoFallSlider.setValue(val);
    }

    private void validateText() {
        String t = this.AutoFallStrText.getText();
        int updated;
        if (!(t.length() <= 4)) {
            updated = (int) Math.round(Integer.parseInt(t));
            if (updated < this.autoFallMin) {
                updated = this.autoFallMin;
            } else if (updated > this.autoFallMax) {
                updated = this.autoFallMax;
            }
        } else {
            updated = this.autoFallMax;
        }
        this.AutoFallStrText.setText(String.valueOf(updated));
    }

    private void updateText() { // updates text fields from sliders
        this.AutoFallStrText.setText(String.valueOf(this.AutoFallSlider.getValue()));
    }

}
