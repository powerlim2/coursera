/*************************************************************************
 * Name:  Joon Lim
 * Email: powerlim2@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER;       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
        SLOPE_ORDER = new slopeOrder();
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (that.x == this.x) {             // denominator is zero
            if (that.y == this.y) return Double.NEGATIVE_INFINITY;
            else                  return Double.POSITIVE_INFINITY;
        } else if (that.y == this.y) {      // numerator is zero
            return 0.0;
        } else {
            return ((double) that.y - this.y) / (that.x - this.x);
        }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y == that.y)   return this.x - that.x;
        else                    return this.y - that.y;
    }

    private class slopeOrder implements Comparator<Point> {
        public int compare(Point a, Point c) {
            double res = slopeTo(a) - slopeTo(c);
            if      (res < 0.0) return -1;      // d(a) <  d(c)
            else if (res > 0.0) return  1;      // d(a) >  d(c)
            else                return  0;      // d(a) == d(c)
        }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        // when two points are the same
        Point p = new Point(10, 20);
        Point q = new Point(10, 20);
        assert p.slopeTo(q) == Double.NEGATIVE_INFINITY;

        // when only x values are the same
        p = new Point(10, 20);
        q = new Point(10, 21);
        assert p.slopeTo(q) == Double.POSITIVE_INFINITY;

        // compare 2 slopes (x, y) and (x, z)
        Point x = new Point(10, 20);
        Point y = new Point(20, 40);
        Point z = new Point(20, 20);
        assert x.SLOPE_ORDER.compare(y, z) == 1;
        assert x.slopeTo(y) == 2;
        assert x.slopeTo(z) == 0.0;
    }
}