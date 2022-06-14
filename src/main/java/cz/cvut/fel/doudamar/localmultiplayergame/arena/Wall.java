package cz.cvut.fel.doudamar.localmultiplayergame.arena;

import cz.cvut.fel.doudamar.localmultiplayergame.core.Const;
import cz.cvut.fel.doudamar.localmultiplayergame.utils.Vector2;

import java.awt.*;

public class Wall {
    Point p;
    int w;
    int h;

    Vector2 d;

    Const.WallType wallType;

    public Wall(int x, int y, int w, int h){
        this.p = new Point(x, y);
        this.h = h;
        this.w = w;
        d = new Vector2(w, h);
        if (h == 0) {
            wallType = Const.WallType.HORIZONTAL;
        } if (w == 0) {
            wallType = Const.WallType.VERTICAL;
        }
    }

    public void draw(Graphics2D g2){
        g2.fillRect(p.x, p.y, w, h);
    }

    public Point getP() {
        return p;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public Vector2 getD() {
        return d;
    }

    public Const.WallType getWallType() {
        return wallType;
    }
}
