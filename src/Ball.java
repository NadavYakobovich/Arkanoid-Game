import biuoop.DrawSurface;

import java.awt.Color;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * A ball Class.
 */
public class Ball implements Sprite {
    /*
     * the balls class represent a ball with his properties
     * the color, the center point of the ball, the color, and the velocity.
     */

    //the details of the frame
    private GameEnvironment environment;

    private Point point;
    private int radius;
    private Color color;
    private Velocity velocity;

    /**
     * Constructor.
     *
     * @param center      a point
     * @param r           the radios of the point
     * @param color       the color of the ball
     * @param environment the environment where the ball need to be enter
     */
    public Ball(Point center, int r, Color color, GameEnvironment environment) {
        this.point = center;
        this.radius = r;
        this.color = color;
        this.environment = environment;
    }

    /**
     * Constructor.
     *
     * @param x     the x coordinate of the point
     * @param y     the x coordinate of the point
     * @param r     the radios of the point
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, Color color) {
        this.point = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructor.
     *
     * @param x           the x coordinate of the point
     * @param y           the x coordinate of the point
     * @param r           the radios of the point
     * @param color       the color of the ball
     * @param environment for the environment that the ball in it
     */
    public Ball(int x, int y, int r, Color color, GameEnvironment environment) {
        this.point = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.environment = environment;
    }

    /**
     * Constructor.
     *
     * @param x     the x coordinate of the point
     * @param y     the x coordinate of the point
     * @param r     the radios of the point
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        this.point = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructor - build a new ball that copy other ball.
     *
     * @param ball the ball that need to be copy
     */
    public Ball(Ball ball) {
        this.point = ball.point;
        this.radius = ball.radius;
        this.color = ball.color;
        this.velocity = ball.velocity;
        this.environment = ball.environment;
    }
    // accessors

    /**
     * Returns the x of the the point.
     *
     * @return the x of th point.
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * Returns the y of the the point.
     *
     * @return the y of th point.
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * Returns the radius  of the the point.
     *
     * @return the radius point.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Returns the color  of the the point.
     *
     * @return the color of the point.
     */
    public Color getColor() {

        return this.color;
    }

    /**
     * set a new game environment fot the ball.
     *
     * @param envi environment for the game
     */
    public void setGameEnvironment(GameEnvironment envi) {
        this.environment = envi;
    }

    /**
     * Returns the velocity  of the the point.
     *
     * @return the velocity of the point.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    // set method.

    /**
     * set a new velocity to the objects.
     *
     * @param v (velocity object)
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set the velocity of the point by dx and dy values.
     *
     * @param dx - rate of change of x
     * @param dy - rate of change of y
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }


    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface - will the ball will be drew.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.point.getX(), (int) this.point.getY(), this.radius);
    }

    /**
     * move the ball by the time he need in the game run.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * the function check where the ball hit the block and change.
     * its center point to the right coordinate after the hit.
     *
     * @param collision - contain the date about the hit, like the block size and place on the board
     * @return the hit point location (contain x and y)
     */
    private Point hitPoint(CollisionInfo collision) {

        double x;
        double y;
        Rectangle rect = collision.collisionObject().getCollisionRectangle();
        //the ball hit the right corner of the block
        if (collision.collisionPoint().getX() == rect.getUpperLeft().getX() + rect.getWidth()) {
            x = collision.collisionPoint().getX() + this.radius;
            y = collision.collisionPoint().getY();
            return new Point(x, y);
        }
        //the ball hit the bottom of the block
        if (collision.collisionPoint().getY() == rect.getUpperLeft().getY() - rect.getHeight()) {
            x = collision.collisionPoint().getX();
            y = collision.collisionPoint().getY() - this.radius;
            return new Point(x, y);
        }
        //the ball hit the left of the block
        if (collision.collisionPoint().getX() == rect.getUpperLeft().getX()) {
            x = collision.collisionPoint().getX() - this.radius;
            y = collision.collisionPoint().getY();
            return new Point(x, y);
        }
        //the ball hit the top of the block
        if (collision.collisionPoint().getY() == rect.getUpperLeft().getY()) {
            x = collision.collisionPoint().getX();
            y = collision.collisionPoint().getY() + this.radius;
            return new Point(x, y);
        }
        return collision.collisionPoint();
    }

    /**
     * move the ball to his next point on the board by this velocity, if he hit a block (or a paddle) change his move
     * by the new velocity he get from the block he hit.
     */
    public void moveOneStep() {

        //check if the ball is going to hit one of the block
        //add 1 for the endPoint to check if  the ball  is going to hit just near the block and not right on the block
        double dx1 = 1 * this.radius;
        double dy1 = 1 * this.radius;
        if (this.velocity.getDx() < 0) {
            dx1 = -1 * this.radius;
        }
        if (this.velocity.getDy() < 0) {
            dy1 = -1 * this.radius;
        }

        Point endPoint = new Point(this.point.getX() + dx1, this.point.getY() + dy1);
        Line trajectory = new Line(this.point, endPoint);
        CollisionInfo info = this.environment.getClosestCollision(trajectory);
        if (info != null) {
            this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
        }
        this.point = this.getVelocity().applyToPoint(this.point);
    }

    /**
     * add the ball to the game , by add him to the Sprite list.
     *
     * @param g the game class
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove the ball from the game.
     * @param g - the game that the ball need to be remove from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
