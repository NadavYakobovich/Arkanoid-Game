import biuoop.DrawSurface;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * the Sprite interface.
 */
public interface Sprite {
    /**
     * The interface includes all the common features required for sliding objects on the board.
     */

    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}