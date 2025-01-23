package com.narlock.RogueV2.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RBData {
    private String id;
    private int damage;
}