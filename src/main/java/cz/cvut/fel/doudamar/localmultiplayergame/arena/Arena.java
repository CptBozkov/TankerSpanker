package cz.cvut.fel.doudamar.localmultiplayergame.arena;

import cz.cvut.fel.doudamar.localmultiplayergame.core.Const;

import java.awt.*;
import java.util.ArrayList;

public class Arena {
    int sizeX;
    int sizeY;
    int r;



    int arenaToCenterX;
    int arenaToCenterY;

    ArrayList<Wall> walls = new ArrayList<>();

    public Arena(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        r = Const.WALL_WIDTH/2;

        arenaToCenterX = Const.SCREEN_SIZE_X/2 - getSizeX()*Const.TILE_SIZE/2;
        arenaToCenterY = Const.BAR_HEIGHT;
        addBoundary();
        addWall(1, 0, 0, 3);
        addWall(3, 1, 0, 3);
    }

    private void addBoundary(){
        addWall(0, 0,sizeX, 0);
        addWall(0, 0, 0, sizeY);
        addWall(sizeX, 0, 0, sizeY);
        addWall(0, sizeY, sizeX, 0);
    }

    private void addWall(int x, int y, int w, int h){
        walls.add(new Wall(x*Const.TILE_SIZE - r + arenaToCenterX, y*Const.TILE_SIZE - r + arenaToCenterY,
                w*Const.TILE_SIZE + r, h*Const.TILE_SIZE + r));
    }

    public void draw(Graphics2D g2){
        for (Wall w : walls){
            w.draw(g2);
        }
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getArenaToCenterX() {
        return arenaToCenterX;
    }

    public int getArenaToCenterY() {
        return arenaToCenterY;
    }
}
