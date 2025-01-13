package com.narlock.RogueV2.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window extends JFrame {

    public GraphicsPanel gp;
    private Point initialClick;

    public Window() {
        setTitle("Rogue Boss V2");

        // The window is 524x424, where the screen size is 500x400 with room for a 12px border
        setSize(524, 424);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        // Add graphics
        gp = new GraphicsPanel();
        add(gp);

        // Enable dragging the window
        enableWindowDragging();

        // Enable right-click close prompt
        enableRightClickToClose();

        setVisible(true);
    }

    private void enableWindowDragging() {
        // Add a MouseAdapter to track mouse movements
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Store the initial position of the mouse click
                initialClick = e.getPoint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Get the current mouse location on the screen
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();

                // Calculate the new position of the window
                int newX = x - initialClick.x;
                int newY = y - initialClick.y;

                // Set the location of the JFrame
                setLocation(newX, newY);
            }
        });
    }

    private void enableRightClickToClose() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
                    // Show a confirmation dialog when right-click is detected
                    int result = JOptionPane.showConfirmDialog(
                            Window.this,
                            "Do you want to close the application?",
                            "Close Application",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    // Close the application if the user selects "Yes"
                    if (result == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });
    }
}
