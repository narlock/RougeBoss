package com.narlock.rogue.gui;

import com.narlock.rogue.model.event.RBEvent;
import com.narlock.rogue.model.result.RBResult;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class GraphicsPanel extends JPanel implements Runnable {

  Thread graphicsThread;
  public ArrayList<String> textList;

  public GraphicsPanel() {
    textList = new ArrayList<>();
    textList.add("App Started!");

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

  public void addString(String string) {
    textList.add(string);
  }

  public void update() {}

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    int x = 15;
    int y = 15;
    for (String text : textList) {
      g.drawString(text, x, y);
      y += 15;
    }
  }

  public void event(RBEvent event, RBResult result) {
    // TODO Code to handle event
  }
}
