package com.narlock.rogue.model.result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RBData {
    private String id;
    private int totalDamage;
    private int expEarned;
}
