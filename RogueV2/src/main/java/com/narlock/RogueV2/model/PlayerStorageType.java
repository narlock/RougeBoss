package com.narlock.RogueV2.model;

import lombok.Getter;

@Getter
public enum PlayerStorageType {
    IN_FILE("IN_FILE"), NOT_IN_FILE("NOT_IN_FILE");

    private final String value;

    PlayerStorageType(String value) {
        this.value = value;
    }
}
