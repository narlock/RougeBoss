package com.narlock.RogueV2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
    private String id;
    private String name;
    private PlayerModel model;
    private String skinTone;
    private RBType type;
    private PlayerClass playerClass;
    private Integer xp;
    private Integer gil;
    private PlayerStatistics statistics;
    private Integer currentWeapon;
    private List<Integer> inventory;
}
