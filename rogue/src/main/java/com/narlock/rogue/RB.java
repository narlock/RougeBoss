package com.narlock.rogue;

import com.narlock.rogue.gui.Window;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RB {

  public static Window window;

  public static void main(String[] args) {
    // Run headless spring boot app
    new SpringApplicationBuilder(RB.class).headless(false).run(args);

    // Create Swing frame
    window = new Window();

    // Create a RestTemplate instance
    RestTemplate restTemplate = new RestTemplate();

    // Make a GET request to the endpoint
    String url = "http://localhost:8081/rban";
    String response = restTemplate.getForObject(url, String.class);

    // Process the response
    System.out.println("Response from /rban endpoint: " + response);
  }
}
