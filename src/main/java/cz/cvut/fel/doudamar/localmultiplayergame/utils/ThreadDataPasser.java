package cz.cvut.fel.doudamar.localmultiplayergame.utils;

public class ThreadDataPasser {
    public boolean getRun() {
        return run;
    }

    public boolean getDrawGame() {
        return drawGame;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public void setDrawGame(boolean drawGame) {
        this.drawGame = drawGame;
    }

    public void disconnect() {
        this.run = false;
    }

    public void startGame() {
        this.run = true;
        this.drawGame = true;
    }

    boolean run = true;
    boolean drawGame = false;
}
