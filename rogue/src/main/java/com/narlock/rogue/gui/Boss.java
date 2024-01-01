package com.narlock.rogue.gui;

import com.narlock.rogue.model.boss.RBBossType;
import com.narlock.rogue.util.ImageUtils;
import java.awt.image.BufferedImage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Boss {
  BufferedImage image1, image2;
  int x, y;

  public static Boss getBossByType(RBBossType bossType) {
    switch (bossType) {
      case SLIME:
        return Boss.builder()
            .image1(ImageUtils.readImage("/res/slime_1.png"))
            .image2(ImageUtils.readImage("/res/slime_2.png"))
            .build();
    }

    return SLIME;
  }

  public static Boss SLIME = getBossByType(RBBossType.SLIME);
}
