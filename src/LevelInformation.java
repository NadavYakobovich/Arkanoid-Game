import java.util.List;
/**
 * id - 207984956.
 * name nadav yakobovich.
 * A LevelInformation interface.
 */
public interface LevelInformation {
    /**
     *
     * @return the number of balls in the game
     */
    int numberOfBalls();

    /**
     * he initial velocity of each ball.
     * @return a list that contain the velocities of the balls in the level.
     */
    List<Velocity> initialBallVelocities();

    /**
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return the level name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return the sprite that is the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return list of Blocks
     */
    List<Block> blocks();

    /**
     * // Number of blocks that should be removed before the level is considered to be "cleared".
     * @return the number of block
     */
    int numberOfBlocksToRemove();
}
