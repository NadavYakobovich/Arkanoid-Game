import biuoop.DrawSurface;
/**
 * id - 207984956.
 * name nadav yakobovich.
 * A Animation interface.
 */
public interface Animation {
    /**
     * drew the Animation on the screen.
     * @param d - that the Animation will use to drew
     */
    void doOneFrame(DrawSurface d);

    /**
     *  the condition that the animation should stop.
     * @return true or false
     */
    boolean shouldStop();
}