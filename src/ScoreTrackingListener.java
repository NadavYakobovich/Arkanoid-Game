
/**
 * id - 207984956.
 * name nadav yakobovich.
 * A ScoreTrackingListener class.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private GameEnvironment environment;

    /**
     * constructor.
     *
     * @param scoreCounter contain the score of the player.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }


    /**
     * add score for ever hit on the block (5 point).
     *
     * @param beingHit the coliadble that is hit
     * @param hitter   - the object (ball) that hit the coliadble
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        this.currentScore.increase(5);
    }
}