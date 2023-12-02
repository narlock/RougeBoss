package com.narlock.rogue;

import com.narlock.rogue.gui.Window;
import com.narlock.rogue.model.boss.RBBoss;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RB {

	public static Window window;

	public static void main(String[] args) {
		// Run headless spring boot app
		new SpringApplicationBuilder(RB.class).headless(false).run(args);

		// Create Swing frame
		window = new Window();

		RBBoss boss = RBBoss.random(1);
		System.out.println(boss.toString());
	}

}
