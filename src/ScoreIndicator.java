import biuoop.DrawSurface;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * A ScoreIndicator Class.
 */
public class ScoreIndicator implements Sprite {
    private Block block;
    private Counter score;
    private String levelName;

    /**
     * @param block that show on the screen on the top on screen
     * @param score contain the score of the player that will be display
     */
    public ScoreIndicator(Block block, Counter score) {
        this.block = block;
        this.score = score;
    }

    /**
     * constructor.
     *
     * @param block     that show on the screen on the top on screen
     * @param score     contain the score of the player that will be display
     * @param levelName the level of the game
     */
    public ScoreIndicator(Block block, Counter score, String levelName) {
        this.block = block;
        this.score = score;
        this.levelName = levelName;
    }

    /**
     * drew the the score block on the top on the screen.
     *
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
        d.drawText(300, 17, "Score: " + Integer.toString(score.getValue()), 15);
        d.drawText(500, 17, "Level Name :" + this.levelName, 15);
    }

    /**
     * show the block of the borad by the gui draw.
     */
    @Override
    public void timePassed() {
        this.block.timePassed();
    }
}