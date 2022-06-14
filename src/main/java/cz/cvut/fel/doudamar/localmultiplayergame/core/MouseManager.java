package cz.cvut.fel.doudamar.localmultiplayergame.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener {
    GamePanel gamePanel;
    boolean leftPressed, rightPressed;
    boolean lastLeftPressed, lastRightPressed;

    boolean leftTapped, rightTapped;

    public boolean getLeftPressed() {
        return leftPressed;
    }

    public boolean getRightPressed() {
        return rightPressed;
    }

    public boolean getLeftTapped() {
        return leftTapped;
    }

    public boolean getRightTapped() {
        return rightTapped;
    }

        public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Point getMousePos() {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mousePos, gamePanel);
        return mousePos;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void updateMouseTapped() {
        leftTapped = false;
        rightTapped = false;
        if (leftPressed && !lastLeftPressed) {
            leftTapped = true;
        }
        if (rightPressed && !lastRightPressed) {
            rightTapped = true;
        }
        lastLeftPressed = leftPressed;
        lastRightPressed = rightPressed;
    }
}
