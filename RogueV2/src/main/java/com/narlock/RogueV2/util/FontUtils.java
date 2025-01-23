package com.narlock.RogueV2.util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Font utility class, for methods pertaining to loading and calling fonts
 *
 * @author narlock
 */
public class FontUtils {

  public static Font pressStart;
  public static Font pressStart_8;
  public static Font pressStart_12;
  public static Font pressStart_16;
  public static Font pressStart_24;
  public static Font pressStart_32;
  public static Font pressStart_40;
  public static Font pressStart_48;
  public static Font pressStart_96;

  static {
    loadFonts();
  }

  private static void loadFonts() {
    InputStream is = FontUtils.class.getResourceAsStream("/res/font/PressStart2P-Regular.ttf");
    try {
      pressStart = Font.createFont(Font.TRUETYPE_FONT, is);
      pressStart_8 = pressStart.deriveFont(Font.PLAIN, 8F);
      pressStart_12 = pressStart.deriveFont(Font.PLAIN, 12F);
      pressStart_16 = pressStart.deriveFont(Font.PLAIN, 16F);
      pressStart_24 = pressStart.deriveFont(Font.PLAIN, 24F);
      pressStart_32 = pressStart.deriveFont(Font.PLAIN, 32F);
      pressStart_40 = pressStart.deriveFont(Font.PLAIN, 40F);
      pressStart_48 = pressStart.deriveFont(Font.PLAIN, 48F);
      pressStart_96 = pressStart.deriveFont(Font.PLAIN, 96F);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }
  }
}
