import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * A CountdownAnimation class.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private double screenTime;

    /**
     * @param numOfSeconds how mach time to wait
     * @param countFrom    form where to start
     * @param gameScreen   the object that there are in the game before start.
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.screenTime = this.numOfSeconds / countFrom;
    }

    /**
     * The CountdownAnimation will display the given gameScreen,for numOfSeconds seconds, and on top of them it
     * will show a countdown from countFrom back to 1, where each number will appear on the
     * screen for (numOfSeconds / countFrom) seconds, before it is replaced with the next one.
     *
     * @param d the DrawSurface object to drew on the screen the animation
     */
    public void doOneFrame(DrawSurface d) {
        if (this.shouldStop()) {
            return;
        }
        Sleeper sleeper = new Sleeper();
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.black);
        if (this.countFrom > 0) {
            d.drawText(370, 300, Integer.toString(this.countFrom), 100);
        }
        if (countFrom != 3) {
            sleeper.sleepFor((long) (1000 * screenTime));
        }
        this.countFrom = countFrom - 1;
    }

    /**
     * @return true or false of the Countdown animation should stop
     */
    public boolean shouldStop() {
        if (this.countFrom == -1) {
            return true;
        }
        return this.stop;
    }
}