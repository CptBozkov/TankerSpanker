package cz.cvut.fel.doudamar.localmultiplayergame.core;

/**
 * Keeps constant FPS.
 */
public class FPSManager {
    double drawInterval;
    double nextDrawTime;

    public FPSManager(int fps) {
        this.drawInterval = 1000000000f / fps;
        this.nextDrawTime = System.nanoTime() + drawInterval;
    }

    /**
     * keeps constant FPS
     */
    public void manageFPS() {
        // keeps the fps and given value
        try {
            double remainingTime = (nextDrawTime - System.nanoTime()) / 1000000;
            if (remainingTime < 0) {
                remainingTime = 0;
            }
            Thread.sleep((long) remainingTime);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        nextDrawTime += drawInterval;
    }
}
