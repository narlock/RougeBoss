package com.narlock.rogue.util;

import java.awt.*;

public class ScreenUtils {
    public static int getXForCenterText(Graphics g, String text) {
        int textLength = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        return (300 / 2) - (textLength / 2);
    }
}
