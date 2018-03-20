package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 
 * @author kell-gigabyte
 */
public class AlertBox {

    JFrame alertWindow;

    /**
     * Generates a GUI window to display an exception to the user. Use java java Swing Dimension object
     * to set width and height (in pixels) for the box.
     * @param obj
     * @param title
     * @param message 
     */
    public AlertBox(Dimension obj, String title, String message) {
        alertWindow = new JFrame();
        alertWindow.setTitle(title);
        alertWindow.setSize(obj);
        alertWindow.setPreferredSize(obj);
        alertWindow.setLayout(new BorderLayout());
        alertWindow.setLocationRelativeTo(null);
        JTextArea lblMessage = new JTextArea(message);
        lblMessage.setLineWrap(true);
        lblMessage.setEditable(false);
        lblMessage.setWrapStyleWord(true);
        lblMessage.setFont(new Font(lblMessage.getFont().getName(), Font.BOLD, 24));
        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener((ActionEvent e) -> {
            alertWindow.setVisible(false);
            alertWindow.dispose();
        });
        alertWindow.add(btnOk, BorderLayout.SOUTH);
        alertWindow.add(lblMessage, BorderLayout.CENTER);
    }

    public void display() {
        alertWindow.setVisible(true);
    }
}
