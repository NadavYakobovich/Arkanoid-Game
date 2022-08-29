
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * A GameLevel class.
 */
public class GameLevel implements Animation {
    /**
     * The Game class represents an object that holds all the objects on the board (like ball and block).
     * the object can initialize the game and run it
     */
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private Counter counter;
    private BlockRemover blockListener;
    private BallRemover ballRemover;
    private Counter ballCounter;
    private ScoreTrackingListener scoreTrackingListener;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * constructor.
     *
     * @param levelInformation the information of the level that the gamelevel need to build by.
     * @param keyboard         where the game get the input of the player
     * @param runner           an object that run animation on the screen
     * @param score            contain the score of the player in the current level and update it
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard,
                     AnimationRunner runner, Counter score) {
        this.runner = runner;
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;
        this.gui = runner.getGui();
        this.score = score;
    }

    /**
     * @return the number of balls in the game
     */
    public int getBallCounter() {
        return ballCounter.getValue();
    }

    /**
     * @return the number of block in the level
     */
    public Counter getCounter() {
        return counter;
    }

    /**
     * add a new Colliadable to the game.
     *
     * @param c a collidable ( like block) to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a new sprite to the game.
     *
     * @param s a Sprite ( like ball or paddle) to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove the Collidable from the game.
     *
     * @param c the coli that need to be remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);

    }

    /**
     * remove the sprite from the game.
     *
     * @param s the sprite that need to be remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        //this.gui = new GUI("Ass6Game", 800, 600);
        this.sleeper = new Sleeper();
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment(new Point(0, 600), 800, 600);
        PrintingHitListener listener = new PrintingHitListener();
        this.counter = new Counter();
        this.ballCounter = new Counter();
        this.blockListener = new BlockRemover(this, counter);
        this.ballRemover = new BallRemover(this, ballCounter);
        this.scoreTrackingListener = new ScoreTrackingListener(score);

        //add the backGrond to the spirites
        this.sprites.addSprite(this.levelInformation.getBackground());

        //game Score display
        Block scoreBlock = new Block(0, 0, 800, 20, Color.LIGHT_GRAY);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreBlock, this.score, levelInformation.levelName());
        this.addSprite(scoreIndicator);

        //game borders
        Block left = new Block(0, 40, 20, 600, Color.gray);
        Block top = new Block(0, 20, 800, 20, Color.gray);
        Block right = new Block(780, 40, 20, 600, Color.gray);

        // the block that will "kill" the ball if the hit it
        Block deathblock = new Block(0, 600, 800, 20, Color.gray);
        deathblock.addHitListener(ballRemover);

        //add the block to the game
        for (Block b1 : this.levelInformation.blocks()) {
            b1.addHitListener(listener);
            b1.addHitListener(blockListener);
            b1.addHitListener(scoreTrackingListener);
            b1.addToGame(this);
        }
        //add the block to the counter of the block
        this.counter.increase(this.levelInformation.numberOfBlocksToRemove());

        //add the object to the game
        left.addToGame(this);
        top.addToGame(this);
        right.addToGame(this);
        deathblock.addToGame(this);

        //creat a fuddle
        Rectangle puddleBlock = new Rectangle(new Point(this.environment.getWidth()
                / 2 - levelInformation.paddleWidth() / 2, 560), levelInformation.paddleWidth(), 20);
        Paddle paddle = new Paddle(puddleBlock, gui, this.environment, Color.red, levelInformation.paddleSpeed());
        paddle.addToGame(this);
        paddle.addHitListener(listener);

        //add the balls to the game
        for (Velocity ballVel : levelInformation.initialBallVelocities()) {
            Ball ball1 = new Ball(400, 540, 5, Color.white, this.environment);
            ball1.setVelocity(ballVel);
            ball1.addToGame(this);
        }
        this.ballCounter.increase(levelInformation.numberOfBalls());
    }

    /**
     * @return true or false if the level should continue.
     */
    public boolean shouldStop() {
        //if there is no more block exit the game and close the program.
        if (this.ballCounter.getValue() == 0) {
            return true;
        }
        if (this.counter.getValue() == 0) {
            return true;
        }
        return false;
    }

    /**
     * drew the spirits of the level on the scree.(that will show by the runner later).
     * if the player press p, drew a new screen of pauseScreen
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run((new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen(this.keyboard))));
        }
    }

    /**
     * run the level on the screen
     * first show 3 ...2...1  count and later run the level on the screen.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

}