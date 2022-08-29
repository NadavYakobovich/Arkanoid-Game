/**
 * id - 207984956.
 * name nadav yakobovich.
 * A Class PrintingHitListener.
 */
public class PrintingHitListener implements HitListener {
    /**
     * constructor.
     */
    public PrintingHitListener() {
    }

    /**
     * @param beingHit the coliadble that is hit
     * @param hitter   - the object (ball) that hit the coliadble
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}