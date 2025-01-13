package com.narlock.RogueV2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RogueBossController {

  @GetMapping("/hello")
  public String helloWorld() {
    return "Hello world!";
  }
}
