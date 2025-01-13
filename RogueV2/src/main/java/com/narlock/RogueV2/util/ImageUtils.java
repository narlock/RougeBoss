package com.narlock.RogueV2.util;

import java.awt.*;
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

    public static BufferedImage scaleReadImage(String imagePath, int width, int height) {
        BufferedImage image = readImage(imagePath);
        return scaleImage(image, width, height);
    }

    public static BufferedImage scaleImage(BufferedImage image, int widthHeight) {
        BufferedImage scaledImage = new BufferedImage(widthHeight, widthHeight, image.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(image, 0, 0, widthHeight, widthHeight, null);
        g2.dispose();
        return scaledImage;
    }

    public static BufferedImage scaleImage(BufferedImage image, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, image.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }
}
