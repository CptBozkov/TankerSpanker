package cz.cvut.fel.doudamar.localmultiplayergame.core;

import cz.cvut.fel.doudamar.localmultiplayergame.scenes.Scene;
import cz.cvut.fel.doudamar.localmultiplayergame.textures.Textures;
import cz.cvut.fel.doudamar.localmultiplayergame.utils.ThreadDataPasser;

import java.awt.*;

public class ScenesManager {
    MouseManager mouseManager;
    KeyManager keyManager;
    Textures textures;

    public ThreadDataPasser getThreadDP() {
        return threadDP;
    }

    ThreadDataPasser threadDP = new ThreadDataPasser();

    int activeScene = 0;
    Scene[] scenes = new Scene[2];

    public ScenesManager(MouseManager mouseManager, KeyManager keyManager){
        this.mouseManager = mouseManager;
        this.keyManager = keyManager;
        textures = new Textures();
    }

    public void addScene(int i, Scene s){
        scenes[i] = s;
    }

    public void setActiveScene(int i){
        activeScene = i;
    }

    public void update(){
        scenes[activeScene].update();
    }

    public void draw(Graphics2D g2){
        scenes[activeScene].draw(g2);
    }

    public MouseManager getMouseManager(){
        return mouseManager;
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public Textures getTextures(){
        return textures;
    }
}
