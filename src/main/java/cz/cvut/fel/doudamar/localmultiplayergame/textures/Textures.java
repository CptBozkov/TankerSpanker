package cz.cvut.fel.doudamar.localmultiplayergame.textures;

import cz.cvut.fel.doudamar.localmultiplayergame.core.Const;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Textures {
    public enum TankType {BLUE_BASIC, RED_BASIC}

    BufferedImage tanksSheet;
    BufferedImage blueTankBasic;
    BufferedImage redTankBasic;


    public Textures() {
        try {
            tanksSheet = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\main\\java\\cz\\cvut\\fel\\doudamar\\localmultiplayergame\\textures\\tanks.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blueTankBasic = tanksSheet.getSubimage(0, 0, Const.TANK_WIDTH, Const.TANK_HEIGHT);
        blueTankBasic = ImageTransformation.resizeImage(blueTankBasic, (int)(blueTankBasic.getWidth()*Const.TANK_SCALE), (int)(blueTankBasic.getHeight()*Const.TANK_SCALE));
        redTankBasic = tanksSheet.getSubimage(Const.TANK_WIDTH, 0, Const.TANK_WIDTH, Const.TANK_HEIGHT);
        redTankBasic = ImageTransformation.resizeImage(redTankBasic, (int)(redTankBasic.getWidth()*Const.TANK_SCALE), (int)(redTankBasic.getHeight()*Const.TANK_SCALE));
    }

    public void drawPlayer(Graphics2D g2, TankType tankType, int x, int y, double rotation){
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (tankType == TankType.BLUE_BASIC){
            ImageTransformation.drawRotatedImageByRadians(g2, x, y, blueTankBasic, rotation);
        } else if (tankType == TankType.RED_BASIC){
            ImageTransformation.drawRotatedImageByRadians(g2, x, y, redTankBasic, rotation);
        }
    }
}
