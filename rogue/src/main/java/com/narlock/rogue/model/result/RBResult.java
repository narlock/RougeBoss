package com.narlock.rogue.model.result;

import com.narlock.rogue.model.boss.RBBoss;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RBResult {
  private String note;
  private boolean slain;
  private RBBoss boss;

  public static RBResult modify(RBResult original) {
    String modifiedNote = original.note.replaceAll("\n", " ");
    return RBResult.builder().note(modifiedNote).slain(original.slain).boss(original.boss).build();
  }
}
