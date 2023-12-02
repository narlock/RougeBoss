package com.narlock.rogue.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {
  public static BufferedImage readImage(String imagePath) {
    try {
      return ImageIO.read(ImageUtils.class.getResourceAsStream(imagePath));
    } catch (IOException e) {
      e.printStackTrace();
    }
    throw new RuntimeException("Failed to load image from path: " + imagePath);
  }
}
