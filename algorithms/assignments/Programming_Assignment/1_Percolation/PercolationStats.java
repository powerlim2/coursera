/**
 * Monte Carlo Simulation to estimate the optimal percolation threshold.
 * p* (optimal threshold) ~ 0.593
 *
 * Dependencies: Percolation.java, StdStats.java, StdOut.java, Stopwatch.java
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
            boolean NP = false;
            int count = 0;
            while (!perc.percolates()) {
                int row = StdRandom.uniform(N);
                int column = StdRandom.uniform(N);
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
     * @param trial an array, which contains the result from monte carlo simulation
     * @return sample mean of percolation threshold
     */
    public static double mean(double[] trial) {
        return StdStats.mean(trial);
    }

    /**
     * @param trial an array, which contains the result from monte carlo simulation
     * @return sample standard deviation of percolation threshold
     */
    public static double stddev(double[] trial) {
        return StdStats.stddev(trial);
    }


    /**
     * @param trial an array, which contains the result from monte carlo simulation
     * @return lower bound of the 95% confidence interval
     */
    public static double confIntLo(double[] trial) {
        return mean(trial) - ((1.96 * stddev(trial)) / Math.sqrt(trial.length));
    }

    /**
     * @param trial an array, which contains the result from monte carlo simulation
     * @return upper bound of the 95% confidence interval
     */
    public static double confIntHi(double[] trial) {
        return mean(trial) + ((1.96 * stddev(trial)) / Math.sqrt(trial.length));
    }

    /**
     * Receive the trial array
     * @return an array, which contains the result from monte carlo simulation
     */
    public double[] getTrial() {
        return trial;
    }

    /**
     * Input value check
     * @param N the size of the site matrix
     * @param T the number of monte carlo trials
     */
    private static void checkRange(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IndexOutOfBoundsException();
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

        double[] trial = ps.getTrial();
        StdOut.printf("grid size: %d, number of trials: %d\n", size, numTry);
        StdOut.println("----------------------------------------");
        StdOut.println("\t\t\t\tSUMMARY");
        StdOut.println("========================================");
        StdOut.printf("* mean: %.3f\n", mean(trial));
        StdOut.printf("* standard deviation: %.3f\n", stddev(trial));
        StdOut.printf("* 95%% confidence interval: (%.4f , %.4f)\n", confIntLo(trial), confIntHi(trial));
        StdOut.printf("* Computation Time: %.3f secs\n", e);
    }
}
