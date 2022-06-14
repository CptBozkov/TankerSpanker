package cz.cvut.fel.doudamar.localmultiplayergame.scenes;

import cz.cvut.fel.doudamar.localmultiplayergame.core.ScenesManager;

import java.awt.*;

public interface Scene {
    ScenesManager s = null;

    public void update();

    public void draw(Graphics2D g2);
}
