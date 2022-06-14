package cz.cvut.fel.doudamar.localmultiplayergame.scenes;


import cz.cvut.fel.doudamar.localmultiplayergame.core.*;
import cz.cvut.fel.doudamar.localmultiplayergame.widgets.ActionListener;
import cz.cvut.fel.doudamar.localmultiplayergame.widgets.Button;
import cz.cvut.fel.doudamar.localmultiplayergame.widgets.Widget;
import cz.cvut.fel.doudamar.localmultiplayergame.world.World;


import java.awt.*;
import java.util.ArrayList;

public class Game implements Scene, ActionListener {
    ScenesManager scenesManager;
    NetWorker netWorker;
    World world;

    ArrayList<Widget> widgets = new ArrayList<>();

    public Game(ScenesManager scenesManager, NetWorker netWorker){
        this.scenesManager = scenesManager;
        this.netWorker = netWorker;
        world = new World(scenesManager, netWorker);

        String s = netWorker.getNetWorkerType() == Const.NetWorkerType.SERVER ? "Close Server" : "Disconnect";
        Button disconnectButton = new Button(125, Const.BAR_SPACING, 250, Const.BAR_HEIGHT-Const.BAR_SPACING*2, s);
        disconnectButton.setActionListener(this);
        widgets.add(disconnectButton);
    }

    @Override
    public void update() {
        for (Widget w: widgets){
            w.update(scenesManager.getMouseManager());
        }
        world.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        if (scenesManager.getThreadDP().getDrawGame()){
            for (Widget w: widgets){
                w.draw(g2);
            }
            world.draw(g2);
        }

    }

    @Override
    public void actionPerformed() {
        scenesManager.getThreadDP().disconnect();
    }
}
