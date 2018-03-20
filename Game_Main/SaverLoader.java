package Game_Main;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.AlertBox;

/**
 * Loads and saves high scores. Note: Need to ensure it doesn't overwrite old
 * scores
 *
 * @author kell-gigabyte
 */
public class SaverLoader {

    private static File highScoreFolder;

    private static ArrayList<Integer> highScoreList = new ArrayList<>();

    public static void setHighScoreFolder(File folder) {
        if (folder != null) {
            System.out.println("High Score folder: " + folder.getAbsolutePath());
            highScoreFolder = folder;
        }
    }

    public static File getHighScoreFolder() {
        return highScoreFolder;
    }

    public static boolean LoadScores() {
        if (highScoreFolder == null) {
            AlertBox alert = new AlertBox(new Dimension(400, 100), "Folder Error", "Error selecting folder. Try again.");
            alert.display();
            return false;
        } else {
            try {
                Scanner file = new Scanner(highScoreFolder);
                String temp;
                while (file.hasNext()) {
                    temp = file.nextLine();
                    if (temp.equals("Print Test Please Ignore")) {
                        // Do nothing, it is the test print
                    } else {
                        highScoreList.add(Integer.parseInt(temp));
                    }
                }
                return true;
            } catch (FileNotFoundException ex) {
                AlertBox alert = new AlertBox(new Dimension(400, 100), "Folder Error", "Error finding folder. Try again.");
                alert.display();
                return false;
            }
        }
    }

    public static boolean SaveScores() {
        System.out.println("Saving scores.");
        FileWriter fw;
        try {
            fw = new FileWriter(highScoreFolder);
            PrintWriter printer = new PrintWriter(fw);
            for (Integer score : highScoreList) {
                printer.println("" + score);
            }
            printer.close();
            fw.close();
            AlertBox alert = new AlertBox(new Dimension(300, 100), "Success", "Success saving scores!");
            alert.display();
            return true;
        } catch (IOException ex) {
            AlertBox alert = new AlertBox(new Dimension(400, 100), "Folder Error", "Error writing to folder. Try again.");
            alert.display();
            return false;
        }
    }

    /**
     * Checks if file can be written to.
     *
     * @deprecated
     * @return canWrite
     */
    public static boolean checkScorePath() {
        FileWriter fw;
        try {
            fw = new FileWriter(highScoreFolder);
            PrintWriter printer = new PrintWriter(fw);
            printer.println("Print Test Please Ignore");
            printer.close();
            fw.close();
            //AlertBox alert = new AlertBox(new Dimension(200, 100), "Success", "Success saving settings.");
            //alert.display();
            return true;
        } catch (IOException ex) {
            //AlertBox alert = new AlertBox(new Dimension(400, 100), "Folder Error", "Error writing to folder. Try again.");
            //alert.display();
            return false;
        }
    }

    public static void newScore(int score) {
        highScoreList.add(score);
    }

    public static ArrayList<Integer> getScoreList() {
        return highScoreList;
    }

}
