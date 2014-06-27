/**
 * This is to test the PercolationWithoutWeightedQuickUnion without WeightedQuickUnion Algorithm.
 *
 * The meaning of values of matrix.
 * false : blocked
 * true : opened
 */
public class PercolationWithoutWeightedQuickUnion {
    private boolean[][] mat_open;

    /**
     * Initializes an empty PercolationWithoutWeightedQuickUnion data structure with N^2 with 0 in each entry
     * @throws java.lang.IllegalArgumentException if N < 0
     * @param N the number of objects
     */
    public PercolationWithoutWeightedQuickUnion(int N) {
        mat_open = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mat_open[i][j] = false;
            }
        }
    }

    /**
     * build a probabilistic PercolationWithoutWeightedQuickUnion data structure with open probability of p.
     * @throws java.lang.IllegalArgumentException if N < 0
     * @param N the number of objects
     * @param p the probability of open
     */
    public static boolean[][] random(int N, double p) {
        boolean[][] mat_open = new boolean[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                mat_open[i][j] = StdRandom.bernoulli(p);

        return mat_open;
    }

    /**
     * Getter for the variable 'mat_open'
     * return the variable: mat_open
     * @return the 2 dimensional array
     */
    public boolean[][] getOpen() {
        return mat_open;
    }

    /**
     * open site (row i, column j) if it is not already
     */
    public void open(int i, int j) {
        if (!mat_open[i][j]) {
            mat_open[i][j] = true;
        }
    }

    /**
     * is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        return mat_open[i][j];
    }

    /**
     * is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        boolean[][] mat_full = flow(mat_open);
        return mat_full[i][j];
    }

    /**
     * Logic: the site is full if (i, j) is open and 4 neighbors are full.
     * @return the 2 dimensional array that represents full sites
     */
    public static boolean[][] flow(boolean[][] mat_open) {
        int N = mat_open.length;
        boolean[][] mat_full = new boolean[N][N];

        for (int j = 0; j < N; j++)
            if (mat_open[0][j]) flow(mat_open, mat_full, 0, j);

        return mat_full;
    }

    public static void flow(boolean[][] mat_open,
                            boolean[][] mat_full, int i, int j) {
        int N = mat_full.length;
        if (i < 0 || i >= N || j < 0 || j >= N) return;
        if (!mat_open[i][j]) return;
        if (mat_full[i][j]) return;
        mat_full[i][j] = true; // mark
        flow(mat_open, mat_full, i+1, j); // down
        flow(mat_open, mat_full, i, j+1); // right
        flow(mat_open, mat_full, i, j-1); // left
        flow(mat_open, mat_full, i-1, j); // up
    }


    /**
     * Check whether the system percolates
     */
    public static boolean percolates(boolean[][] mat_open) {
        int N = mat_open.length;
        boolean[][] mat_full = flow(mat_open);
        for (int j = 0; j < N; j++)
            if (mat_full[N-1][j]) return true;  // percolates if we have any full site in the bottom row
        return false;
    }


    public static double eval(int N, double p, int T) {
        int cnt = 0;
        for (int t = 0; t < T; t++) {
            boolean[][] open = PercolationWithoutWeightedQuickUnion.random(N, p);
            if (PercolationWithoutWeightedQuickUnion.percolates(open)) cnt++;
        }
        return (double) cnt / T;
    }

    /**
     * Ensure that 0.593 is the threshold that makes the mean percolation rate of 0.5
     * @param args No Arguments
     */
    public static void main(String[] args) {
        int N = 500;
        double p = 0.593;
        int T = 500;

        Stopwatch watch = new Stopwatch();  // measure processing time
        double result = eval(N, p, T);
        double e = watch.elapsedTime();

        StdOut.printf("grid size: %d, trials: %d, open prob: %f\n", N, T, p);
        StdOut.println("-------------------------------------------");
        StdOut.println("\t\t\t\t\tSUMMARY");
        StdOut.println("===========================================");
        StdOut.printf("Mean: %f\n", result);
        StdOut.printf("* Computation Time: %.3f secs\n", e);
    }
}
