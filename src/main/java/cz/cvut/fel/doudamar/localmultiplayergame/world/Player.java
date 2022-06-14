package cz.cvut.fel.doudamar.localmultiplayergame.world;

import cz.cvut.fel.doudamar.localmultiplayergame.arena.Arena;
import cz.cvut.fel.doudamar.localmultiplayergame.arena.Wall;
import cz.cvut.fel.doudamar.localmultiplayergame.core.Const;
import cz.cvut.fel.doudamar.localmultiplayergame.core.KeyManager;
import cz.cvut.fel.doudamar.localmultiplayergame.core.ScenesManager;
import cz.cvut.fel.doudamar.localmultiplayergame.textures.Textures;
import cz.cvut.fel.doudamar.localmultiplayergame.utils.Vector2;

import java.awt.*;

public class Player {
    ScenesManager scenesManager;
    World world;
    KeyManager km;

    Vector2 pos;
    double rotation;

    int w = (int)(Const.TANK_WIDTH*Const.TANK_SCALE);
    int h = (int)(Const.TANK_HEIGHT*Const.TANK_SCALE);

    Textures.TankType tankType;

    long lastShotTime;

    boolean alive = true;


    public Player(int posX, int posY, double rotation, Textures.TankType tankType, ScenesManager scenesManager, World world){
        pos = new Vector2(posX, posY);
        this.rotation = rotation;
        this.scenesManager = scenesManager;
        this.world = world;
        km = scenesManager.getKeyManager();
        this.tankType = tankType;
    }

    private void drawPlayer(Graphics2D g2){
        scenesManager.getTextures().drawPlayer(g2, tankType, (int)Math.round(pos.getX())-w/2, (int)Math.round(pos.getY())-h/2, rotation);
    }

    private void move(){
        Vector2 d = new Vector2();
        if ((km.leftPressed && !km.downPressed) || (km.rightPressed && km.downPressed)){
            rotation -= Const.ROTATION_SPEED;
        } if ((km.rightPressed && !km.downPressed) || (km.leftPressed && km.downPressed)){
            rotation += Const.ROTATION_SPEED;
        } if (km.upPressed){
            d.x += Math.cos(rotation) * Const.MOVEMENT_SPEED;
            d.y += Math.sin(rotation) * Const.MOVEMENT_SPEED;
        } if (km.downPressed){
            d.x -= Math.cos(rotation) * Const.MOVEMENT_SPEED;
            d.y -= Math.sin(rotation) * Const.MOVEMENT_SPEED;
        }

        Vector2 moveVector = d.copy();
        Vector2 potentialPos = pos.copy();
        potentialPos.add(moveVector);
        for (Wall w : world.getActiveArena().getWalls()){
            Vector2 nearestPoint = new Vector2();
            nearestPoint.x = Math.max(w.getP().x, Math.min(potentialPos.x, w.getP().x + w.getW()));
            nearestPoint.y = Math.max(w.getP().y, Math.min(potentialPos.y, w.getP().y + w.getH()));

            Vector2 rayToNearest = new Vector2(nearestPoint.x - potentialPos.x, nearestPoint.y - potentialPos.y);
            double overlap = Const.TANK_HIT_BOX_RADIUS - rayToNearest.getLength();

            if (overlap > 0) {
                rayToNearest.normalize();
                rayToNearest.multiply(overlap);
                potentialPos.add(-rayToNearest.x, -rayToNearest.y);
            }
        }
        pos = potentialPos;

    }

    private void shoot(){
        if (km.spacePressed && lastShotTime + Const.RELOAD_TIME * 1000000000 < System.nanoTime()) {
            lastShotTime = System.nanoTime();
            world.myProjectiles.add(new Projectile(pos.getX(), pos.getY(), rotation, Const.PROJECTILE_RADIUS));
            world.isNewProjectile = true;
            world.newProjectile = new Projectile(pos.getX(), pos.getY(), rotation, Const.PROJECTILE_RADIUS);
        }
    }

    private void getHit(){
        if (alive) {
            for (Projectile p : world.myProjectiles) {
                if (p.hasCollided && Math.sqrt(Math.pow(p.pos.x - pos.x, 2) + Math.pow(p.pos.y - pos.y, 2)) <= Const.TANK_HIT_BOX_RADIUS + Const.PROJECTILE_RADIUS) {
                    alive = false;
                    p.destroy = true;
                }
            }
            for (Projectile p : world.enemyProjectiles) {
                if (Math.sqrt(Math.pow(p.pos.x - pos.x, 2) + Math.pow(p.pos.y - pos.y, 2)) <= Const.TANK_HIT_BOX_RADIUS + Const.PROJECTILE_RADIUS) {
                    alive = false;
                    p.destroy = true;
                }
            }
        }
    }

    private void enemyGetHit(){
        if (alive){
            for (Projectile p : world.myProjectiles) {
                if (Math.sqrt(Math.pow(p.pos.x - pos.x, 2)+Math.pow(p.pos.y - pos.y, 2)) <= Const.TANK_HIT_BOX_RADIUS + Const.PROJECTILE_RADIUS){
                    alive = false;
                    p.destroy = true;
                }
            }
            for (Projectile p : world.enemyProjectiles) {
                if (p.hasCollided && Math.sqrt(Math.pow(p.pos.x - pos.x, 2)+Math.pow(p.pos.y - pos.y, 2)) <= Const.TANK_HIT_BOX_RADIUS + Const.PROJECTILE_RADIUS){
                    alive = false;
                    p.destroy = true;
                }
            }
        }
    }

    public void update(){
        move();
        shoot();
        getHit();
    }

    public void updateEnemy(){
        enemyGetHit();
    }

    public void draw(Graphics2D g2){
        if (alive) {
            drawPlayer(g2);
        }
    }

    public Vector2 getPos() {
        return pos;
    }

    public double getRotation() {
        return rotation;
    }
}

