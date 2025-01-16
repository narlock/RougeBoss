package com.narlock.RogueV2.model;

import lombok.Getter;

@Getter
public enum PlayerModel {
    ADA("ADA"), ANT("ANT");

    private final String value;

    PlayerModel(String value) {
        this.value = value;
    }
}
