package com.narlock.rogue.gui;

import com.narlock.rogue.model.event.RBModel;
import com.narlock.rogue.util.ImageUtils;
import java.awt.image.BufferedImage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attacker {
  BufferedImage attack1, attack2;
  int x, y;

  public static Attacker getAttackerByType(RBModel model) {
    switch (model) {
    case TRINITY:
        return Attacker.builder()
            .attack1(ImageUtils.readImage("/res/trinity_sword_right_1.png"))
            .attack2(ImageUtils.readImage("/res/trinity_sword_right_2.png"))
                .x(30)
                .y(50)
            .build();
    }

    // Default
    return TRINITY;
  }

  public static Attacker TRINITY = getAttackerByType(RBModel.TRINITY);
}
