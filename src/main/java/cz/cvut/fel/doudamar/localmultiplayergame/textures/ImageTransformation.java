package cz.cvut.fel.doudamar.localmultiplayergame.textures;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Manages image scaling and rotations.
 */
public class ImageTransformation {
    /**
     * @param image buffered image to flip
     * @return returns flipped version of buffered image
     */
    public static BufferedImage getFlippedImage(BufferedImage image) {
        AffineTransform affineTransform = AffineTransform.getScaleInstance(-1, 1);
        affineTransform.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(image, null);
    }

    /**
     * draws rotated image on screen
     *
     * @param g2
     * @param x     pos x
     * @param y     pos y
     * @param img   buffered imnage
     * @param angle rotation angle
     */
    public static void drawRotatedImageByDegrees(Graphics2D g2, int x, int y, BufferedImage img, int angle) {
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2f, (newHeight - h) / 2f);

        at.rotate(rads, w / 2f, h / 2f);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        g2.drawImage(rotated, x + Math.round((img.getWidth() - rotated.getWidth()) / 2f), y + Math.round((img.getHeight() - rotated.getHeight()) / 2f), null);
    }

    public static void drawRotatedImageByRadians(Graphics2D g2, int x, int y, BufferedImage img, double rads) {
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2f, (newHeight - h) / 2f);

        at.rotate(rads, w / 2f, h / 2f);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        g2.drawImage(rotated, x + Math.round((img.getWidth() - rotated.getWidth()) / 2f), y + Math.round((img.getHeight() - rotated.getHeight()) / 2f), null);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2.dispose();
        return resizedImage;
    }
}
