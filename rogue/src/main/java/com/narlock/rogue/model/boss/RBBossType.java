package com.narlock.rogue.model.boss;

import java.util.Random;

public enum RBBossType {
  SLIME("Slime"),
  ZOMBIE("Zombie"),
  DRAGON("Dragon"),
  WOLF("Wolf"),
  OGRE("Ogre"),
  GHOST("Ghost"),
  UNICORN("Unicorn"),
  LION("Lion");

  RBBossType(String name) {}

  private static final Random RANDOM = new Random();

  public static RBBossType random() {
    return values()[RANDOM.nextInt(values().length)];
  }
}
