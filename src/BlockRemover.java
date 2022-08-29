/**
 * id - 207984956.
 * name nadav yakobovich.
 * A BlockRemover class.
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param gameLevel that the block remover need to be add.
     * @param removedBlocks the counter that the blockRemover hold (contain the number of block in the game)
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the coliadble that is hit
     * @param hitter the object (ball) that hit the coliadble
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        this.gameLevel.removeCollidable(beingHit);
        this.gameLevel.removeSprite(beingHit);
        this.remainingBlocks.decrease(1);
    }
}