package cz.cvut.fel.doudamar.localmultiplayergame.core;

import cz.cvut.fel.doudamar.localmultiplayergame.scenes.Game;
import cz.cvut.fel.doudamar.localmultiplayergame.widgets.ActionListener;
import cz.cvut.fel.doudamar.localmultiplayergame.world.World;

public class NetWorker implements ActionListener {
    ScenesManager scenesManager;

    Const.NetWorkerType netWorkerType;

    public NetWorker(ScenesManager scenesManager, Const.NetWorkerType netWorkerType){
        this.scenesManager = scenesManager;
        this.netWorkerType = netWorkerType;
    }

    @Override
    public void actionPerformed() {
        Game game = new Game(scenesManager, this);
        scenesManager.addScene(1, game);
        scenesManager.setActiveScene(1);
    }

    public void createConnection(World world){
        SocketWorker socketWorker = new SocketWorker(scenesManager, world, netWorkerType);
        Thread socketWorkerThread = new Thread(socketWorker);
        socketWorkerThread.start();
    }

    public Const.NetWorkerType getNetWorkerType(){
        return netWorkerType;
    }
}
