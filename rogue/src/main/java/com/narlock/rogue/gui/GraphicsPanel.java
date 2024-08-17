package com.narlock.rogue.gui;

import com.narlock.rogue.model.boss.RBBoss;
import com.narlock.rogue.model.event.RBEvent;
import com.narlock.rogue.model.result.RBResult;
import com.narlock.rogue.util.FontUtils;
import com.narlock.rogue.util.ImageUtils;
import com.narlock.rogue.util.ScreenUtils;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
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
  boolean updatedHealthBar;
  boolean hideSlainBoss;

  private LinkedList<EventPair> eventList;
  private LinkedList<Integer> healthBarList;
  private boolean eventInProgress;
  private EventPair currentEvent;
  int animationCounter = 0;
  private Attacker attacker;
  private boolean didAttack;

  public GraphicsPanel() {
    titleImage = ImageUtils.readImage("/res/title.png");

    eventList = new LinkedList<>();
    healthBarList = new LinkedList<>();
    this.boss = Boss.SLIME;
    this.rbBoss = RBBoss.SLIME;
    healthBarWidth = 200;

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
    if (!hideSlainBoss) {
      drawBoss(g);
      drawBossName(g);
    }
    drawBossHealth(g);

    if (!eventList.isEmpty() && !eventInProgress) {
      // Process the current event
      currentEvent = eventList.poll();
      updatedHealthBar = false;

      // set boss based on current event - ensures drawing current boss and not future boss
      this.rbBoss = currentEvent.getResult().getBoss();
      boss = Boss.getBossByTypeAndRbType(rbBoss.getBossType(), rbBoss.getType());

      // set attacker based on current event
      if (currentEvent.getEvent().equals(RBEvent.NEW_BOSS)
          || currentEvent.getEvent().equals(RBEvent.PING)) {
        attacker = Attacker.NONE;
      } else {
        attacker =
            Attacker.getAttacker(
                currentEvent.getEvent().getType(), currentEvent.getEvent().getModel());
      }

      // draw the current event now that variables are configured
      log.info("Event from {} now in progress...", currentEvent.getEvent().getId());
      eventInProgress = true;
    } else if (eventInProgress) {
      // while the event is in progress, draw the animation for the event
      drawAnimation(g);
    } else {
      // On no events, simply draw the rogue boss logo
      g.drawImage(titleImage, 60, 0, null);
    }
  }

  public void drawAnimation(Graphics g) {
    // On new boss or ping event, we want to draw "rogue boss event" to signal initialization or new
    // boss
    if (currentEvent.getEvent().equals(RBEvent.NEW_BOSS)
        || currentEvent.getEvent().equals(RBEvent.PING)) {
      // On new bosses only, we want to ensure that the health bar is max when drawn
      if (currentEvent.getEvent().equals(RBEvent.NEW_BOSS)) {
        healthBarWidth = 200;
      }

      // display rogue boss event message
      String message = "Rogue Boss Event!";
      g.setFont(FontUtils.pressStart_12);
      int x = ScreenUtils.getXForCenterText(g, message);
      g.drawString(message, x, 35);
    }

    // play the animation
    if (animationCounter < 180) {
      animationCounter++;
      if (animationCounter < 5) {
        g.drawImage(attacker.walk1, attacker.x, attacker.y, 128, 64, null);
        attacker.x += 2;
      } else if (animationCounter < 15) {
        g.drawImage(attacker.walk2, attacker.x, attacker.y, 128, 64, null);
        attacker.x += 2;
      } else if (animationCounter < 25) {
        g.drawImage(attacker.walk1, attacker.x, attacker.y, 128, 64, null);
      } else if (animationCounter < 35) {
        g.drawImage(attacker.attack1, attacker.x, attacker.y, 128, 64, null);
      } else if (animationCounter < 50) {
        g.drawImage(attacker.attack2, attacker.x, attacker.y, 128, 64, null);
        didAttack = true;
        if (currentEvent.getResult().isSlain()) {
          hideSlainBoss = true;
          // TODO drawn slain message
        }
      } else if (animationCounter < 75) {
        g.drawImage(attacker.attack1, attacker.x, attacker.y, 128, 64, null);
      } else {
        g.drawImage(attacker.walk1, attacker.x, attacker.y, 128, 64, null);
      }
    } else {
      // reset animation variables
      animationCounter = 0;
      eventInProgress = false;
      didAttack = false;
      updatedHealthBar = false;
      hideSlainBoss = false;
    }

    if (didAttack
        && !currentEvent.getEvent().equals(RBEvent.NEW_BOSS)
        && !currentEvent.getEvent().equals(RBEvent.PING)) {
      // draw message after attack is completed
      g.setColor(Color.BLACK);
      g.setFont(FontUtils.pressStart_12);
      String[] lines = currentEvent.getResult().getNote().split("\n");
      int y = 35;
      for (String line : lines) {
        int x = ScreenUtils.getXForCenterText(g, line);
        g.drawString(line, x, y);
        y += 12;
      }

      // Calculate the current health percentage
      if (!updatedHealthBar && !currentEvent.getEvent().equals(RBEvent.NEW_BOSS)) {
        if (!healthBarList.isEmpty()) {
          healthBarWidth = healthBarList.poll();
          log.info("Health bar now {}", healthBarWidth);
        }
        updatedHealthBar = true;
      }
    }
  }

  public void drawBoss(Graphics g) {
    // Draw boss
    if (bossAnimationCounter >= 0 && bossAnimationCounter < 40) {
      // Display the first image for 20 frames (0-19 and 40-59)
      g.drawImage(boss.image1, 130 + boss.x, 35 + boss.y, null);
    } else {
      // Display the second image for 20 frames (20-39)
      g.drawImage(boss.image2, 130 + boss.x, 35 + boss.y, null);
    }

    // Increment the bossAnimationCounter by 1 for each frame
    bossAnimationCounter++;

    // Reset the counter if it exceeds the total frames for two images (60 frames)
    if (bossAnimationCounter >= 60) {
      bossAnimationCounter = 0;
    }
  }

  public void drawBossName(Graphics g) {
    g.setFont(FontUtils.pressStart_12);
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

    // Draw the filled portion of the health bar based on the current health
    g.setColor(healthColor);
    g.fillRect(barX, barY, healthBarWidth, barHeight);

    // Draw the health bar outline
    g.setColor(Color.BLACK);
    g.drawRect(barX, barY, barWidth, barHeight);
  }

  public void event(RBEvent event, RBResult result) {
    // Adds the event to the event queue (event is the pair of cause 'event' and effect 'result')
    eventList.add(EventPair.builder().event(event).result(result).build());

    // Calculate the width of the boss's health bar after the event
    int maxHealth = result.getBoss().getLevel() * 50;
    int barWidthAfterAttack = (int) (200 * ((double) result.getBoss().getHealth() / maxHealth));

    // If the event is a ping, lets set the bar width to the correct width based on current boss
    // health
    if (event.equals(RBEvent.PING)) {
      healthBarWidth = barWidthAfterAttack;
    }
    // If the event is a regular event, add it to the health bar queue for change
    else if (!event.equals(RBEvent.NEW_BOSS)) {
      // Add bar update to a queue (this will correspond with the event list)
      healthBarList.add(barWidthAfterAttack);
    }
  }
}
