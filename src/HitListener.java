/**
 * id - 207984956.
 * name nadav yakobovich.
 * A HitListener class.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the coliadble that is hit
     * @param hitter   - the object (ball) that hit the coliadble
     */
    void hitEvent(Block beingHit, Ball hitter);
}
