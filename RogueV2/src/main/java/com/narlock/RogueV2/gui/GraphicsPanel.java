package com.narlock.RogueV2.gui;

import com.narlock.RogueV2.util.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicsPanel extends JPanel implements Runnable {

    Thread graphicsThread;
    BufferedImage borderImage;

    public GraphicsPanel() {
        // Load border image
        borderImage = ImageUtils.readImage("/res/border/sample.png");

        graphicsThread = new Thread(this);
        graphicsThread.start();
    }

    @Override
    public void run() {
        // This is a simple loop for thread to run app at 60 FPS
        double drawInterval = 1000000000 / 60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (graphicsThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta > 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBorder(g);
    }

    public void drawBorder(Graphics g) {
        g.drawImage(borderImage, 0, 0, null);
    }
}
