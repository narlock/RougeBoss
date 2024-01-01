package com.narlock.rogue.gui;

import com.narlock.rogue.model.event.RBModel;
import com.narlock.rogue.model.event.RBType;
import com.narlock.rogue.util.ImageUtils;
import java.awt.image.BufferedImage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attacker {
  BufferedImage walk1, walk2, attack1, attack2;
  int x, y;

  public static Attacker getAttackerByType(RBType type) {
    switch (type) {
      case FIRE:
        return Attacker.builder()
            .walk1(ImageUtils.readImage("/res/fire_f_1.png"))
            .walk2(ImageUtils.readImage("/res/fire_f_2.png"))
            .attack1(ImageUtils.readImage("/res/fire_f_3.png"))
            .attack2(ImageUtils.readImage("/res/fire_f_4.png"))
            .x(30)
            .y(100)
            .build();
      case WATER:
        return Attacker.builder()
                .walk1(ImageUtils.readImage("/res/water_f_1.png"))
                .walk2(ImageUtils.readImage("/res/water_f_2.png"))
                .attack1(ImageUtils.readImage("/res/water_f_3.png"))
                .attack2(ImageUtils.readImage("/res/water_f_4.png"))
                .x(30)
                .y(100)
                .build();
      case EARTH:
        return Attacker.builder()
                .walk1(ImageUtils.readImage("/res/earth_f_1.png"))
                .walk2(ImageUtils.readImage("/res/earth_f_2.png"))
                .attack1(ImageUtils.readImage("/res/earth_f_3.png"))
                .attack2(ImageUtils.readImage("/res/earth_f_4.png"))
                .x(30)
                .y(100)
                .build();
      case PSYCHIC:
        return Attacker.builder()
                .walk1(ImageUtils.readImage("/res/psychic_f_1.png"))
                .walk2(ImageUtils.readImage("/res/psychic_f_2.png"))
                .attack1(ImageUtils.readImage("/res/psychic_f_3.png"))
                .attack2(ImageUtils.readImage("/res/psychic_f_4.png"))
                .x(30)
                .y(100)
                .build();
      case LIGHT:
        return Attacker.builder()
                .walk1(ImageUtils.readImage("/res/light_f_1.png"))
                .walk2(ImageUtils.readImage("/res/light_f_2.png"))
                .attack1(ImageUtils.readImage("/res/light_f_3.png"))
                .attack2(ImageUtils.readImage("/res/light_f_4.png"))
                .x(30)
                .y(100)
                .build();
      case DARK:
        return Attacker.builder()
                .walk1(ImageUtils.readImage("/res/dark_f_1.png"))
                .walk2(ImageUtils.readImage("/res/dark_f_2.png"))
                .attack1(ImageUtils.readImage("/res/dark_f_3.png"))
                .attack2(ImageUtils.readImage("/res/dark_f_4.png"))
                .x(30)
                .y(100)
                .build();
    }

    // Default
    return FIRE;
  }

  public static Attacker FIRE = getAttackerByType(RBType.FIRE);
}
