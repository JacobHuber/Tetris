package Game_Main;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import GUI.AlertBox;

/**
 * Loads and saves high scores. Note: Need to ensure it doesn't overwrite old
 * scores
 *
 * @author T03-2
 */
public final class SaverLoader {

    // The folder that highscores are saved in.
    private static File highScoreFolder;

    // An ArrayList that will be used to display highscores in game.
    private static ArrayList<Integer> highScoreList = new ArrayList<>();
    private static int[] highScoreListSorted;

    /**
     * Will set the highscore folder to the specified folder.
     * @param folder
     */
    public static void setHighScoreFolder(File folder) {
        if (folder != null) {
            System.out.println("High Score folder: " + folder.getAbsolutePath());
            highScoreFolder = folder;
        }
    }

    public static void setHighScoreSortedArray(int[] shl) {
        highScoreListSorted = shl;
    }

    /**
     * Returns the highscore folder.
     * @return File
     */
    public static File getHighScoreFolder() {
        return highScoreFolder;
    }

    /**
     * Fills ArrayList highScoreList with all the highscores in the highscore text file.
     * @return boolean
     */
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
                int[] shl = new int[highScoreList.size()];

                int i = 0;
                for (Integer score : highScoreList) {
                    shl[i] = score;
                    i++;
                }
                Arrays.sort(shl);
                setHighScoreSortedArray(shl);
                return true;
            } catch (FileNotFoundException ex) {
                AlertBox alert = new AlertBox(new Dimension(400, 100), "Folder Error", "Error finding folder. Try again.");
                alert.display();
                return false;
            }
        }
    }

    /**
     * Writes to the highscore folder with a new score. Returns success or not.
     * @return boolean
     */
    public static boolean SaveScore(int score) {
        System.out.println("Saving scores.");
        FileWriter fw;
        try {
            fw = new FileWriter(highScoreFolder, true);
            PrintWriter printer = new PrintWriter(fw, true);
            printer.println("" + score);
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
     * @return canWrite
     */
    public static boolean checkScorePath() {
        FileWriter fw;
        try {
            fw = new FileWriter(highScoreFolder, true);
            fw.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Gets the highscore arraylist.
     * @return ArrayList<Integer>
     */
    public static ArrayList<Integer> getScoreList() {
        return highScoreList;
    }

    /**
     * Gets the sorted highscore array.
     * @return int
     */
    public static int[] getSortedScoreList() {
        return highScoreListSorted;
    }

}
