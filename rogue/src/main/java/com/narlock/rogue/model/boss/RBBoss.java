package com.narlock.rogue.model.boss;

import com.narlock.rogue.model.event.RBType;
import com.narlock.rogue.model.result.RBData;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
// @ToStrin g
public class RBBoss {
  private int level;
  private RBBossType bossType;
  private RBType type;
  private int health;
  private List<RBData> damageList;

  public static RBBoss random(int level) {
    return RBBoss.builder()
        .level(level)
        .bossType(RBBossType.random())
        .type(RBType.random())
        .health(level * 50)
        .damageList(new ArrayList<>())
        .build();
  }

  public String getName() {
    return type.name() + " " + bossType.name() + " LV" + level;
  }

  public RBBoss copy() {
    return RBBoss.builder()
        .level(this.level)
        .bossType(this.bossType)
        .type(this.type)
        .health(this.health)
        .damageList(new ArrayList<>(this.damageList)) // Copy the damage list
        .build();
  }

  public static RBBoss SLIME =
      RBBoss.builder()
          .level(1)
          .bossType(RBBossType.SLIME)
          .type(RBType.LIGHT)
          .health(1 * 500)
          .damageList(new ArrayList<>())
          .build();
}
