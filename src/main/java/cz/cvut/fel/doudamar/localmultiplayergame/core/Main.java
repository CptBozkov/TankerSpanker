package cz.cvut.fel.doudamar.localmultiplayergame.core;

import javax.swing.*;

public class Main {
    private static void createAndShowGUI() {
        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel(window);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}

