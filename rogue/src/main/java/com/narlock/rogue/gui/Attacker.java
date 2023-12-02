package com.narlock.rogue.gui;

import com.narlock.rogue.model.event.RBModel;
import com.narlock.rogue.util.ImageUtils;
import java.awt.image.BufferedImage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attacker {
  BufferedImage walk1, walk2, attack1, attack2;
  int x, y;

  public static Attacker getAttackerByType(RBModel model) {
    switch (model) {
      case TRINITY:
        return Attacker.builder()
            .walk1(ImageUtils.readImage("/res/trinity_right_1.png"))
            .walk2(ImageUtils.readImage("/res/trinity_right_2.png"))
            .attack1(ImageUtils.readImage("/res/trinity_sword_right_1.png"))
            .attack2(ImageUtils.readImage("/res/trinity_sword_right_2.png"))
            .x(30)
            .y(100)
            .build();
    }

    // Default
    return TRINITY;
  }

  public static Attacker TRINITY = getAttackerByType(RBModel.TRINITY);
}
