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

    // two UFs to prevent "backwash"
    // percUF has top and bottom virt sites, uses for "percolates"
    // fullUF has top virt, but no bottom virt, used for "isFull"
    private final WeightedQuickUnionUF siteMatrix;
    private final WeightedQuickUnionUF fullCheck;


    /**
     * Initialize Percolation data structure
     * create N-by-N grid, with all sites blocked
     * @param N the length of a dimension of the grid
     */
    public Percolation(int N){
        if (N <= 0) throw new IllegalArgumentException("N must be larger than 0");

        side = N;
        area = N * N;
        siteMatrix = new WeightedQuickUnionUF(area + 2);
        fullCheck = new WeightedQuickUnionUF(area + 1); // no bottom virtual site
        open = new boolean[area + 2];

        for(int i = 0; i < area; i++){
            open[i] = false; // all sites are blocked
        }
        open[area] = true;  // top row connection
        open[area + 1] = true;  // bottom row connection
    }

    /**
     * open site (row i, column j) if it is not already
     * @param i row index (1, N)
     * @param j column index (1, N)
     */
    public void open(int i, int j) {
        checkRange(i, j);
        if (isOpen(i, j)) return;
        int site = getIndex(i, j);

        open[site] = true;

        if (i == 1) {
            // connect to top check point in case of top row
            union(site, area);
        } else if (isOpen(i - 1, j)) { // when i != 0
            union(getIndex(i - 1, j), site);
        }

        if (i == side) {
            // connect to bottom check point in case of bottom row
            siteUnionOnly(site, area + 1);
        } else if (isOpen(i + 1, j)) { // when i != side
            union(getIndex(i + 1, j), site);
        }

        // if not left border
        if (j > 1)
            if (isOpen(i, j - 1))
                union(getIndex(i, j - 1), site);

        // if not right border
        if (j < side)
            if (isOpen(i, j + 1))
                union(getIndex(i, j + 1), site);

    }

    /**
     * Input value check
     * @param i the row index
     * @param j the number of monte carlo trials
     */
    private void checkRange(int i, int j) {
        if (i <= 0 || j <= 0 || i > side || j > side) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Connect two points (a and b) in Weighted Quick Union Data Structure
     * @param i point 'a'
     * @param j point 'b'
     */
    private void siteUnionOnly(int i, int j) {
        if (!siteMatrix.connected(i, j)) {
            siteMatrix.union(i, j);
        }
    }

    /**
     * Connect two points (a and b) in 2 Weighted Quick Union Data Structure:
     * siteMatix and fullCheck
     * @param i point 'a'
     * @param j point 'b'
     */
    private void union(int i, int j) {
        siteMatrix.union(i, j);
        fullCheck.union(i, j);
    }

    /**
     * check whether the site is open
     * @param i row index (0, N - 1)
     * @param j column index (0, N - 1)
     * @return boolean value of indicating whether the site is open
     */
    public boolean isOpen(int i, int j){
        checkRange(i, j);
        return open[getIndex(i, j)];
    }

    /**
     * full means from this point do we have a connection to the top virtual
     * however, once percolation occurs the entire bottom row will be connected
     * not just the path that we want, this is called "backwash"
     * to prevent isFull from returning true for backwash sites
     * ensure any site that is connected ALSO...
     * @param i row index (1, N)
     * @param j column index (1, N)
     * @return boolean value of indicating whether the site is full
     */
    public boolean isFull(int i, int j) {
        checkRange(i, j);
        return fullCheck.connected(area, getIndex(i, j));
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
        return side * (i-1) + (j-1);
    }

    /**
     * Simple sanity check for the Percolation data structure
     * @param args No Argument
     */
    public static void main(String[] args) {

        // simple sanity check!
        Percolation ps = new Percolation(5);

        // Artificially make vertically percolated situation.
        ps.open(1, 5);
        ps.open(2, 5);
        ps.open(3, 5);
        ps.open(4, 5);
        ps.open(5, 5);

        StdOut.println(ps.percolates());
    }
}