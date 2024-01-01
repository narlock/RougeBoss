package com.narlock.rogue.model.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum RBType {
  FIRE("Fire"),
  WATER("Water"),
  EARTH("Earth"),
  PSYCHIC("PSYCHIC"),
  LIGHT("Light"),
  DARK("Dark"),
  TEST("Test");

  RBType(String name) {}

  private static final Random RANDOM = new Random();

  public static RBType random() {
    RBType[] values = values();
    RBType randomType;
    do {
      randomType = values[RANDOM.nextInt(values.length)];
    } while (randomType == TEST); // Exclude TEST
    return randomType;
  }

  // Type matchups and effectiveness
  private static final Map<RBType, Map<RBType, Double>> TYPE_EFFECTIVENESS = new HashMap<>();

  static {
    // Initialize the effectiveness map for each type
    for (RBType type : values()) {
      TYPE_EFFECTIVENESS.put(type, new HashMap<>());
    }

    // Define type matchups and effectiveness values
    TYPE_EFFECTIVENESS.get(FIRE).put(EARTH, 2.0); // FIRE is super effective against EARTH
    TYPE_EFFECTIVENESS.get(FIRE).put(WATER, 0.5); // FIRE is not very effective against WATER
    TYPE_EFFECTIVENESS.get(FIRE).put(FIRE, 0.5); // FIRE is not very effective against itself

    TYPE_EFFECTIVENESS.get(WATER).put(FIRE, 2.0); // WATER is super effective against FIRE
    TYPE_EFFECTIVENESS.get(WATER).put(EARTH, 0.5); // WATER is not very effective against EARTH
    TYPE_EFFECTIVENESS.get(WATER).put(WATER, 0.5); // WATER is not very effective against itself

    TYPE_EFFECTIVENESS.get(EARTH).put(WATER, 2.0); // EARTH is super effective against WATER
    TYPE_EFFECTIVENESS.get(EARTH).put(FIRE, 0.5); // EARTH is not very effective against FIRE
    TYPE_EFFECTIVENESS.get(EARTH).put(EARTH, 0.5); // EARTH is not very effective against itself

    TYPE_EFFECTIVENESS.get(PSYCHIC).put(DARK, 2.0); // PSYCHIC is super effective against DARK
    TYPE_EFFECTIVENESS
        .get(PSYCHIC)
        .put(LIGHT, 0.5); // PSYCHIC is not very effective against LIGHT
    TYPE_EFFECTIVENESS
        .get(PSYCHIC)
        .put(PSYCHIC, 0.5); // PSYCHIC is not very effective against itself

    TYPE_EFFECTIVENESS.get(LIGHT).put(PSYCHIC, 2.0); // LIGHT is super effective against PSYCHIC
    TYPE_EFFECTIVENESS.get(LIGHT).put(DARK, 0.5); // LIGHT is not very effective against DARK
    TYPE_EFFECTIVENESS.get(LIGHT).put(LIGHT, 0.5); // LIGHT is not very effective against itself

    TYPE_EFFECTIVENESS.get(DARK).put(LIGHT, 2.0); // DARK is super effective against LIGHT
    TYPE_EFFECTIVENESS.get(DARK).put(PSYCHIC, 0.5); // DARK is not very effective against PSYCHIC
    TYPE_EFFECTIVENESS.get(DARK).put(DARK, 0.5); // DARK is not very effective against itself
  }

  public double getEffectivenessAgainst(RBType otherType) {
    Map<RBType, Double> effectivenessMap = TYPE_EFFECTIVENESS.get(this);
    if (effectivenessMap != null) {
      Double effectiveness = effectivenessMap.get(otherType);
      if (effectiveness != null) {
        return effectiveness;
      }
    }
    return 1.0; // Default effectiveness is 1.0 (normally effective)
  }
}
