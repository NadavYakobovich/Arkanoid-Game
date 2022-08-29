import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * A Level2 class.
 */
public class Level2 implements LevelInformation {
    private int blockCounter;

    /**
     * @return return the the number of balls in the level
     */
    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    /**
     * @return the list contain the velocity of hte ball that need to be in the game.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        //the speed for the balls
        final int speed = this.paddleSpeed();
        //set velocity for each balls
        velocityList.add(Velocity.fromAngleAndSpeed(320, speed));
        velocityList.add(Velocity.fromAngleAndSpeed(330, speed));
        velocityList.add(Velocity.fromAngleAndSpeed(340, speed));
        velocityList.add(Velocity.fromAngleAndSpeed(350, speed));
        velocityList.add(Velocity.fromAngleAndSpeed(20, speed));
        velocityList.add(Velocity.fromAngleAndSpeed(30, speed));
        velocityList.add(Velocity.fromAngleAndSpeed(40, speed));
        velocityList.add(Velocity.fromAngleAndSpeed(50, speed));

        return velocityList;
    }

    /**
     * @return the speed of the paddle
     */
    @Override
    public int paddleSpeed() {
        //the speed fo the paddle (right or left move)
        return 5;
    }

    /**
     * @return the widht of the paddle
     */
    @Override
    public int paddleWidth() {
        return 250;
    }

    /**
     * @return the name of the level that will display in the top of the screen
     */
    @Override
    public String levelName() {
        return "Wide Easy";

    }

    /**
     * @return drew the background of the level
     */
    @Override
    public Sprite getBackground() {
        Color color = new Color(2, 90, 190);
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
        for (int i = 20; i < 780; i = i + 76) {
            Block b1 = new Block(i, 200, 76, 30, new Color(215, 35, 35));
            blockList.add(b1);
            this.blockCounter = this.blockCounter + 1;
        }

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