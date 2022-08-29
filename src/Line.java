import java.util.List;

/**
 * id - 207984956.
 * name nadav yakobovich.
 * A class Line.
 */
public class Line {

    /*
     *class line continue start point and end point of the line
     */
    private Point start;
    private Point end;

    /**
     * Constructor.
     *
     * @param start point
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor.
     *
     * @param x1 - the x coordinate for the start point
     * @param y1 - the y coordinate for the start point
     * @param x2 - the x coordinate for the end point
     * @param y2 - the y coordinate for the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculate the length of the line.
     *
     * @return return the length between the start point and the end point of the line
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Returns the start point.
     *
     * @return start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point.
     *
     * @return end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * calculate the middle point of the line.
     *
     * @return the point that contain the middle coordinate of the line.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * check if two line intersect.
     *
     * @param other line
     * @return true is the line intersect, otherwise return false.
     */
    public boolean isIntersecting(Line other) {
        if (this.equals(other)) {
            return true;
        }
        if (isLineOverEachOther(other)) {
            return true;
        }
        if (intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * Returns the intersection point if the lines intersect,and null otherwise.
     * the function checks a Edge cases
     *
     * @param other line
     * @return point of the intersection, if there is no intersection return null
     */
    public Point checkIntersection(Line other) {
        //if the "this" line is actually only point only
        if (this.start.equals(this.end)) {
            if (this.intersectionWithOnePoint(other, this.start)) {
                return this.start;
            }
        }
        //if the other line is actually only point only
        if (other.start.equals(other.end)) {
            if (this.intersectionWithOnePoint(this, other.start)) {
                return other.start;
            }
        }
        //if the line are not only 1 point and there are the same - return null
        if (other.equals(this)) {
            return null;
        }
        double maxThisX = Math.max(this.start.getX(), this.end.getX());
        double maxThisY = Math.max(this.start.getY(), this.end.getY());
        double maxOtherX = Math.max(other.start.getX(), other.end.getX());
        double maxOtherY = Math.max(other.start.getY(), other.end.getY());
        double minThisX = Math.min(this.start.getX(), this.end.getX());
        double minThisY = Math.min(this.start.getY(), this.end.getY());
        double minOtherX = Math.min(other.start.getX(), other.end.getX());
        double minOtherY = Math.min(other.start.getY(), other.end.getY());
        double mOther = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        double mThis = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        //if the the start/end point is on of the start/end point of the other line
        if (this.start.equals(other.start)) {
            if (this.end.getY() == other.end.getY()) {
                if (minOtherX == maxThisX || maxOtherX == minThisX) {
                    return this.start;
                }
                return null;
            }
            if (this.end.getX() == other.end.getX()) {
                if (minOtherY == maxThisY || maxOtherY == minThisY) {
                    return this.start;
                }
                return null;
            }
            return this.start;
        }
        if (this.start.equals(other.end)) {
            if (this.end.getY() == other.start.getY()) {
                if (maxThisX == minOtherX || minThisX == maxOtherX) {
                    return this.start;
                }
                return null;
            }
            if (this.end.getX() == other.start.getX()) {
                if (maxThisY == minOtherY || minThisY == maxOtherY) {
                    return this.start;
                }
                return null;
            }
            return this.start;
        }
        if (this.end.equals(other.start)) {
            if (this.start.getY() == other.end.getY()) {
                if (maxThisX == minOtherX || minThisX == maxOtherX) {
                    return this.end;
                }
                return null;
            }
            if (this.start.getX() == other.end.getX()) {
                if (maxThisY == minOtherY || minThisY == maxOtherY) {
                    return this.end;
                }
                return null;
            }

            return this.end;
        }
        if (this.end.equals(other.end)) {
            if (this.start.getY() == other.start.getY()) {
                if (maxThisX == minOtherX || minThisX == maxOtherX) {
                    return this.end;
                }
                return null;
            }
            if (this.start.getX() == other.start.getX()) {
                if (maxThisY == minOtherY || minThisY == maxOtherY) {
                    return this.end;
                }
                return null;
            }
            return this.end;
        }

        return null;
    }

    /**
     * check if two line are parallel, and if there over each other or not.
     *
     * @param other line
     * @return true is the line is over each other, otherwise false
     */
    public boolean isLineOverEachOther(Line other) {
        double mOther = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        double mThis = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        if (mOther != mThis) {
            return false;
        }
        //if the line in partial over each other or parallel each other
        double maxThisX = Math.max(this.start.getX(), this.end.getX());
        double maxThisY = Math.max(this.start.getY(), this.end.getY());
        double maxOtherX = Math.max(other.start.getX(), other.end.getX());
        double maxOtherY = Math.max(other.start.getY(), other.end.getY());
        double minThisX = Math.min(this.start.getX(), this.end.getX());
        double minThisY = Math.min(this.start.getY(), this.end.getY());
        double minOtherX = Math.min(other.start.getX(), other.end.getX());
        double minOtherY = Math.min(other.start.getY(), other.end.getY());
        // the line are not touch each other
        if ((maxThisX < minOtherX || maxThisY < minOtherY) || (maxOtherX < minThisX || maxOtherY < minThisY)) {
            return false;
        }
        // the line over each other
        if ((maxOtherX > minThisX && maxOtherY > minThisY) || (maxThisX > minOtherX && maxThisY > minOtherY)) {
            return true;
        }

        return false;
    }

    /**
     * Returns the intersection point if the lines intersect,and null otherwise.
     *
     * @param other line
     * @return point of the intersection, if there is no intersection return null
     */
    public Point intersectionWith(Line other) {
        //the line is the same line  ( and the 2 line is not a point)
        if (this.equals(other) && !this.end.equals(this.start) && !other.end.equals(other.start)) {
            return null;
        }
        if (checkIntersection(other) != null) {
            return checkIntersection(other);
        }

        // Line other
        double a1 = other.end.getY() - other.start.getY();
        double b1 = other.start.getX() - other.end.getX();
        double c1 = a1 * (other.start.getX()) + b1 * (other.start.getY());

        // Line this
        double a2 = this.end.getY() - this.start.getY();
        double b2 = this.start.getX() - this.end.getX();
        double c2 = a2 * (this.start.getX()) + b2 * (this.start.getY());
        double determinant = a1 * b2 - a2 * b1;
        if (determinant == 0) {
            // The lines are parallel.
            if (isLineOverEachOther(other)) {
                return null;
            }
            return null;
        } else {
            // get the coordinate of the new point
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            // check if the intersection Point is in the 2 line
            Point newPoint = new Point(x, y);
            if (!isInTheLine(newPoint, other)) {
                return null;
            } else {
                return newPoint;
            }
        }
    }

    /**
     * check is the point is in the line.
     *
     * @param other line
     * @param point - point that we want to check
     * @return true if the point is in the line, otherwise return false.
     */
    public boolean intersectionWithOnePoint(Line other, Point point) {
        //if the line is vertical
        if (other.start.getX() == other.end.getX()) {
            return isInTheLine(point, other);
        }
        //get the slope of the line
        double m = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        // get the c value - the x value of the intersection with y
        double c = other.start.getY() - m * other.start.getX();
        // check if the value of y by the equation's of a line (with x from the point) is the same y on the point
        if (point.getY() == (point.getX() * m + c)) {
            return true;
        }
        double lineLength = other.length();
        double lineByPoint = point.distance(other.start) + point.distance(other.end);
        double epsilon = Math.pow(10, -15);
        /**
         *  this checks if lineLength (the original line) - the line by the new point is closer to 0 than epsilon,
         *  if true, then a probably equals b
         */
        if (Math.abs(lineLength - lineByPoint) < epsilon) {
            return true;
        }
        return false;
    }

    /**
     * check if the intersection Point is in the 2 line.
     *
     * @param point that need to check
     * @param other the line  that need to check if the point in it.
     * @return true if the point in the line, else return false.
     */
    private boolean isInTheLine(Point point, Line other) {

        //enter for max the bigger value of x between x values of the star point and the end point
        double maxThis = Math.max(this.start.getX(), this.end.getX());
        double minThis = Math.min(this.start.getX(), this.end.getX());
        double maxOther = Math.max(other.start.getX(), other.end.getX());
        double minOther = Math.min(other.start.getX(), other.end.getX());

        //enter for max the bigger value of y between y values of the star point and the end point
        double maxThisY = Math.max(this.start.getY(), this.end.getY());
        double minThisY = Math.min(this.start.getY(), this.end.getY());
        double maxOtherY = Math.max(other.start.getY(), other.end.getY());
        double minOtherY = Math.min(other.start.getY(), other.end.getY());
        int count = 0;
        //check if the point of the intersection in between the x and y value of the stat point and the end point of the
        // two lines
        if (point.getX() <= maxOther && point.getX() <= maxThis && point.getX()
                >= minOther && point.getX() >= minThis) {
            count++;
        }
        if (point.getY() <= maxOtherY && point.getY() <= maxThisY && point.getY()
                >= minOtherY && point.getY() >= minThisY) {
            count++;
        }
        return count == 2;
    }

    /**
     * equals -return true is the lines are equal (the start point and the end point is the same, false otherwise.
     *
     * @param other (line) that need to be checked.
     * @return true if the two line is equal
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        } else {
            if (this.start.equals(other.end) && this.end.equals(other.start)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return the closet point of intersection with rectangle to the start of the line.
     *
     * @param rect rectangle
     * @return the closet intersection point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> array = rect.intersectionPoints(this);
        if (array.size() == 0) {
            return null;
        }
        //check which point in the array is the closet to the start of the line
        Point min = array.get(0);
        for (Point point : array) {
            if (min.distance(this.start) >= point.distance(this.start)) {
                min = point;
            }
        }
        return min;
    }
}