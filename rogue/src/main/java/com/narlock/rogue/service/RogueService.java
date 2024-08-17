package com.narlock.rogue.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.narlock.rogue.RB;
import com.narlock.rogue.model.boss.RBBoss;
import com.narlock.rogue.model.event.RBEvent;
import com.narlock.rogue.model.result.RBData;
import com.narlock.rogue.model.result.RBResult;
import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RogueService {

  public static RBBoss boss;

  @Autowired private ObjectMapper objectMapper;

  @PostConstruct
  public void init() {
    loadBossFromJsonFile();
    log.info("Boss loaded {}", boss);
  }

  private void loadBossFromJsonFile() {
    try {
      String filePath = getBossFilePath();
      File file = new File(filePath);

      if (!file.exists()) {
        boss = RBBoss.random(1); // Creating a random boss with level 1
        saveBossToJsonFile(); // Saving the randomly generated boss to file
      } else {
        boss = objectMapper.readValue(file, RBBoss.class);
      }
    } catch (IOException e) {
      e.printStackTrace();
      // Handle the exception as needed
    }
  }

  public void saveBossToJsonFile() {
    try {
      String filePath = getBossFilePath();
      String bossJson = objectMapper.writeValueAsString(boss);
      Files.write(Paths.get(filePath), bossJson.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
      // Handle the exception as needed
    }
  }

  private String getBossFilePath() {
    String userHome = System.getProperty("user.home");
    return userHome
        + File.separator
        + "Documents"
        + File.separator
        + "RogueBoss"
        + File.separator
        + "boss.json";
  }

  public void pingWithAnimation() {
    sendMessageToEventQueue(RBEvent.PING);
  }

  public RBResult sendMessageToEventQueue(RBEvent event) {
    // Process the event
    RBResult result = processEvent(event);

    // Draw the event
    RB.window.gp.event(event, result);

    // Check if Boss has been slain
    if (result.isSlain()) {
      boss = RBBoss.random(result.getBoss().getLevel() + 1);
      saveBossToJsonFile();
      sendMessageToEventQueue(RBEvent.NEW_BOSS);
    }

    // Return result
    return result;
  }

  protected RBResult processEvent(RBEvent event) {
    String note = "";

    // Use attack on the boss
    int attackPower = getAttackPower(event);
    if (event.getId().equalsIgnoreCase("0")) {
      attackPower = 0;
    }

    // Roll for critical hit
    if (roll(7)) {
      note += "[Critical hit!]\n";
      attackPower *= 2;
    }

    // Roll for hit chance
    if (!roll(95)) {
      note = event.getName() + "'s\nattack missed!";
    } else {
      // Apply attack to boss!
      note += event.getName() + "\ndealt " + attackPower + " damage.";
      boss.setHealth(boss.getHealth() - attackPower);

      // Update damage dealt for user if they exist on record
      boolean hasUserDoneDamage = false;
      for (RBData data : boss.getDamageList()) {
        if (data.getId().equalsIgnoreCase(event.getId())) {
          data.setDamage(data.getDamage() + attackPower);
          hasUserDoneDamage = true;
        }
      }

      // Add new user to damage record if they didn't exist
      if (!hasUserDoneDamage) {
        RBData data = RBData.builder().id(event.getId()).damage(attackPower).build();
        boss.getDamageList().add(data);
      }

      // Save new damage record
      saveBossToJsonFile();
    }

    // determine slain status
    boolean slain = false;
    if (boss.getHealth() <= 0) {
      slain = true;
      boss.setHealth(0);
    }

    return RBResult.builder().note(note).slain(slain).boss(boss).build();
  }

  protected int getAttackPower(RBEvent event) {
    if (event.getId().equalsIgnoreCase("TEST")) {
      return 0;
    }

    // Get possible base attack power lower and upper bound
    int baseAttackLowerBound = getLevel(event.getExp()) * 2;
    int baseAttackUpperBound = getLevel(event.getExp()) * 4;

    // Roll random attack value between that
    Random random = new Random();
    int baseAttack =
        random.nextInt(baseAttackUpperBound - baseAttackLowerBound + 1) + baseAttackLowerBound;

    // Apply effectiveness
    double effectiveMultiplier = event.getType().getEffectivenessAgainst(boss.getType());
    baseAttack *= effectiveMultiplier;

    return baseAttack;
  }

  protected int getLevel(int exp) {
    double b = 0.317;
    int level = exp < 9 ? 1 : (int) Math.pow(exp, b);

    log.info("Level of attacker: {}", level);
    return level;
  }

  public static boolean roll(int chance) {
    Random random = new Random();
    int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100

    return randomNumber <= chance;
  }
}
