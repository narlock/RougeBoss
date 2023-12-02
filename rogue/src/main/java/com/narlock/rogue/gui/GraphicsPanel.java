package com.narlock.rogue.gui;

import ch.qos.logback.core.joran.sanity.Pair;
import com.narlock.rogue.model.event.RBEvent;
import com.narlock.rogue.model.result.RBResult;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.*;

public class GraphicsPanel extends JPanel implements Runnable {

  Thread graphicsThread;

  private Stack<EventPair> eventStack;
  private boolean eventInProgress;
  private EventPair currentEvent;
  int animationCounter = 0;

  public GraphicsPanel() {
    eventStack = new Stack<>();

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

    if(!eventStack.isEmpty() && !eventInProgress) {
      // Draw events
      eventInProgress = true;
      currentEvent = eventStack.pop();

      // Draw the animation
      drawAnimation(g);
    } else if(eventInProgress) {
      drawAnimation(g);
    }
  }

  public void drawAnimation(Graphics g) {
    if(animationCounter < 180) {
      animationCounter++;
      g.drawString(currentEvent.getResult().getNote(), 15, 15);
    } else {
      animationCounter = 0;
      eventInProgress = false;
    }
  }

  public void event(RBEvent event, RBResult result) {
    eventStack.push(EventPair.builder().event(event).result(result).build());
  }
}
