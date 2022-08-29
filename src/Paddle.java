import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * the Paddle class
 */
public class Paddle implements Sprite, Collidable {
    /**
     * class paddle represent the paddle in the game that the ball can hit, the paddle can move and change the ball
     * angle of move.
     */
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rec;
    private GameEnvironment environment;
    private Velocity velocity;
    private Color color;
    private final double borderWidth = 20;
    private List<HitListener> hitListeners;
    //the speed for the paddle
    private int speed;

    //constructor

    /**
     * Constructor.
     *
     * @param rec         the block that is the paddle
     * @param gui         of the game
     * @param environment - the environment that the paddle in it
     * @param color       - the color of the paddle
     * @param speed - the speed of the paddle of in the game
     */
    public Paddle(Rectangle rec, GUI gui, GameEnvironment environment, Color color, int speed) {
        this.keyboard = gui.getKeyboardSensor();
        this.rec = rec;
        this.environment = environment;
        this.velocity = new Velocity(speed, 0);
        this.color = color;
        this.speed = speed;
        this.hitListeners = new ArrayList<HitListener>();

    }

    /**
     * move the paddle to the left.
     */
    public void moveLeft() {
        double x = this.rec.getUpperLeft().getX() - this.velocity.getDx();
        double y = this.rec.getUpperLeft().getY();
        if (x < borderWidth) {
            this.rec.setUpperLeft(borderWidth, y);
            return;
        }
        this.rec.setUpperLeft(x, y);
    }

    /**
     * move the paddle to the right.
     */
    public void moveRight() {
        double x = this.rec.getUpperLeft().getX() + this.velocity.getDx();
        double y = this.rec.getUpperLeft().getY();
        if (x + this.rec.getWidth() >= this.environment.getWidth() - borderWidth) {
            this.rec.setUpperLeft(this.environment.getWidth() - this.borderWidth - this.rec.getWidth(), y);

            return;
        }
        this.rec.setUpperLeft(x, y);
    }

    /**
     * get from the user keyboard input (left or right) and move the paddle by them.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * drew the paddle on the screen.
     *
     * @param d the DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    /**
     * return the rectangle of the paddle shape.
     *
     * @return the rectangle of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * return a new velocity that will enter to the ball after the hit with the paddle.
     * the paddle is divided with 5 part, and each has a new velocity to return
     *
     * @param collisionPoint  location of the hit on the paddle
     * @param currentVelocity the current velocity of the ball when hit the paddle
     * @param hitter          the object (ball) that hit the coliadble
     * @return velocity (a new to the ball)
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        //the width of 5th of the width block
        double part = this.rec.getWidth() / 5;
        double xHit = collisionPoint.getX();
        double xBLock = this.rec.getUpperLeft().getX();
        if (collisionPoint.getY() == this.rec.getUpperLeft().getY()) {

            // culucte the speed of thr ball in form dx and dy to speed
            double speedBall = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
            //part 1
            if (xBLock <= xHit && xHit < xHit + part) {
                currentVelocity = Velocity.fromAngleAndSpeed(300, speedBall);
            }
            //part 2
            if (xBLock + part < xHit && xHit < xHit + 2 * part) {
                currentVelocity = Velocity.fromAngleAndSpeed(330, speedBall);

            }
            //part 3
            if (xBLock + 2 * part < xHit && xHit < xHit + 3 * part) {
                this.hitRegular(collisionPoint, currentVelocity);
            }
            //part 4
            if (xBLock + 3 * part < xHit && xHit < xHit + 4 * part) {
                currentVelocity = Velocity.fromAngleAndSpeed(30, speedBall);
            }
            //part 5
            if (xBLock + 4 * part < xHit && xHit <= xBLock + 5 * part) {
                currentVelocity = Velocity.fromAngleAndSpeed(60, speedBall);
            }
        } else {
            //not his the width top part of the paddle
            return this.hitRegular(collisionPoint, currentVelocity);
        }
        this.notifyHit(hitter);
        return currentVelocity;

    }

    /**
     * return a new velocity to the ball after its hit the paddle (the hit is not on the top of the paddle).
     *
     * @param collisionPoint  the location of the hit with the paddle
     * @param currentVelocity the current velocity of the ball
     * @return new velocity after the hit
     */
    public Velocity hitRegular(Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        //the hit is on the left of the block
        if (collisionPoint.getX() == this.rec.getUpperLeft().getX()) {
            newVelocity.setDx(-1 * currentVelocity.getDx());
        }

        //the hit is on the right of the block
        if (collisionPoint.getX() == this.rec.getUpperLeft().getX() + rec.getWidth()) {
            newVelocity.setDx(-1 * currentVelocity.getDx());
        }
        return newVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game object that the faddle need to add
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * notify the hit listener list of the block that that ball hit the block.
     * deliver to the listener in the listeners list the information about the hit ball and the block
     *
     * @param hitter - the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(new Block(this.rec), hitter);
        }
    }

    /**
     * aAdd hl as a listener to hit events.
     *
     * @param hl the listener that need to be add to the
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener that need to be remove to the
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}

