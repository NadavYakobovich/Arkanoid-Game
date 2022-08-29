
/**
 * id - 207984956.
 * name nadav yakobovich.
 * A Collidable interface.
 */
public interface Collidable {
    /*
     * the Collidable The interface represents the required features of objects that can be collided with.
     */

    /**
     * Return the "collision shape" of the object.
     *
     * @return collision object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  the hit location with the collidable
     * @param currentVelocity the current velocity of the ball when its hit the collidable
     * @param hitter the object (ball) that hit the coliadble
     * @return new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}