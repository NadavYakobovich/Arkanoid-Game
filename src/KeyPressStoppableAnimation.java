import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * id - 207984956.
 * name nadav yakobovich.
 * A KeyPressStoppableAnimation class.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     *
     * @param sensor    - for get the input from the user
     * @param key       - the string that the user will need to enter
     * @param animation that will show on the screen
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        this.isAlreadyPressed = false;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
    // ...
    // think about the implementations of doOneFrame and shouldStop.
}