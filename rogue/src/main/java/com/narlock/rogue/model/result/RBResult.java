package com.narlock.rogue.model.result;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RBResult {
    private int damage;
    private boolean slain;
    private List<RBData> slainData;
}
