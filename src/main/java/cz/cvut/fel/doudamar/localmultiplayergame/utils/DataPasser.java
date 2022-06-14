package cz.cvut.fel.doudamar.localmultiplayergame.utils;

import cz.cvut.fel.doudamar.localmultiplayergame.world.Player;
import cz.cvut.fel.doudamar.localmultiplayergame.world.Projectile;

import java.io.Serializable;

public class DataPasser implements Serializable {
    Vector2 pos;
    double rotation;
    Projectile projectile;

    boolean alive = true;

    public DataPasser(Player p){
        pos = p.getPos();
        rotation = p.getRotation();
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public Vector2 getPos() {
        return pos;
    }

    public double getRotation() {
        return rotation;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
