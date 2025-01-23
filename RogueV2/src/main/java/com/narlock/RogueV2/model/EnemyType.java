package com.narlock.RogueV2.model;

import lombok.Getter;

import java.util.Random;

@Getter
public enum EnemyType {
    SLIME("SLIME"),
    ZOMBIE("ZOMBIE"),
    DRAGON("DRAGON"),
    WOLF("WOLF"),
    OGRE("OGRE"),
    GHOST("GHOST"),
    UNICORN("UNICORN"),
    LION("LION");

    private final String name;

    EnemyType(String name) {
        this.name = name;
    }

    private static final Random RANDOM = new Random();
    /**
     * Generates a random enemy type
     * @return EnemyType that is randomly generated
     */
    public static EnemyType random() {
        return values()[RANDOM.nextInt(values().length)];
    }
}
