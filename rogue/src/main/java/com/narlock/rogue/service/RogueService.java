package com.narlock.rogue.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.narlock.rogue.RB;
import com.narlock.rogue.model.boss.RBBoss;
import com.narlock.rogue.model.event.RBEvent;
import com.narlock.rogue.model.result.RBResult;
import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RogueService {

  private RBBoss boss;

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

  public RBResult sendMessageToEventQueue(RBEvent event) {
    // Process the event
    RBResult result = processEvent(event);

    // Draw the event
    RB.window.gp.event(event, result);

    // Return result
    return result;
  }

  protected RBResult processEvent(RBEvent event) {
    // Use attack on the boss
    int attackPower = getAttackPower(event);
    return null;
  }

  protected int getAttackPower(RBEvent event) {
    return 1;
  }
}
