import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * A PauseScreen class.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;

    /**
     * constructor.
     *
     * @param k - the sensor of the keyboard
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
    }

    /**
     * drew the Animation on the screen.
     *
     * @param d - that the Animation will use to drew
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        Color color = new Color(151, 220, 166);
        Rectangle rec = new Rectangle(new Point(0, 0), 800, 600);
        Block background = new Block(rec, color);
        background.drawOn(d);
        d.drawText(20, d.getHeight() / 2, "paused -- press space to continue ", 25);
    }

    /**
     * the condition that the animation should stop.
     *
     * @return true or false
     */
    public boolean shouldStop() {
        return true;
    }
}