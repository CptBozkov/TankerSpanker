package cz.cvut.fel.doudamar.localmultiplayergame.widgets;

import cz.cvut.fel.doudamar.localmultiplayergame.core.MouseManager;

import java.awt.*;

public interface Widget {
    public void update(MouseManager mouseManager);

    public void draw(Graphics2D g2);
}
