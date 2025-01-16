package com.narlock.RogueV2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerStatistics {
    private Integer attack;
    private Integer specialAttack;
    private Integer defense;
    private Integer specialDefense;
    private Integer agility;
}
