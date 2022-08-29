import java.util.ArrayList;
import java.util.List;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * the Rectangle class
 */
public class Rectangle {
    /**
     * the Rectangle class contain the information about the rectangle like his width,height and location on the screen.
     */
    private Point upperLeft;
    private double width;
    private double height;

    //constructor

    /**
     * Constructor.
     *
     * @param upperLeft the upper left location of the the rectangle (x and y).
     * @param width     of the rectangle
     * @param height    of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper left location (point) of the rectangle.
     *
     * @return upperleft point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * check is a point (her values) is in list of point.
     *
     * @param array - list of point
     * @param point contain x and y coordinate
     * @return true is the point in the list, otherwise false
     */
    private boolean checkInTheArray(List<Point> array, Point point) {
        for (Point checkPoint : array) {
            if (checkPoint.equals(point)) {
                return true;
            }
        }
        return false;
    }

    /**
     * set the upper left point of the rect.
     *
     * @param x contain x
     * @param y coordinate
     */
    public void setUpperLeft(double x, double y) {
        Point point = new Point(x, y);
        this.upperLeft = point;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line that need to check if he intersect with the rectangle
     * @return list of the intersection point with the recngle
     */
    public List<Point> intersectionPoints(Line line) {
        //build the top line of the rect
        Point upperLeftNew = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY());
        Point upperRight = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        Line top = new Line(upperLeftNew, upperRight);

        //build the bottom line of the rect
        Point bottomLeft = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        Point bottomRight = new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
        Line bottom = new Line(bottomLeft, bottomRight);

        //build the right line of the rect
        Line right = new Line(bottomRight, upperRight);

        //build the left line of the rect
        Line left = new Line(bottomLeft, upperLeft);

        //list for the point intersection
        List<Point> array = new ArrayList<>();

        if (line.intersectionWith(top) != null) {
            array.add(line.intersectionWith(top));
        }
        if (line.intersectionWith(bottom) != null) {
            array.add(line.intersectionWith(bottom));
        }
        if (line.intersectionWith(right) != null) {
            array.add(line.intersectionWith(right));
        }
        if (line.intersectionWith(left) != null) {
            array.add(line.intersectionWith(left));
        }
        if (array.size() == 0) {
            return array;
        }

        // if there is a same intersection point in the corner, need to return only 1 point.
        if (array.size() != 1) {
            if (array.get(0).equals(array.get(1))) {
                array.remove(1);
            }
            //if the line is parallel to the on of the line of the rectangle
            if (this.checkInTheArray(array, bottomRight) && this.checkInTheArray(array, upperRight)) {
                return array;
            }
            if (this.checkInTheArray(array, upperRight) && this.checkInTheArray(array, upperLeft)) {
                return array;
            }
            if (this.checkInTheArray(array, bottomLeft) && this.checkInTheArray(array, upperLeft)) {
                return array;
            }
            if (this.checkInTheArray(array, bottomLeft) && this.checkInTheArray(array, bottomRight)) {
                return array;
            }
        }
        return array;


    }
}