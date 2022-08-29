/**
 * id - 207984956.
 * name nadav yakobovich.
 * A ball Class.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param gameLevel          that the the object of the class need to enter
     * @param removedBlocks the counter that hold the number of blaock in the game
     */
    public BallRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * remove the block from the game and update the counter of the blocks.
     *
     * @param beingHit the coliadble that is hit
     * @param hitter   - the object (ball) that hit the coliadble
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.removeSprite(hitter);
        this.remainingBlocks.decrease(1);
    }
}