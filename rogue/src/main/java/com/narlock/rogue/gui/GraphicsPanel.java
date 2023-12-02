package com.narlock.rogue.gui;

import com.narlock.rogue.model.boss.RBBoss;
import com.narlock.rogue.model.event.RBEvent;
import com.narlock.rogue.model.result.RBResult;
import com.narlock.rogue.util.ImageUtils;
import com.narlock.rogue.util.ScreenUtils;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;
import javax.swing.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GraphicsPanel extends JPanel implements Runnable {

  Thread graphicsThread;
  BufferedImage titleImage;

  private Boss boss;
  private RBBoss rbBoss;
  int bossAnimationCounter = 0;
  int healthBarWidth;

  private Stack<EventPair> eventStack;
  private boolean eventInProgress;
  private EventPair currentEvent;
  int animationCounter = 0;
  private Attacker attacker;
  private boolean didAttack;

  public GraphicsPanel() {
    titleImage = ImageUtils.readImage("/res/title.png");

    eventStack = new Stack<>();
    this.boss = Boss.GNASHER;
    this.rbBoss = RBBoss.GNASHER;
    healthBarWidth = 200;

    graphicsThread = new Thread(this);
    graphicsThread.start();
  }

  public void initializeBoss(RBBoss rbBoss) {
    this.rbBoss = rbBoss;
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
    drawBossName(g);
    drawBossHealth(g);

    if (!eventStack.isEmpty() && !eventInProgress) {
      // Draw events
      currentEvent = eventStack.pop();
      attacker = Attacker.getAttackerByType(currentEvent.getEvent().getModel());
      eventInProgress = true;
      didAttack = false;
      log.info("Event from {} now in progress...", currentEvent.getEvent().getId());
    } else if (eventInProgress) {
      drawAnimation(g);
    } else {
      // NO EVENTS
      // TODO - DRAW ROGUE BOSS LOGO!
      g.drawImage(titleImage, 60, 0, null);
    }
  }

  public void drawAnimation(Graphics g) {
    if (animationCounter < 180) {
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
      g.setColor(Color.BLACK);
      int x = ScreenUtils.getXForCenterText(g, currentEvent.getResult().getNote());
      g.drawString(currentEvent.getResult().getNote(), x, 35);

      // Calculate the current health percentage
      int maxHealth = (rbBoss.getLevel() * 500);
      double healthPercentage = (double) rbBoss.getHealth() / maxHealth;
      setNewHealthBarWidth(200, healthPercentage);
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

  public void drawBossName(Graphics g) {
    int x = ScreenUtils.getXForCenterText(g, rbBoss.getName());
    g.drawString(rbBoss.getName(), x, 180);
  }

  public void drawBossHealth(Graphics g) {
    // Define the size and position of the health bar
    int barWidth = 200;
    int barHeight = 25;
    int barX = 50; // X-coordinate
    int barY = 190; // Y-coordinate

    // Set color for the health bar
    Color healthColor = Color.RED; // For example, set to green

    // Draw the health bar outline
    g.setColor(Color.BLACK);
    g.drawRect(barX, barY, barWidth, barHeight);

    // Draw the filled portion of the health bar based on the current health
    g.setColor(healthColor);
    g.fillRect(barX, barY, healthBarWidth, barHeight);
  }

  public void setNewHealthBarWidth(int barWidth, double healthPercentage) {
    this.healthBarWidth = (int) (barWidth * healthPercentage);
  }

  public void event(RBEvent event, RBResult result) {
    eventStack.push(EventPair.builder().event(event).result(result).build());
  }
}
