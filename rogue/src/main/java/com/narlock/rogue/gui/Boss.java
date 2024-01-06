package com.narlock.rogue.gui;

import com.narlock.rogue.model.boss.RBBossType;
import com.narlock.rogue.model.event.RBType;
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
      case ZOMBIE:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/zombie_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/zombie_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case DRAGON:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/dragon.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/dragon.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case WOLF:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/wolf_1.png", 131, 100))
            .image2(ImageUtils.scaleReadImage("/res/wolf_1.png", 131, 100))
            .x(0)
            .y(30)
            .build();
      case OGRE:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/ogre_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/ogre_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case GHOST:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/ghost_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/ghost_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case UNICORN:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/unicorn.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/unicorn.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case LION:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/lion.png", 125, 100))
            .image2(ImageUtils.scaleReadImage("/res/lion.png", 125, 100))
            .x(0)
            .y(35)
            .build();
    }

    return SLIME;
  }

  public static Boss getBossByTypeAndRbType(RBBossType bossType, RBType type) {
    switch (type) {
      case FIRE:
        return getFireBoss(bossType);
      case WATER:
        return getWaterBoss(bossType);
      case EARTH:
        return getEarthBoss(bossType);
      case PSYCHIC:
        return getPsychicBoss(bossType);
      case LIGHT:
        return getLightBoss(bossType);
      case DARK:
        return getDarkBoss(bossType);
    }
    return SLIME;
  }

  public static Boss getFireBoss(RBBossType bossType) {
    switch (bossType) {
      case SLIME:
        return Boss.builder()
            .image1(ImageUtils.readImage("/res/fire/slime_1.png"))
            .image2(ImageUtils.readImage("/res/fire/slime_2.png"))
            .build();
      case ZOMBIE:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/fire/zombie_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/fire/zombie_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case DRAGON:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/fire/dragon.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/fire/dragon.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case WOLF:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/fire/wolf_1.png", 131, 100))
            .image2(ImageUtils.scaleReadImage("/res/fire/wolf_1.png", 131, 100))
            .x(0)
            .y(30)
            .build();
      case OGRE:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/fire/ogre_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/fire/ogre_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case GHOST:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/fire/ghost_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/fire/ghost_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case UNICORN:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/fire/unicorn.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/fire/unicorn.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case LION:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/fire/lion.png", 125, 100))
            .image2(ImageUtils.scaleReadImage("/res/fire/lion.png", 125, 100))
            .x(0)
            .y(35)
            .build();
    }
    return getFireBoss(RBBossType.SLIME);
  }

  public static Boss getWaterBoss(RBBossType bossType) {
    switch (bossType) {
      case SLIME:
        return Boss.builder()
            .image1(ImageUtils.readImage("/res/water/slime_1.png"))
            .image2(ImageUtils.readImage("/res/water/slime_2.png"))
            .build();
      case ZOMBIE:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/water/zombie_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/water/zombie_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case DRAGON:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/water/dragon.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/water/dragon.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case WOLF:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/water/wolf_1.png", 131, 100))
            .image2(ImageUtils.scaleReadImage("/res/water/wolf_1.png", 131, 100))
            .x(0)
            .y(30)
            .build();
      case OGRE:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/water/ogre_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/water/ogre_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case GHOST:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/water/ghost_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/water/ghost_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case UNICORN:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/water/unicorn.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/water/unicorn.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case LION:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/water/lion.png", 125, 100))
            .image2(ImageUtils.scaleReadImage("/res/water/lion.png", 125, 100))
            .x(0)
            .y(35)
            .build();
    }
    return getWaterBoss(RBBossType.SLIME);
  }

  public static Boss getEarthBoss(RBBossType bossType) {
    switch (bossType) {
      case SLIME:
        return Boss.builder()
            .image1(ImageUtils.readImage("/res/earth/slime_1.png"))
            .image2(ImageUtils.readImage("/res/earth/slime_2.png"))
            .build();
      case ZOMBIE:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/earth/zombie_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/earth/zombie_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case DRAGON:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/earth/dragon.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/earth/dragon.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case WOLF:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/earth/wolf_1.png", 131, 100))
            .image2(ImageUtils.scaleReadImage("/res/earth/wolf_1.png", 131, 100))
            .x(0)
            .y(30)
            .build();
      case OGRE:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/earth/ogre_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/earth/ogre_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case GHOST:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/earth/ghost_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/earth/ghost_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case UNICORN:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/earth/unicorn.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/earth/unicorn.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case LION:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/earth/lion.png", 125, 100))
            .image2(ImageUtils.scaleReadImage("/res/earth/lion.png", 125, 100))
            .x(0)
            .y(35)
            .build();
    }
    return getEarthBoss(RBBossType.SLIME);
  }

  public static Boss getPsychicBoss(RBBossType bossType) {
    switch (bossType) {
      case SLIME:
        return Boss.builder()
            .image1(ImageUtils.readImage("/res/psychic/slime_1.png"))
            .image2(ImageUtils.readImage("/res/psychic/slime_2.png"))
            .build();
      case ZOMBIE:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/psychic/zombie_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/psychic/zombie_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case DRAGON:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/psychic/dragon.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/psychic/dragon.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case WOLF:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/psychic/wolf_1.png", 131, 100))
            .image2(ImageUtils.scaleReadImage("/res/psychic/wolf_1.png", 131, 100))
            .x(0)
            .y(30)
            .build();
      case OGRE:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/psychic/ogre_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/psychic/ogre_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case GHOST:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/psychic/ghost_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/psychic/ghost_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case UNICORN:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/psychic/unicorn.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/psychic/unicorn.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case LION:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/psychic/lion.png", 125, 100))
            .image2(ImageUtils.scaleReadImage("/res/psychic/lion.png", 125, 100))
            .x(0)
            .y(35)
            .build();
    }
    return getPsychicBoss(RBBossType.SLIME);
  }

  public static Boss getLightBoss(RBBossType bossType) {
    switch (bossType) {
      case SLIME:
        return Boss.builder()
            .image1(ImageUtils.readImage("/res/light/slime_1.png"))
            .image2(ImageUtils.readImage("/res/light/slime_2.png"))
            .build();
      case ZOMBIE:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/light/zombie_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/light/zombie_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case DRAGON:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/light/dragon.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/light/dragon.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case WOLF:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/light/wolf_1.png", 131, 100))
            .image2(ImageUtils.scaleReadImage("/res/light/wolf_1.png", 131, 100))
            .x(0)
            .y(30)
            .build();
      case OGRE:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/light/ogre_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/light/ogre_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case GHOST:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/light/ghost_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/light/ghost_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case UNICORN:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/light/unicorn.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/light/unicorn.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case LION:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/light/lion.png", 125, 100))
            .image2(ImageUtils.scaleReadImage("/res/light/lion.png", 125, 100))
            .x(0)
            .y(35)
            .build();
    }
    return getLightBoss(RBBossType.SLIME);
  }

  public static Boss getDarkBoss(RBBossType bossType) {
    switch (bossType) {
      case SLIME:
        return Boss.builder()
            .image1(ImageUtils.readImage("/res/dark/slime_1.png"))
            .image2(ImageUtils.readImage("/res/dark/slime_2.png"))
            .build();
      case ZOMBIE:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/dark/zombie_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/dark/zombie_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case DRAGON:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/dark/dragon.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/dark/dragon.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case WOLF:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/dark/wolf_1.png", 131, 100))
            .image2(ImageUtils.scaleReadImage("/res/dark/wolf_1.png", 131, 100))
            .x(0)
            .y(30)
            .build();
      case OGRE:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/dark/ogre_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/dark/ogre_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case GHOST:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/dark/ghost_1.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/dark/ghost_2.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case UNICORN:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/dark/unicorn.png", 100, 100))
            .image2(ImageUtils.scaleReadImage("/res/dark/unicorn.png", 100, 100))
            .x(0)
            .y(30)
            .build();
      case LION:
        return Boss.builder()
            .image1(ImageUtils.scaleReadImage("/res/dark/lion.png", 125, 100))
            .image2(ImageUtils.scaleReadImage("/res/dark/lion.png", 125, 100))
            .x(0)
            .y(35)
            .build();
    }
    return getDarkBoss(RBBossType.SLIME);
  }

  public static Boss SLIME = getBossByType(RBBossType.SLIME);
}
