package com.narlock.rogue.model.event;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class RBEvent {
  private String id;
  private RBType type;
  private RBModel model;
  private int weapon;
  private int powerUp;
  private int exp;
}
