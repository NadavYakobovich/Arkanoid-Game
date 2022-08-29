/**
 * id - 207984956.
 * name nadav yakobovich.
 * A Class Point.
 */
public class Point {
    /*
     * A class that represent a point that has x and y values
     */
    private double x;
    private double y;

    // constructor

    /**
     * Initialize a new point with x and y value.
     *
     * @param x double coordinators.
     * @param y double coordinators.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Initialize a new point with x and y value.
     *
     * @param newPoint  contain x and y double coordinators.
     */
    public Point(Point newPoint) {
        this.x = newPoint.getX();
        this.y = newPoint.getY();
    }

    /**
     * set a new x for the point.
     *
     * @param xNew , the position of x of the point
     */
    public void setX(double xNew) {
        this.x = xNew;
    }

    /**
     * set a new y for the point.
     *
     * @param yNew , the position of y of the point
     */
    public void setY(double yNew) {
        this.y = yNew;
    }


    /**
     * calculate the distance between 2 Point.
     *
     * @param other that is of type Point
     * @return return the distance of this point to the other point
     */
    public double distance(Point other) {
        double x2 = other.getX();
        double y2 = other.getY();
        return Math.sqrt(((this.x - x2) * (this.x - x2)) + ((this.y - y2) * (this.y - y2)));
    }

    /**
     * equals.
     *
     * @param other that is of type Point.
     * @return return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        double epsilon = Math.pow(10, -15);

        // this checks if a-b is closer to 0 than epsilon, if true, then a probably equals b
        return Math.abs(this.x - other.getX()) < epsilon && Math.abs(this.y - other.getY()) < epsilon;

        //return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * @return the value of x
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the value of y
     */
    public double getY() {
        return this.y;
    }
}
