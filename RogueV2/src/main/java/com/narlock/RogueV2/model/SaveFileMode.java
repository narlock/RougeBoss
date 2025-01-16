package com.narlock.RogueV2.model;

import lombok.Getter;

@Getter
public enum SaveFileMode {
    RPG("RPG"), PEACEFUL("PEACEFUL");

    private final String value;

    SaveFileMode(String value) {
        this.value = value;
    }
}
