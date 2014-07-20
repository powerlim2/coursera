/*************************************************************************
 * Compilation:  javac Fast.java
 * Execution:    java Fast points.txt
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
 *  Given a point p, the following method determines whether p participates in a set of 4 or more collinear points.
 *  Think of p as the origin.
 *  For each other point q, determine the slope it makes with p.
 *  Sort the points according to the slopes they makes with p.
 *  Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p.
 *  If so, these points, together with p, are collinear.
 *
 *  The order of growth of the running time of your program should be N2 log N in the worst case
 *  and it should use space proportional to N
 *
 * @author Joon Lim
 */
public class Fast {
    private static void printLineSeg(Point[] seg) {
        String output = "";
        for (int i = 0; i < seg.length; i++) {
            output += seg[i].toString();
            if (i < (seg.length - 1)) output += " -> ";

        }
        StdOut.println(output);
        seg[0].drawTo(seg[seg.length-1]);
    }

    private static void outputLineSegments(Point origin, Point[] sortedPoints, int marker, int adjpoints) {
        Point[] seg = new Point[adjpoints+1];

        for (int i = 0; i < adjpoints; i++) {
            seg[i] = sortedPoints[marker + i];
        }
        seg[adjpoints] = origin;
        Arrays.sort(seg);
        if (origin.compareTo(seg[0]) == 0)
            printLineSeg(seg);

    }

    private static void findLineSegments(Point origin, Point[] sortedPoints) {
        int marker = 1;
        int adjpoints = 1;

        double firstslope = origin.slopeTo(sortedPoints[1]);
        for (int j = 2; j < sortedPoints.length; j++) {
            double currentslope = origin.slopeTo(sortedPoints[j]);

            if (currentslope == firstslope) {
                adjpoints += 1;
            } else if (adjpoints >= 3) {
                outputLineSegments(origin,sortedPoints, marker, adjpoints);
                marker = j;
                adjpoints = 1;
                firstslope = currentslope;
            } else {
                adjpoints = 1;
                marker = j;
                firstslope = currentslope;
            }
        }
        if (adjpoints >= 3){
            outputLineSegments(origin,sortedPoints,marker,adjpoints);
        }

    }

    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        Point[] sortedPoints = new Point[N];

        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p  = new Point(x, y);
            points[i] = p;
            sortedPoints[i] = p;
            p.draw();
        }

        for (int i = 0; i < N; i++) {
            Point origin = points[i];
            Arrays.sort(sortedPoints, origin.SLOPE_ORDER);
            findLineSegments(origin, sortedPoints);
        }
        StdDraw.show(0);
    }
}