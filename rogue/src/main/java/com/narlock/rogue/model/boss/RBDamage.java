package com.narlock.rogue.model.boss;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RBDamage {
  private String id;
  private int damage;
}
