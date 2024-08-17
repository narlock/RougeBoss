package com.narlock.rogue.model.event;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class RBEvent {
  private String id;
  private String name;
  private RBType type;
  private RBModel model;
  private int weapon;
  private int powerUp;
  private int exp;

  public static RBEvent NEW_BOSS =
      RBEvent.builder()
          .id("0")
          .name("")
          .type(RBType.FIRE)
          .model(RBModel.TRINITY)
          .weapon(1)
          .powerUp(1)
          .exp(0)
          .build();

  public static RBEvent PING =
      RBEvent.builder()
          .id("0")
          .name("")
          .type(RBType.WATER)
          .model(RBModel.TRINITY)
          .weapon(1)
          .powerUp(1)
          .exp(0)
          .build();
}
