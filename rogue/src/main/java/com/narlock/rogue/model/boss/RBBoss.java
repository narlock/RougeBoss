package com.narlock.rogue.model.boss;

import com.narlock.rogue.model.event.RBType;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class RBBoss {
  private int level;
  private RBBossType bossType;
  private RBType type;
  private int health;
  private List<RBDamage> damageList;

  public static RBBoss random(int level) {
    return RBBoss.builder()
        .level(level)
        .bossType(RBBossType.random())
        .type(RBType.random())
        .health(level * 1000)
        .damageList(new ArrayList<>())
        .build();
  }
}
