/**
 * id - 207984956.
 * name nadav yakobovich.
 * A CollisionInfo class.
 */
public class CollisionInfo {
    /*
     * the CollidableInfo class Represents an object that returns information when a collision
     *  occurs between a collision object and a sphere
     */
    private Point collistionPoint;
    private Collidable collidable;

    //constructor

    /**
     * constructor.
     *
     * @param point      the location of the collision
     * @param collidable the object that involve in the collision
     */
    CollisionInfo(Point point, Collidable collidable) {
        this.collistionPoint = point;
        this.collidable = collidable;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return the point of the collision
     */
    public Point collisionPoint() {
        return this.collistionPoint;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return the object involve
     */
    public Collidable collisionObject() {
        return collidable;
    }
}