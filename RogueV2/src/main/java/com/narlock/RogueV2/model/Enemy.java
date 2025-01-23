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
public class Enemy {
    private EnemyType enemyType;
    private Integer enemyLevel;
    private RBType type;
    private Integer health;
    private List<RBData> damageList;
}
