/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Logging utility to help debug.
 *
 * @author kell-gigabyte
 */
public class Kaizen_85 {

    private static final ArrayList Kaizen85 = new ArrayList(); // See the game Event[0].

    private static String pathToLog;

    /**
     * Sets the event for Kaizen-85, the name for the logger. Please do not call
     * outside of initialization.
     *
     * @param logPath
     */
    public static void setLogPath(String logPath) {
        pathToLog = logPath;
    }

    public static String getLogPath() {
        return pathToLog;
    }

    public static boolean checkLogPath() {
        Kaizen_85.newEvent("Data check for init dialog, path folder is " + pathToLog);
        FileWriter fw;
        try {
            fw = new FileWriter(pathToLog);
            PrintWriter printer = new PrintWriter(fw);
            printer.println("Print Test Please Ignore");
            printer.close();
            fw.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Call this whenever you are accessing a method or doing something new,
     * this acts as a simple logger. Kaizen-85 will record all event given to
     * it.
     *
     * @param newEvent
     */
    public static void newEvent(String newEvent) {
        System.out.println("Event has been incremented. Current event is [" + Kaizen85.size() + "]");
        Kaizen85.add(newEvent);
    }

    /**
     * Call when program is failing, Kaizen-85 Will print it out into the
     * submitted folder, to help debug the program.
     */
    public static void panic() {
        try {
            //System.out.println(pathToLog);
            FileWriter fw = new FileWriter(pathToLog + "/Kaizen85Log.txt");
            PrintWriter printer = new PrintWriter(fw);
            ListIterator iter = Kaizen85.listIterator();
            while (iter.hasNext()) {
                String s = (String) iter.next();
                printer.println(s);
            }
            printer.close();
            fw.close();
        } catch (FileNotFoundException e) {
            System.err.println("Inputted file not found while trying to panic write logs,   " + e);
            printToTerminal();
        } catch (IOException e) {
            System.err.println("General IO exception while panic log writing   " + e);
            printToTerminal();
        }
    }

    private static void printToTerminal() {
        ListIterator printIter = Kaizen85.listIterator();
        while (printIter.hasNext()) {
            String s = (String) printIter.next();
            System.out.println(s);
        }
    }

}
