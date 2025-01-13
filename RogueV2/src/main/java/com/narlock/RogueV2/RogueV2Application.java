package com.narlock.RogueV2;

import com.narlock.RogueV2.gui.Window;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RogueV2Application {

  public static Window window;

  public static void main(String[] args) {
    // Run headless spring boot app
    new SpringApplicationBuilder(RogueV2Application.class).headless(false).run(args);

    // Create Swing frame
    window = new Window();
  }
}
