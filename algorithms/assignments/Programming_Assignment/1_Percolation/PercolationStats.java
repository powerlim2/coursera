/**
 * Monte Carlo Simulation to estimate the optimal percolation threshold.
 * p* (optimal threshold) ~ 0.593
 *
 * Dependencies: Percolation.java, StdStats.java, StdOut.java, StopWatch.java
 * @author Joon Lim
 */
public class PercolationStats {
    private double [] trial;

    // perform T independent computational experiments on an N-by-N grid

    /**
     * Initialize Monte Carlo Simulation for Percolation and
     * perform T independent computational experiments on an N-by-N grid
     * @param N the size of the site matrix
     * @param T the number of monte carlo trials
     */
    public PercolationStats(int N, int T){
        checkRange(N, T);
        trial = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation perc = new Percolation(N);
            int count = 0;
            while (!perc.percolates()) {
                int row = StdRandom.uniform(N) + 1; // [1, N]
                int column = StdRandom.uniform(N) + 1; // [1, N]
                if (!perc.isOpen(row, column)) {
                    perc.open(row, column);
                    count++;
                }
            }
            // todo
            trial[i] = (double) count / (N*N);
        }
    }

    /**
     *
     * @return sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(trial);
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(trial);
    }


    /**
     * @return lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trial.length));
    }

    /**
     * @return upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trial.length));
    }

    /**
     * Input value check
     * @param N the size of the site matrix
     * @param T the number of monte carlo trials
     */
    private static void checkRange(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("N, T > 0");
    }

    /**
     * @param args (N) (T)
     * N means the size of the site matrix (default, 300)
     * T means the number of monte carlo trials (default, 300)
     */
    public static void main(String[] args){
        int size;
        int numTry;

        // argument value control
        if (args.length > 0) {
            if (args.length == 1) {
                size = Integer.parseInt(args[0]);
                numTry = 300;
            } else {
                size = Integer.parseInt(args[0]);
                numTry = Integer.parseInt(args[1]);
            }
        } else {
            size = 300;
            numTry = 300;
        }

        if (size <= 0) size = 300;
        if (numTry <= 0) numTry = 300;

        Stopwatch watch = new Stopwatch();  // measure processing time
        PercolationStats ps = new PercolationStats(size, numTry);
        double e = watch.elapsedTime();

        StdOut.printf("grid size: %d, number of trials: %d\n", size, numTry);
        StdOut.println("----------------------------------------");
        StdOut.println("\t\t\t\tSUMMARY");
        StdOut.println("========================================");
        StdOut.printf("* mean: %.3f\n", ps.mean());
        StdOut.printf("* standard deviation: %.3f\n", ps.stddev());
        StdOut.printf("* 95%% confidence interval: (%.4f , %.4f)\n", ps.confidenceLo(), ps.confidenceHi());
        StdOut.printf("* Computation Time: %.3f secs\n", e);
    }
}
