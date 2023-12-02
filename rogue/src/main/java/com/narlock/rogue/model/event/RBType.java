package com.narlock.rogue.model.event;

import com.narlock.rogue.model.boss.RBBossType;

import java.util.Random;

public enum RBType {
    FIRE("Fire"),
    WATER("Water"),
    EARTH("Earth"),
    ELECTRIC("Electric"),
    LIGHT("Light"),
    DARK("Dark");

    RBType(String name) {
    }

    private static final Random RANDOM = new Random();

    public static RBType random() {
        return values()[RANDOM.nextInt(values().length)];
    }
}
