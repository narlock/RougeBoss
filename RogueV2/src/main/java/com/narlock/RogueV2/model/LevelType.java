package com.narlock.RogueV2.model;

import lombok.Getter;

@Getter
public enum LevelType {
    TRIAL("TRIAL"), BOSS("BOSS");

    private final String value;

    LevelType(String value) {
        this.value = value;
    }
}
