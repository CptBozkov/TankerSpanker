package cz.cvut.fel.doudamar.localmultiplayergame.scenes;

import cz.cvut.fel.doudamar.localmultiplayergame.core.*;
import cz.cvut.fel.doudamar.localmultiplayergame.widgets.Button;
import cz.cvut.fel.doudamar.localmultiplayergame.widgets.Widget;

import java.awt.*;
import java.util.ArrayList;

public class Menu implements Scene{
    ScenesManager scenesManager;

    ArrayList<Widget> widgets = new ArrayList<>();

    public Menu(ScenesManager scenesManager){
        this.scenesManager = scenesManager;

        Button serverButton = new Button(Const.SCREEN_SIZE_X/2-Const.MENU_BUTTON_WIDTH-Const.MENU_BUTTON_SPACING/2, 100, Const.MENU_BUTTON_WIDTH, Const.MENU_BUTTON_HEIGHT, "SERVER");
        Button clientButton = new Button(Const.SCREEN_SIZE_X/2+Const.MENU_BUTTON_SPACING/2, 100, Const.MENU_BUTTON_WIDTH, Const.MENU_BUTTON_HEIGHT, "CLIENT");

        serverButton.setActionListener(new NetWorker(scenesManager, Const.NetWorkerType.SERVER));
        clientButton.setActionListener(new NetWorker(scenesManager, Const.NetWorkerType.CLIENT));

        widgets.add(serverButton);
        widgets.add(clientButton);
    }

    public void update(){
        for (Widget w: widgets){
            w.update(scenesManager.getMouseManager());
        }
    }

    public void draw(Graphics2D g2){
        for (Widget w: widgets){
            w.draw(g2);
        }
    }
}
