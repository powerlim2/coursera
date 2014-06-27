/**
 * Percolation Data Structure,
 * implemented based on Weighted Quick Union Algorithm.
 *
 * Dependencies: WeightedQuickUnionUF.java
 * @author Joon Lim
 */

public class Percolation {
    private boolean [] open;
    private int side;
    private int area;
    private WeightedQuickUnionUF siteMatrix;

    /**
     * Initialize Percolation data structure
     * create N-by-N grid, with all sites blocked
     * @param N the length of a dimension of the grid
     */
    public Percolation(int N){
        side = N;
        area = N * N;
        siteMatrix = new WeightedQuickUnionUF(area + 2);
        open = new boolean[area + 2];

        for(int i = 0; i < area; i++){
            open[i] = false; // all sites are blocked
        }
        open[area] = true;  // top row connection
        open[area + 1] = true;  // bottom row connection
    }

    /**
     * open site (row i, column j) if it is not already
     * @param i row index (0, N-1)
     * @param j column index (0, N-1)
     */
    public void open(int i, int j) {
        checkRange(i, j, side);
        if (isOpen(i, j)) return;
        int site = getIndex(i, j);

        open[site] = true;

        // if not top row
        if (i == 0) {
            // connect to top check point in case of top row
            union(site, area);
        } else if (isOpen(i - 1, j)) { // when i != 0
            union(getIndex(i - 1, j), site);
        }

        // if not bottom row
        if (i == side - 1) {
            // connect to bottom check point in case of bottom row
            union(site, area + 1);
        } else if (isOpen(i + 1, j)) { // when i != side
            union(getIndex(i + 1, j), site);
        }

        // if not left border
        if (j > 0)
            if (isOpen(i, j - 1))
                union(getIndex(i, j - 1), site);

        // if not right border
        if (j < side - 1)
            if (isOpen(i, j + 1))
                union(getIndex(i, j + 1), site);

    }

    /**
     * Input value check
     * @param i the row index
     * @param j the number of monte carlo trials
     * @param side length of a dimension of the grid
     */
    private static void checkRange(int i, int j, int side) {
        if (i < 0 || j < 0 || i > side || j > side)
            throw new IndexOutOfBoundsException();
    }

    /**
     * Connect two points (a and b) in Weighted Quick Union Data Structure
     * @param i point 'a'
     * @param j point 'b'
     */
    private void union(int i, int j) {
        if (!siteMatrix.connected(i, j)) {
            siteMatrix.union(i, j);
        }
    }

    /**
     * check whether the site is open
     * @param i row index (0, N-1)
     * @param j column index (0, N-1)
     * @return boolean value of indicating whether the site is open
     */
    public boolean isOpen(int i, int j){
        checkRange(i, j, side);
        return open[getIndex(i, j)];
    }

    /**
     * check whether the site is full
     * @param i row index (0, N-1)
     * @param j column index (0, N-1)
     * @return boolean value of indicating whether the site is full
     */
    public boolean isFull(int i, int j) {
        checkRange(i, j, side);
        return siteMatrix.connected(area, getIndex(i, j));
    }

    /**
     * check whether the site grid is percolated
     * @return boolean value of indicating whether the grid is percolated
     */
    public boolean percolates() {
        return siteMatrix.connected(area, area+1);
    }

    /**
     * Convert 2 dimensional input to 1 dimensional Weighted Quick Union data structure
     * One to One mapping
     * @param i row index (0, N-1)
     * @param j column index (0, N-1)
     * @return 1 dimensional index for the site of (row i, column j)
     */
    private int getIndex(int i, int j) {
        return (side * i + j);
    }

    /**
     * Simple sanity check for the Percolation data structure
     * @param args No Argument
     */
    public static void main(String[] args) {

        // simple sanity check!
        Percolation ps = new Percolation(5);

        // Artificially make vertically percolated situation.
        ps.open(0, 1);
        ps.open(1, 1);
        ps.open(2, 1);
        ps.open(3, 1);
        ps.open(4, 1);

        StdOut.println(ps.percolates());
    }
}