package com.semperos.screwdriver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Graphical notifications for important log messages
 */
public class GuiNotification {
    private static void showDialog(final String title, final String message) {
        showDialog(title, message, 1200);
    }

    private static void showDialog(final String title, final String message, final int duration) {
        Thread dialogThread = new Thread() {
            public void run() {
                JFrame frame = new JFrame("Screwdriver: " + title);
                frame.setSize(500,75);
                final JDialog dialog = new JDialog(frame, title, true);
                final JLabel label = new JLabel("<html>" + message + "</html>");
                dialog.add(label);
                dialog.pack();
                dialog.setSize(500, 75);
                dialog.validate();
                Timer timer = new Timer(duration, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                        dialog.dispose();
                    }
                });
                timer.setRepeats(false);
                timer.start();

                dialog.setVisible(true);
            }
        };
        dialogThread.start();
    }

    public static void success(final String message) {
        showDialog("Success", message);
    }

    public static void failure(final String message) {
        showDialog("FAILURE", message, 6000);
    }

}
