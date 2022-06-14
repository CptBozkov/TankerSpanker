package cz.cvut.fel.doudamar.localmultiplayergame.world;

import cz.cvut.fel.doudamar.localmultiplayergame.arena.Arena;
import cz.cvut.fel.doudamar.localmultiplayergame.arena.Wall;
import cz.cvut.fel.doudamar.localmultiplayergame.core.Const;
import cz.cvut.fel.doudamar.localmultiplayergame.utils.Vector2;

import java.awt.*;
import java.io.Serializable;

public class Projectile implements Serializable {
    Vector2 pos;
    Vector2 d;
    int r;
    long ttl;

    boolean hasCollided = false;

    boolean destroy = false;

    public Projectile(double posX, double posY, double angle, int r){
        pos = new Vector2(posX, posY);
        d = new Vector2(Math.cos(angle)*Const.PROJECTILE_SPEED, Math.sin(angle)*Const.PROJECTILE_SPEED);
        this.r = r;
        this.ttl = System.nanoTime()+(long)(Const.PROJECTILE_TTL*1000000000);
    }

    private Projectile(Vector2 pos, Vector2 d, int r, long ttl){
        this.pos = pos.copy();
        this.d = d.copy();
        this.r = r;
        this.ttl = ttl;
    }

    public void update(Arena arena) {
        if (ttl < System.nanoTime()) {
            destroy = true;
        }

        Vector2 moveVector = d.copy();
        Vector2 potentialPos = pos.copy();
        potentialPos.add(moveVector);

        for (Wall w : arena.getWalls()) {
            Vector2 nearestPoint = new Vector2();
            nearestPoint.x = Math.max(w.getP().x, Math.min(potentialPos.x, w.getP().x + w.getW()));
            nearestPoint.y = Math.max(w.getP().y, Math.min(potentialPos.y, w.getP().y + w.getH()));

            Vector2 rayToNearest = new Vector2(nearestPoint.x - potentialPos.x, nearestPoint.y - potentialPos.y);
            double overlap = r - rayToNearest.getLength();

            if (overlap > 0) {
                hasCollided = true;
                if (rayToNearest.x != 0) {
                    d.x = -d.x;
                }
                if (rayToNearest.y != 0) {
                    d.y = -d.y;
                }
            }
        }
        pos.add(d);
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillOval(pos.getRoundX()-r, pos.getRoundY()-r, r*2, r*2);
    }

    public Projectile copy() {
        return new Projectile(pos, d, r, ttl);
    }
}
