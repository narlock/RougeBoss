package com.narlock.rogue.controller;

import com.narlock.rogue.model.event.RBEvent;
import com.narlock.rogue.model.result.RBResult;
import com.narlock.rogue.service.RogueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RogueController {

  @Autowired private RogueService rogueService;

  @GetMapping("/rb")
  public ResponseEntity<String> ping() {
    log.info("Ping request received. Syncing Boss with Graphics Panel");
    rogueService.ping();
    return ResponseEntity.ok("Success");
  }

  @PostMapping("/rb")
  public ResponseEntity<RBResult> rb(@RequestBody RBEvent event) {
    log.info("Received event {}", event);
    RBResult result = rogueService.sendMessageToEventQueue(event);
    return ResponseEntity.ok(result);
  }
}
