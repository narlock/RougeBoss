package com.narlock.rogue.model.result;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RBResult {
  private int damage;
  private boolean slain;
  private List<RBData> slainData;
}
