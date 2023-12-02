package com.narlock.rogue.gui;

import com.narlock.rogue.model.event.RBEvent;
import com.narlock.rogue.model.result.RBResult;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventPair {
  private RBEvent event;
  private RBResult result;
}
