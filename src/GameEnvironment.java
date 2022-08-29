import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * A GameEnvironment class.
 */
public class GameEnvironment {
    /**
     * the GameEnviromnment Class contain the information of the game like the collidableList (the block or the paddle)
     * also contain the game board information.
     */
    private List<Collidable> collidableList;
    //The size and dimensions of the game.
    private Point upperLeft;
    private double width;
    private double height;

    //constructor

    /**
     * Constructor.
     *
     * @param collidableList a list of all the collidable in the game
     * @param upperLeft      the upper left location of the game board
     * @param width          of the board game
     * @param height         of the game board
     */
    public GameEnvironment(List<Collidable> collidableList, Point upperLeft, double width, double height) {
        this.collidableList = collidableList;
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
    }

    /**
     * Constructor.
     *
     * @param upperLeft the upper left location of the game board.
     * @param width     of the board game
     * @param height    of the game board
     */
    public GameEnvironment(Point upperLeft, double width, double height) {
        this.collidableList = new ArrayList<>();
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the new collidable that need to be added
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * remove Collidable from the Collidable list.
     *
     * @param c - that need to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }
    // accessors

    /**
     * Returns the list of collidable item (block) of the game.
     *
     * @return list
     */
    public List<Collidable> getCollidList() {
        return this.collidableList;
    }

    /**
     * Returns upperleft point of the game board.
     *
     * @return Point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns width  of the game board.
     *
     * @return double
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns height  of the game board.
     *
     * @return double
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null. Else,
     * return the information about the closest collision that is going to occur.
     *
     * @param trajectory that represent the line of the ball (his current position and his next position)
     * @return Collisioninfo - an objects that hold the information  about the hit
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable minimumCollied = null;
        Point min = trajectory.end();
        boolean checker = false;
        //move over the collision on the board and find the closest intersection in the line to the start point line
        for (Collidable colid : this.collidableList) {
            if (trajectory.closestIntersectionToStartOfLine(colid.getCollisionRectangle()) == null) {
                continue;
            }
            Point checkPoint = new Point(trajectory.closestIntersectionToStartOfLine(colid.getCollisionRectangle()));
            if (checkPoint.distance(trajectory.start()) <= min.distance(trajectory.start())) {
                min = checkPoint;
                minimumCollied = colid;
                checker = true;
            }
            //check if is the same point as the end point (check with epsilon)
            if (checkPoint.equals(min)) {
                min = checkPoint;
                minimumCollied = colid;
                checker = true;
            }
        }
        // mean that there is a hit with a block
        if (checker) {
            CollisionInfo info = new CollisionInfo(min, minimumCollied);
            return info;
        } else {
            return null;
        }
    }

    /**
     * drew on the giu the block of the game environment.
     *
     * @param d - the DrawSurface form the main
     */
    public void drewBlock(DrawSurface d) {

        for (Collidable colli : this.collidableList) {
            Block block = (Block) colli;
            block.drawOn(d);
        }
    }
}