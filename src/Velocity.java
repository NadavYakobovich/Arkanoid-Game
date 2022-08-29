/**
 * id - 207984956.
 * name nadav yakobovich.
 * A class Velocity.
 */
public class Velocity {
    /*
     * Velocity specifies the change in position on the `x` and the `y` axes.
     * contain dx  and dy - the change in the x and y coordinate
     */
    private double dx;
    private double dy;
    // constructor

    /**
     * Initialize the velocity objects.
     *
     * @param dx - the change rate in x
     * @param dy - the change rate in y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return the value of dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the value of dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * set the value of the dx.
     *
     * @param dxNew - the change rate in x.
     */
    public void setDx(double dxNew) {
        this.dx = dxNew;
    }

    /**
     * set the value of the dy.
     *
     * @param dyNew - the change rate in y
     */
    public void setDy(double dyNew) {
        this.dy = dyNew;
    }

    /**
     * converse angle and speed to dx and dy value and build new Velocity objects.
     *
     * @param angle of the ball
     * @param speed of the ball
     * @return new Velocity objects
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //add 270 degree to make degree 0 as the up direction
        double radian = Math.toRadians(angle + 270);
        double dx = Math.cos(radian) * speed;
        double dy = Math.sin(radian) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p of type Point
     * @return new point with the new coordinates
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point(p.getX() + dx, p.getY() + dy);
        return newPoint;
    }
}