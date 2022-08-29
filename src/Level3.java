import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * A Level3 class.
 */
public class Level3 implements LevelInformation {
    private int blockCounter;

    /**
     * @return return the number of balls in the game.
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
        velocityList.add(Velocity.fromAngleAndSpeed(40, speed));

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
        return 200;
    }

    /**
     * @return the name of the level that will display in the top of the screen
     */
    @Override
    public String levelName() {
        return "Medium level";

    }

    /**
     * @return drew the background of the level
     */
    @Override
    public Sprite getBackground() {

        Color color = new Color(44, 72, 193);
        Rectangle rec = new Rectangle(new Point(0, 0), 800, 600);
        Block background = new Block(rec, color);
        return background;
    }

    /**
     * @return list of the blocks in the level.
     */
    @Override
    public List<Block> blocks() {
        this.blockCounter = 0;
        List<Block> blockList = new ArrayList<>();
        //block
        for (int i = 230; i < 780; i = i + 50) {
            Block b1 = new Block(i, 170, 50, 30, Color.cyan);
            blockList.add(b1);
            this.blockCounter = this.blockCounter + 1;
        }
        for (int i = 330; i < 780; i = i + 50) {
            Block b1 = new Block(i, 200, 50, 30, Color.pink);
            blockList.add(b1);
            this.blockCounter = this.blockCounter + 1;
        }
        for (int i = 430; i < 780; i = i + 50) {
            Block b1 = new Block(i, 230, 50, 30, Color.red);
            blockList.add(b1);
            this.blockCounter = this.blockCounter + 1;
        }
        for (int i = 530; i < 780; i = i + 50) {
            Block b1 = new Block(i, 260, 50, 30, Color.green);
            blockList.add(b1);
            this.blockCounter = this.blockCounter + 1;
        }
        for (int i = 630; i < 780; i = i + 50) {
            Block b1 = new Block(i, 290, 50, 30, Color.yellow);
            blockList.add(b1);
            this.blockCounter = this.blockCounter + 1;
        }
        for (int i = 730; i < 780; i = i + 50) {
            Block b1 = new Block(i, 320, 50, 30, Color.orange);
            blockList.add(b1);
            this.blockCounter = this.blockCounter + 1;
        }
        return blockList;
    }

    /**
     * number of block to remove - in this level is set it to 10.
     *
     * @return the number of block to remove
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}