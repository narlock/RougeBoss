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
      case GNASHER:
        return Boss.builder()
            .image1(ImageUtils.readImage("/res/gnasher_1.png"))
            .image2(ImageUtils.readImage("/res/gnasher_2.png"))
            .build();
    }

    return GNASHER;
  }

  public static Boss GNASHER = getBossByType(RBBossType.GNASHER);
}
