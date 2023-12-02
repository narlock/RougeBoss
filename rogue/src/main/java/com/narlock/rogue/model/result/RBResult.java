package com.narlock.rogue.model.result;

import java.util.List;

import com.narlock.rogue.model.boss.RBBoss;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RBResult {
  private String note;
  private boolean slain;
  private RBBoss boss;
}
