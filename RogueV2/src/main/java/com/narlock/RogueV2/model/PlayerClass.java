package com.narlock.RogueV2.model;

import lombok.Getter;

@Getter
public enum PlayerClass {
    ROGUE("ROGUE"), PALADIN("PALADIN"), SORCERER("SORCERER"), BARD("BARD");

    private final String value;

    PlayerClass(String value) {
        this.value = value;
    }
}
