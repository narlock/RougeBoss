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

  private static final String RES_PATH = "/res/attacker/";

  BufferedImage walk1, walk2, attack1, attack2;
  int x, y;

  public static Attacker getAttacker(RBType rbType, RBModel rbModel) {
    AttackerBuilder attackerBuilder = Attacker.builder().x(30).y(100);

    String attackerModelName = rbModel.name().toLowerCase();
    String attackerTypeName = rbType.name().toLowerCase();

    // TODO determine attack animation - may require a new parameter to this method
    String attackerAnimation = "sword";

    attackerBuilder
        .walk1(
            ImageUtils.readImage(
                RES_PATH + attackerModelName + "/" + attackerTypeName + "/walk1.png"))
        .walk2(
            ImageUtils.readImage(
                RES_PATH + attackerModelName + "/" + attackerTypeName + "/walk2.png"))
        .attack1(
            ImageUtils.readImage(
                RES_PATH
                    + attackerModelName
                    + "/"
                    + attackerTypeName
                    + "/"
                    + attackerAnimation
                    + "1.png"))
        .attack2(
            ImageUtils.readImage(
                RES_PATH
                    + attackerModelName
                    + "/"
                    + attackerTypeName
                    + "/"
                    + attackerAnimation
                    + "2.png"));

    return attackerBuilder.build();
  }

  public static final Attacker NONE = getAttacker(RBType.FIRE, RBModel.NONE);
}
