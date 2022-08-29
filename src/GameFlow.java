import biuoop.KeyboardSensor;

import java.util.List;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * A GameFlow class.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    /**
     * constructor.
     *
     * @param ar AnimationRunner
     * @param ks KeyboardSensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
    }

    /**
     * the method get get an array with level information and run the level on the screen by the order in the array.
     * the user can press p to stop the game and space to resume it
     * also the method print if the user win or lost the show his score
     *
     * @param levels an array contain the information about the level that need to run on the game
     */
    public void runLevels(List<LevelInformation> levels) {
        // ...
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }
            //if there is the player end all the block in the lever get extra 100 points
            if (level.getCounter().getValue() == 0) {
                this.score.increase(100);
            }

            if (level.getBallCounter() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        new GameOverScreen(this.keyboardSensor, this.score, this.animationRunner)));
                //this.animationRunner.run(new GameOverScreen(this.keyboardSensor,this.score,this.animationRunner));
                this.animationRunner.getGui().close();
                return;
            }

        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                new WinScreen(this.keyboardSensor, this.score, this.animationRunner)));
        //this.animationRunner.run(new WinScreen(this.keyboardSensor, this.score, this.animationRunner));
        this.animationRunner.getGui().close();
        return;
    }
}

