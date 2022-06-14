package cz.cvut.fel.doudamar.localmultiplayergame.core;

import cz.cvut.fel.doudamar.localmultiplayergame.scenes.Game;
import cz.cvut.fel.doudamar.localmultiplayergame.scenes.Menu;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    JFrame frame;
    Thread gameThread;

    FPSManager fpsManager = new FPSManager(Const.FPS);
    MouseManager mouseManager = new MouseManager();
    KeyManager keyManager = new KeyManager();

    ScenesManager scenesManager = new ScenesManager(mouseManager, keyManager);

    public GamePanel(JFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(Const.SCREEN_SIZE_X, Const.SCREEN_SIZE_Y));
        setDoubleBuffered(true);
        setFocusable(true);

        scenesManager.addScene(0, new Menu(scenesManager));
        mouseManager.setGamePanel(this);
        addMouseListener(mouseManager);
        addKeyListener(keyManager);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            update();
            repaint();
            fpsManager.manageFPS();
        }
    }

    private void update() {
        mouseManager.updateMouseTapped();
        scenesManager.update();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(0, 0, Const.SCREEN_SIZE_X, Const.SCREEN_SIZE_Y);
        scenesManager.draw(g2);
    }
}
