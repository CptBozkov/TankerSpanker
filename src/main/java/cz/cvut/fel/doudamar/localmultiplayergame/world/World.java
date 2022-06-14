package cz.cvut.fel.doudamar.localmultiplayergame.world;

import cz.cvut.fel.doudamar.localmultiplayergame.arena.Arena;
import cz.cvut.fel.doudamar.localmultiplayergame.core.Const;
import cz.cvut.fel.doudamar.localmultiplayergame.core.NetWorker;
import cz.cvut.fel.doudamar.localmultiplayergame.core.ScenesManager;
import cz.cvut.fel.doudamar.localmultiplayergame.textures.Textures;
import cz.cvut.fel.doudamar.localmultiplayergame.utils.DataPasser;
import cz.cvut.fel.doudamar.localmultiplayergame.utils.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class World {
    ScenesManager scenesManager;

    Player myPlayer;
    Player enemyPlayer;

    DataPasser myDP;
    DataPasser enemyDP;

    boolean isNewProjectile = false;
    Projectile newProjectile;

    ArrayList<Projectile> myProjectiles = new ArrayList<>();
    ArrayList<Projectile> enemyProjectiles = new ArrayList<>();

    ArrayList<Arena> arenas = new ArrayList<>();
    int selectedArena = 0;

    public World(ScenesManager scenesManager, NetWorker netWorker){
        this.scenesManager = scenesManager;

        arenas.add(new Arena(6, 4));

        if (netWorker.getNetWorkerType() == Const.NetWorkerType.SERVER){
            myPlayer = new Player(getActiveArena().getArenaToCenterX() + Const.TILE_SIZE/2, getActiveArena().getArenaToCenterY() + (int)((getActiveArena().getSizeY()/2d)*Const.TILE_SIZE), 0, Textures.TankType.BLUE_BASIC, scenesManager, this);
            enemyPlayer = new Player(getActiveArena().getArenaToCenterX() + (int)((getActiveArena().getSizeX()-.5d)*Const.TILE_SIZE), getActiveArena().getArenaToCenterY() + (int)((getActiveArena().getSizeY()/2d)*Const.TILE_SIZE), Math.PI, Textures.TankType.RED_BASIC, scenesManager, this);
        } else if (netWorker.getNetWorkerType() == Const.NetWorkerType.CLIENT){
            myPlayer = new Player(getActiveArena().getArenaToCenterX() + (int)((getActiveArena().getSizeX()-.5d)*Const.TILE_SIZE), getActiveArena().getArenaToCenterY() + (int)((getActiveArena().getSizeY()/2d)*Const.TILE_SIZE), Math.PI, Textures.TankType.BLUE_BASIC, scenesManager, this);
            enemyPlayer = new Player(getActiveArena().getArenaToCenterX() + Const.TILE_SIZE/2, getActiveArena().getArenaToCenterY() + (int)((getActiveArena().getSizeY()/2d)*Const.TILE_SIZE), 0, Textures.TankType.RED_BASIC, scenesManager, this);
        }


        myDP = new DataPasser(myPlayer);
        enemyDP = new DataPasser(enemyPlayer);

        netWorker.createConnection(this);

    }

    private void updateMyDP(){
        Vector2 myPos = myPlayer.pos.copy();
        myPos.multiply(1d/Const.SCALE);
        myDP.setPos(myPos);
        myDP.setRotation(myPlayer.rotation);
        if (isNewProjectile){
            newProjectile.pos.multiply(1d/Const.SCALE);
            newProjectile.d.multiply(1d/Const.SCALE);
            newProjectile.r *= 1d/Const.SCALE;
            isNewProjectile = false;
            myDP.setProjectile(newProjectile);
        }
        myDP.setAlive(myPlayer.alive);
    }

    private void processEnemyDP(){
        enemyPlayer.pos = enemyDP.getPos().copy();
        enemyPlayer.pos.multiply(Const.SCALE);
        enemyPlayer.rotation = enemyDP.getRotation();
        if (enemyDP.getProjectile() != null){
            Projectile p = enemyDP.getProjectile().copy();
            p.pos.multiply(Const.SCALE);
            p.d.multiply(Const.SCALE);
            p.r *= Const.SCALE;
            enemyProjectiles.add(p);
            enemyDP.setProjectile(null);
        }
        if (!enemyDP.isAlive()){
            enemyPlayer.alive = false;
        }
    }

    public void update(){
        myPlayer.update();
        enemyPlayer.updateEnemy();
        updateMyDP();
        processEnemyDP();
        for (int i = myProjectiles.size()-1; i >= 0; i--){
            myProjectiles.get(i).update(arenas.get(selectedArena));
            if (myProjectiles.get(i).destroy){
                myProjectiles.remove(i);
            }
        }
        for (int i = enemyProjectiles.size()-1; i >= 0; i--){
            enemyProjectiles.get(i).update(arenas.get(selectedArena));
            if (enemyProjectiles.get(i).destroy){
                enemyProjectiles.remove(i);
            }
        }
    }

    public void draw(Graphics2D g2){
        arenas.get(selectedArena).draw(g2);
        for (Projectile p : myProjectiles){
            p.draw(g2);
        }
        for (Projectile p : enemyProjectiles){
            p.draw(g2);
        }
        enemyPlayer.draw(g2);
        myPlayer.draw(g2);
    }

    public DataPasser getMyDP() {
        return myDP;
    }

    public DataPasser getEnemyDP() {
        return enemyDP;
    }

    public void resetMyDP(){
        myDP.setProjectile(null);
    }

    public Arena getActiveArena() {
        return arenas.get(selectedArena);
    }
}
