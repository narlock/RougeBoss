package com.narlock.rogue.gui;

import com.narlock.rogue.model.event.RBEvent;
import com.narlock.rogue.model.result.RBResult;
import java.awt.*;
import java.util.Stack;
import javax.swing.*;

public class GraphicsPanel extends JPanel implements Runnable {

  Thread graphicsThread;

  private Stack<EventPair> eventStack;
  private boolean eventInProgress;
  private EventPair currentEvent;
  int animationCounter = 0;
  private Attacker attacker;
  private boolean didAttack;

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

  public void update() {
    if(eventInProgress) {
      attacker.x += 2;
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (!eventStack.isEmpty() && !eventInProgress) {
      // Draw events
      currentEvent = eventStack.pop();
      attacker = Attacker.getAttackerByType(currentEvent.getEvent().getModel());
      eventInProgress = true;
      didAttack = false;

      // Draw the animation
      drawAnimation(g);
    } else if (eventInProgress) {
      drawAnimation(g);
    }
  }

  public void drawAnimation(Graphics g) {
    if (animationCounter < 180) {
      animationCounter++;

//      if(animationCounter < 40) {
//        attacker.
//      }

      if(animationCounter < 5 || animationCounter > 30) {
        g.drawImage(attacker.attack1, attacker.x, attacker.y, 128, 64, null);
      }
      else {
        g.drawImage(attacker.attack2, attacker.x, attacker.y, 128, 64, null);
        didAttack = true;
      }

    } else {
      animationCounter = 0;
      eventInProgress = false;
      didAttack = false;
    }

    if(didAttack) {
      g.drawString(currentEvent.getResult().getNote(), 15, 15);
    }
  }

  public void event(RBEvent event, RBResult result) {
    eventStack.push(EventPair.builder().event(event).result(result).build());
  }
}
