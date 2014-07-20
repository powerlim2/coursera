/*************************************************************************
 * Compilation:  javac Brute.java
 * Execution:    java Brute points.txt
 * Dependencies: StdDraw.java, Point.java
 *
 * Description:  Find four collinear points in a set of points efficiently.
 *               Read the input file (in the format specified below),
 *               print to standard output the line segments discovered (in the format specified below),
 *               and draw to standard draw the line segments discovered (in the format specified below)
 *
 *************************************************************************/

import java.util.Arrays;

/**
 *  The Brute client examines 4 points at a time and checks whether they all lie on the same line segment,
 *  printing out any such line segments to standard output and drawing them using standard drawing.
 *  To check whether the 4 points p, q, r, and s are collinear,
 *  check whether the slopes between p and q, between p and r, and between p and s are all equal.
 *
 *  The order of growth of the running time of your program should be N4 in the worst case
 *  and it should use space proportional to N.
 *
 *  @author Joon Lim
 */
public class Brute {
    private static void setUpDrawing() {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
    }

    /** Read input files of points
     *
     *  First line gives the number of points; each subsequent line gives two
     *  integers, the x and the y coordinates.
     *
     *  Return an array of Point objects.
     */
    private static Point[] readInput(String filename) {
        In in = new In(filename);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(in.readInt(), in.readInt());
        }
        return points;
    }

    // Confirm an array of points is sorted
    private static boolean sorted(Point[] points) {
        int n = points.length;
        if (n < 2)
            return true;
        int i = 0;
        int cmp = 0;
        do {
            cmp = points[i].compareTo(points[++i]);
        } while (cmp == 0 && i < n);
        assert cmp != 0 || i == n; // If i == n then cmp's value doesn't matter.
        if (cmp < 0) cmp = -1;
        else cmp = 1;
        int nextcmp;
        while (i < n - 1) {
            nextcmp = points[i].compareTo(points[++i]);
            if      (nextcmp < 0) nextcmp = -1;
            else if (nextcmp > 0) nextcmp = 1;
            if (cmp != nextcmp && nextcmp != 0)
                return false;
        }
        return true;
    }

    /** Print to stdout a textual representation of a line segment
     *
     *  points is a SORTED array of Point objects.
     *
     *  Example:
     *  (10000, 0) -> (7000, 3000) -> (3000, 7000) -> (0, 10000)
     */
    private static void printLineSegment(Point[] points) {
        assert sorted(points);
        int end = points.length - 1;
        if (end > 0) {
            for (int i = 0; i < end; i++) {
                System.out.print(points[i] + " -> ");
            }
        }
        System.out.println(points[end]);
    }

    /** Draw line segments connecting a SORTED array of points
     *
     *  points is a SORTED array of Point objects
     */
    private static void draw(Point[] points) {
        assert sorted(points);
        points[0].drawTo(points[points.length - 1]);
    }

    // Output a given set of points found to be collinear. points is an array.
    private static void output(Point[] points) {
        Arrays.sort(points);
        printLineSegment(points);
        draw(points);
    }

    // Checks whether the points in the array are collinear
    private static boolean collinear(Point[] points) {
        if (points.length == 2)
            return true;
        Point base = points[0]; // Error if nonsensical number of points.
        double slope = base.slopeTo(points[1]);
        for (int i = 2; i < points.length; i++) {
            // May need to improve equality test.
            if (slope != base.slopeTo(points[i]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        setUpDrawing();
        Point[] points = readInput(args[0]);

        /** Iterate through all combinations

         This (horrible) nested loop works like an odometer: the rightmost,
         dial like the inner-most loop, spins the fastest. Starting each nested
         loop at one greater than its parent loop keeps the entries of the f
         vector from repeating each other's numbers. The f vector then translates
         from selections in the points vector to positions in the result vector.
         We could also write this loop with index variables h, i, j, and k, and
         written the `results[g] = points[f[g]]` line in four lines. Hopefully
         this is easier to read.
         */
        Point[] first3 = new Point[3];
        Point[] result = new Point[4];
        int[] f = {0, 0, 0, 0};
        int n = points.length;
        for             (f[0] = 0;        f[0] < n; f[0]++) {
            points[f[0]].draw();
            for         (f[1] = f[0] + 1; f[1] < n; f[1]++) {
                for     (f[2] = f[1] + 1; f[2] < n; f[2]++) {
                    for (int g = 0; g < 3; g++) {
                        first3[g] = points[f[g]];
                    }
                    if (!collinear(first3)) {
                        continue;
                    }
                    for (f[3] = f[2] + 1; f[3] < n; f[3]++) {
                        for (int g = 0; g < 4; g++) {
                            result[g] = points[f[g]];
                        }
                        if (collinear(result)) {
                            output(result);
                        }
                    }
                }
            }
        }

        // Show everything all at once.
        StdDraw.show(0);
    }
}