package cz.cvut.fel.doudamar.localmultiplayergame.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Manages all keyboard user inputs.
 */
public class KeyManager implements KeyListener {

    public boolean upPressed, leftPressed, downPressed, rightPressed;
    public boolean escPressed;
    public boolean spacePressed;
    public boolean gTapped, cTapped, eTapped, lTapped;
    public boolean tabTapped;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // gets pressed keys
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = true;
        }
        if (code == KeyEvent.VK_G) {
            gTapped = true;
        }
        if (code == KeyEvent.VK_C) {
            cTapped = true;
        }
        if (code == KeyEvent.VK_E) {
            eTapped = true;
        }
        if (code == KeyEvent.VK_L) {
            lTapped = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (code == KeyEvent.VK_TAB) {
            tabTapped = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // resets pressed keys
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = false;
        }
        if (code == KeyEvent.VK_G) {
            gTapped = false;
        }
        if (code == KeyEvent.VK_C) {
            cTapped = false;
        }
        if (code == KeyEvent.VK_E) {
            eTapped = false;
        }
        if (code == KeyEvent.VK_L) {
            lTapped = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (code == KeyEvent.VK_TAB) {
            tabTapped = false;
        }
    }
}
