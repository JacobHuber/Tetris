
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kell-gigabyte
 */
public class SaverLoader {

    private static String highScoreFolder;

    private static ArrayList<Integer> highScoreList = new ArrayList<>();

    public static void setHighScoreFolder(String folder) {
        highScoreFolder = folder;
    }

    public static String getHighScoreFolder() {
        return highScoreFolder;
    }

    public static boolean LoadScores() {
        if (highScoreFolder == null) {
            AlertBox alert = new AlertBox(new Dimension(400, 100), "Folder Error", "Error selecting folder. Try again.");
            alert.display();
            return false;
        } else {
            try {
                Scanner file = new Scanner(new File(highScoreFolder));
                String temp;
                while (file.hasNext()) {
                    temp = file.nextLine();
                    if(temp.equals("Print Test Please Ignore")){
                        // Do nothing, it is the test print
                    }else{
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
        FileWriter fw;
        try {
            fw = new FileWriter(highScoreFolder);
            PrintWriter printer = new PrintWriter(fw);
            for (Integer score : highScoreList) {
                printer.println("" + score);
            }
            printer.close();
            fw.close();
            AlertBox alert = new AlertBox(new Dimension(200, 100), "Success", "Success saving settings.");
            alert.display();
            return true;
        } catch (IOException ex) {
            AlertBox alert = new AlertBox(new Dimension(400, 100), "Folder Error", "Error writing to folder. Try again.");
            alert.display();
            return false;
        }
    }

    public static boolean checkScorePath() {
         FileWriter fw;
        try {
            fw = new FileWriter(highScoreFolder);
            PrintWriter printer = new PrintWriter(fw);
            printer.println("Print Test Please Ignore");
            printer.close();
            fw.close();
            AlertBox alert = new AlertBox(new Dimension(200, 100), "Success", "Success saving settings.");
            alert.display();
            return true;
        } catch (IOException ex) {
            AlertBox alert = new AlertBox(new Dimension(400, 100), "Folder Error", "Error writing to folder. Try again.");
            alert.display();
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
