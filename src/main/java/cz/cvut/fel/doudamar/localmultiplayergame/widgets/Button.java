package cz.cvut.fel.doudamar.localmultiplayergame.widgets;

import cz.cvut.fel.doudamar.localmultiplayergame.core.*;

import java.awt.*;

public class Button implements Widget{
    int x;
    int y;
    int w;
    int h;
    Color c;

    Color baseColor;
    Color hoveredColor;
    Color pressedColor;
    String text;

    Font font;

    ActionListener actionListener;

    public Button(int posX, int posY, int width, int height, String text){
        x = posX;
        y = posY;
        w = width;
        h = height;
        baseColor = Color.cyan;
        hoveredColor = Color.blue;
        pressedColor = Color.green;
        this.text = text;

        font = new Font("Dialog", Font.BOLD, Const.FONT_SIZE);
    }

    public void setActionListener(ActionListener actionListener){
        this.actionListener = actionListener;
    }

    public void update(MouseManager mouseManager){
        Point p = mouseManager.getMousePos();
        if (p.getX() >= x && p.getX() <= x+w && p.getY() >= y && p.getY() <= y+h){
            c = hoveredColor;
            if (mouseManager.getLeftTapped()){
                actionListener.actionPerformed();
            }
            if (mouseManager.getLeftPressed()){
                c = pressedColor;
            }
        } else {
            c = baseColor;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(c);
        g2.fillRoundRect(x, y, w, h, Const.BUTTON_ARC_SIZE, Const.BUTTON_ARC_SIZE);

        g2.setColor(Color.black);
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        int sWidth = g2.getFontMetrics().stringWidth(text);
        int sHeight = g2.getFontMetrics().getHeight();
        g2.drawString(text,x+w/2 - sWidth/2,y+h/2+sHeight/4);
    }
}
