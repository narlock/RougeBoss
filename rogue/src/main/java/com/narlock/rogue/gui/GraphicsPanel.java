package com.narlock.rogue.gui;

import com.narlock.rogue.model.boss.RBBoss;
import com.narlock.rogue.model.boss.RBBossType;
import com.narlock.rogue.model.event.RBEvent;
import com.narlock.rogue.model.result.RBResult;
import java.awt.*;
import java.util.Stack;
import javax.swing.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GraphicsPanel extends JPanel implements Runnable {

  Thread graphicsThread;

  private Boss boss;
  int bossAnimationCounter = 0;

  private Stack<EventPair> eventStack;
  private boolean eventInProgress;
  private EventPair currentEvent;
  int animationCounter = 0;
  private Attacker attacker;
  private boolean didAttack;

  public GraphicsPanel() {
    eventStack = new Stack<>();
    this.boss = Boss.getBossByType(RBBossType.GNASHER);

    graphicsThread = new Thread(this);
    graphicsThread.start();
  }

  public void initializeBoss(RBBoss rbBoss) {
    boss = Boss.getBossByType(rbBoss.getBossType());
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
    drawBoss(g);

    if (!eventStack.isEmpty() && !eventInProgress) {
      // Draw events
      currentEvent = eventStack.pop();
      attacker = Attacker.getAttackerByType(currentEvent.getEvent().getModel());
      eventInProgress = true;
      didAttack = false;

    } else if (eventInProgress) {
      drawAnimation(g);
    }
  }

  public void drawAnimation(Graphics g) {
    if (animationCounter < 140) {
      animationCounter++;

      if (animationCounter < 5) {
        g.drawImage(attacker.walk1, attacker.x, attacker.y, 128, 64, null);
        attacker.x += 2;
      } else if (animationCounter >= 5 && animationCounter < 15) {
        g.drawImage(attacker.walk2, attacker.x, attacker.y, 128, 64, null);
        attacker.x += 2;
      } else if (animationCounter >= 15 && animationCounter < 25) {
        g.drawImage(attacker.walk1, attacker.x, attacker.y, 128, 64, null);
      } else if (animationCounter >= 25 && animationCounter < 35) {
        g.drawImage(attacker.attack1, attacker.x, attacker.y, 128, 64, null);
      } else if (animationCounter >= 35 && animationCounter < 50) {
        g.drawImage(attacker.attack2, attacker.x, attacker.y, 128, 64, null);
        didAttack = true;
      } else if (animationCounter >= 50 && animationCounter < 75) {
        g.drawImage(attacker.attack1, attacker.x, attacker.y, 128, 64, null);
      } else {
        g.drawImage(attacker.walk1, attacker.x, attacker.y, 128, 64, null);
      }

    } else {
      animationCounter = 0;
      eventInProgress = false;
      didAttack = false;
    }

    if (didAttack) {
      g.drawString(currentEvent.getResult().getNote(), 15, 15);
    }
  }

  public void drawBoss(Graphics g) {
    // Draw boss
    if (bossAnimationCounter >= 0 && bossAnimationCounter < 40) {
      // Display the first image for 20 frames (0-19 and 40-59)
      g.drawImage(boss.image1, 130, 35, null);
    } else {
      // Display the second image for 20 frames (20-39)
      g.drawImage(boss.image2, 130, 35, null);
    }

    // Increment the bossAnimationCounter by 1 for each frame
    bossAnimationCounter++;

    // Reset the counter if it exceeds the total frames for two images (60 frames)
    if (bossAnimationCounter >= 60) {
      bossAnimationCounter = 0;
    }
  }

  public void event(RBEvent event, RBResult result) {
    eventStack.push(EventPair.builder().event(event).result(result).build());
  }
}
