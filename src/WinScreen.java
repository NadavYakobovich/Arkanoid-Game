import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
/**
 * id - 207984956.
 * name nadav yakobovich.
 * A class WinScreen.
 */
public class WinScreen implements Animation {
    private KeyboardSensor keyboard;
    private Counter score;
    private AnimationRunner runner;

    /**
     * @param k     keySensor
     * @param score of the game
     * @param run   the animation runner
     */
    public WinScreen(KeyboardSensor k, Counter score, AnimationRunner run) {
        this.keyboard = k;
        this.score = score;
        this.runner = run;
    }
    /**
     * drew the Animation on the screen.
     * show the score of the player the end of the screen
     * @param d - that the Animation will use to drew
     */
    public void doOneFrame(DrawSurface d) {
        Color color = new Color(151, 183, 220);
        Rectangle rec = new Rectangle(new Point(0, 0), 800, 600);
        Block background = new Block(rec, color);
        background.drawOn(d);
        d.drawText(40, d.getHeight() / 2, "You Win! Your score is " + Integer.toString(this.score.getValue())
                + " press space to continue", 25);
    }
    /**
     *  the condition that the animation should stop.
     * @return true or false
     */
    public boolean shouldStop() {
        return true;
    }
}