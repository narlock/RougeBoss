package com.narlock.rogue.model.boss;

import com.narlock.rogue.model.event.RBType;
import com.narlock.rogue.model.result.RBData;
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
  private List<RBData> damageList;

  public static RBBoss random(int level) {
    return RBBoss.builder()
        .level(level)
        .bossType(RBBossType.random())
        .type(RBType.random())
        .health(level * 500)
        .damageList(new ArrayList<>())
        .build();
  }

  public String getName() {
    return type.name() + " " + bossType.name() + " LV" + level;
  }

  public static RBBoss GNASHER =
      RBBoss.builder()
          .level(1)
          .bossType(RBBossType.GNASHER)
          .type(RBType.LIGHT)
          .health(1 * 500)
          .damageList(new ArrayList<>())
          .build();
}
