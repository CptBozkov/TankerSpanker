package cz.cvut.fel.doudamar.localmultiplayergame.core;

import cz.cvut.fel.doudamar.localmultiplayergame.utils.DataPasser;
import cz.cvut.fel.doudamar.localmultiplayergame.world.Projectile;
import cz.cvut.fel.doudamar.localmultiplayergame.world.World;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class SocketWorker implements Runnable{
    ScenesManager scenesManager;
    World world;
    Const.NetWorkerType netWorkerType;

    FPSManager fpsManager = new FPSManager(30);

    ServerSocket serverSocket;
    Socket socket;

    ObjectInputStream ois;
    ObjectOutputStream oos;

    DataPasser myDP;
    DataPasser enemyDP;

    public SocketWorker(ScenesManager scenesManager, World world, Const.NetWorkerType netWorkerType){
        this.scenesManager = scenesManager;
        this.world = world;
        this.netWorkerType = netWorkerType;
        this.myDP = world.getMyDP();
        this.enemyDP = world.getEnemyDP();
    }

    private void initialize(){
        try {
            System.out.println(netWorkerType);
            if (netWorkerType == Const.NetWorkerType.SERVER) {
                serverSocket = new ServerSocket(Const.PORT);
                scenesManager.getThreadDP().startGame();
                socket = serverSocket.accept();
            } else if (netWorkerType == Const.NetWorkerType.CLIENT) {
                socket = new Socket("localhost", Const.PORT);
                scenesManager.getThreadDP().startGame();
            }
            System.out.println("connected");
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            if (netWorkerType == Const.NetWorkerType.SERVER) {
                ois = new ObjectInputStream(is);
                oos = new ObjectOutputStream(os);
            } else if (netWorkerType == Const.NetWorkerType.CLIENT) {
                oos = new ObjectOutputStream(os);
                ois = new ObjectInputStream(is);
            }
            System.out.println("init done");
        } catch (IOException e){
            System.out.println("Could not initialize!");
            scenesManager.getThreadDP().disconnect();
        }
    }

    private void updateEnemyDP(DataPasser newDP){
        world.getEnemyDP().setPos(newDP.getPos().copy());
        world.getEnemyDP().setRotation(newDP.getRotation());
        world.getEnemyDP().setProjectile(newDP.getProjectile());
        world.getEnemyDP().setAlive(newDP.isAlive());
    }

    private void send() {
        try {
            oos.writeUnshared(world.getMyDP());
            oos.flush();
            world.resetMyDP();
            updateEnemyDP((DataPasser) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("connection closed");
            scenesManager.getThreadDP().disconnect();
        }
    }

    private void receive() {
        try {
            updateEnemyDP((DataPasser) ois.readObject());
            oos.writeUnshared(world.getMyDP());
            oos.flush();
            world.resetMyDP();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("connection closed");
            scenesManager.getThreadDP().disconnect();
        }
    }

    private void endConnection(){
        try {
            if (ois != null) {
                ois.close();
            }
            if (oos != null) {
                oos.close();
            }
        } catch (IOException e){
            System.out.println("Could not close ois and oos!");
        }
        try {
            if (socket != null) {
                socket.close();
            }
            if (serverSocket != null){
                serverSocket.close();
            }
        } catch (IOException e){
            System.out.println("Could not close the connection!");
        }
        scenesManager.setActiveScene(0);
    }

    @Override
    public void run() {
        initialize();
        if (netWorkerType == Const.NetWorkerType.SERVER){
            while (scenesManager.getThreadDP().getRun()){
                receive();
                fpsManager.manageFPS();
            }
            endConnection();
        } else if (netWorkerType == Const.NetWorkerType.CLIENT){
            while (scenesManager.getThreadDP().getRun()){
                send();
                fpsManager.manageFPS();
            }
            endConnection();
        }
    }
}
