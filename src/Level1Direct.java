import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * A Level1Direct class.
 */
public class Level1Direct implements LevelInformation {
    private int blockCounter;

    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        //the speed for the balls
        final int speed = 5;
        //set velocity for each balls
        velocityList.add(Velocity.fromAngleAndSpeed(0, speed));
        return velocityList;
    }

    /**
     * @return the speed of the paddle
     */
    @Override
    public int paddleSpeed() {
        //the speed fo the paddle (right or left move)
        return 4;
    }

    /**
     * @return the widht of the paddle
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * @return the name of the level that will display in the top of the screen
     */
    @Override
    public String levelName() {
        return "Direct hit";

    }

    /**
     * @return change the background of the level.
     */
    @Override
    public Sprite getBackground() {
        Color color = new Color(241, 221, 119);
        Rectangle rec = new Rectangle(new Point(0, 0), 800, 600);
        Block background = new Block(rec, color);
        return background;
    }

    /**
     * @return list of the blocks in the game.
     */
    @Override
    public List<Block> blocks() {
        this.blockCounter = 0;
        List<Block> blockList = new ArrayList<>();
        //block
        Block b1 = new Block(370, 100, 50, 30, Color.cyan);
        blockList.add(b1);
        this.blockCounter = this.blockCounter + 1;
        return blockList;
    }

    /**
     * @return the number of block that need to hit to end the game
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.blockCounter;
    }
}