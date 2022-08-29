import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A Block class.
 * id - 207984956.
 * name nadav yakobovich.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /*
     * the block class represent the block object on the board that the ball can hit.
     */
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    //constructor

    /**
     * constructor - only rec get. the color def is black.
     *
     * @param rectangle the shape of the block  (coordinate)
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.color = Color.black;
    }

    /**
     * constructor.
     *
     * @param rectangle the shape of the block  (coordinate)
     * @param color     the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();

    }

    /**
     * constructor.
     *
     * @param upperleft the upper left Location of the block in the board (x and y)
     * @param color     the color of the block
     * @param height    the height of the block
     * @param width     of the block
     */
    public Block(Point upperleft, double width, double height, Color color) {
        this.rectangle = new Rectangle(upperleft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();

    }

    /**
     * constructor.
     *
     * @param x      Location of the block in the board
     * @param y      Location of the block in the board
     * @param color  the color of the block
     * @param height the height of the block
     * @param width  of the block
     */
    public Block(double x, double y, double width, double height, Color color) {
        Point upperleft = new Point(x, y);
        this.rectangle = new Rectangle(upperleft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();

    }

    // accessors

    /**
     * Returns the rectangle object of the block.
     *
     * @return rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Returns the color of the block.
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * drew the shape of the rectangle of the block in color.
     *
     * @param d the drewSurface to the drew
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * show the block of the borad by the gui draw.
     */
    public void timePassed() {
    }

    /**
     * return to the ball a new velocity by the location of the hit with the block.
     * for exmple if the ball hit the right side of the block, the function return a new velocity with
     * that the change in x , will be -1*x - so the ball move now to the left.
     *
     * @param collisionPoint  the location of the hit.
     * @param currentVelocity the velocity of the ball.
     * @param hitter          the ball that hit the block
     * @return a new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        //the hit is on the top of the block
        if (collisionPoint.getY() == this.rectangle.getUpperLeft().getY()) {
            newVelocity.setDy(-1 * currentVelocity.getDy());
        }

        //the hit is on the bottom of the block
        if (collisionPoint.getY() == (this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight())) {
            newVelocity.setDy(-1 * currentVelocity.getDy());
        }

        //the hit is on the left of the block
        if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX()) {
            newVelocity.setDx(-1 * currentVelocity.getDx());
        }

        //the hit is on the right of the block
        if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX() + rectangle.getWidth()) {
            newVelocity.setDx(-1 * currentVelocity.getDx());
        }
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * add the block to the game , by add him to the Sprite list and to the Collidable list.
     *
     * @param g the game class
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * remove this block from the game.
     *
     * @param gameLevel - the game that the game is on.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
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
            hl.hitEvent(this, hitter);
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