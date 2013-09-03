/*----------------------------------------------------------------
 *  Author:        Sathishkumar Kuppuswami
 *  Written:       02/09/2013
 *  Last updated:  03/09/2013
 *
 *  Implements Percolation Stats API running Monte-carlo simulation on Percolation
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats <<siteLength>> <<SimulationCount>>
 *  
 *  % java Percolation <<siteLength>> <<SimulationCount>>
 *  System percolated after opening xxx sites
 *----------------------------------------------------------------*/

public class PercolationStats {

    // Length of the site
    private int siteLength;
    // # of sites
    private int siteCount;
    // # of times to run the simulation
    private int simulationCount;
    // List of opened site ratio for each simulation
    private double[] openedSitesRatio;

    public PercolationStats(int N, int T) {
        siteLength = N;
        simulationCount = T;
        siteCount = N * N;

        // Run simulation after initialization
        startSimulation();
    }

    /**
     * Run simulation for specified number of times
     */
    private void startSimulation() {
        openedSitesRatio = new double[simulationCount];

        for (int i = 0; i < simulationCount; i++) {
            openedSitesRatio[i] = run() / siteCount;
        }
    }

    /**
     * Returns the mean Opened sites ratio across the entire simulation
     */
    public double mean() {
        double totalOpenedSitesRatio = 0;
        for (int i = 0; i < simulationCount; i++) {
            totalOpenedSitesRatio += openedSitesRatio[i];
        }
        return totalOpenedSitesRatio / simulationCount;
    }

    /**
     * Return Standard deviation for the entire simulation
     */
    public double stddev() {
        double meanValue = mean();
        double variance = 0;
        double meanDifference = 0;
        for (int i = 0; i < openedSitesRatio.length; i++) {
            double currentMeanDiff = meanValue - openedSitesRatio[i];
            meanDifference += currentMeanDiff * currentMeanDiff;
        }
        variance = meanDifference / simulationCount;

        return Math.sqrt(variance);
    }

    /**
     * Runs a single simulation for the requested site length
     */
    private double run() {
        double openedSites = 0;
        Percolation perc = new Percolation(siteLength);
        while (!perc.percolates()) {
            int i = 1 + StdRandom.uniform(siteLength);
            int j = 1 + StdRandom.uniform(siteLength);
            if (!perc.isOpen(i, j)) {
                perc.open(i, j);
                openedSites++;
            }
        }
        return openedSites;

    }

    /**
     * Returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - (1.96 * (stddev() / Math.sqrt(simulationCount)));
    }

    /**
     * Returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + (1.96 * (stddev() / Math.sqrt(simulationCount)));
    }

    public static void main(String[] args) {
        int N = 20;
        int T = 100;
        if (args.length == 2) {
            try {
                N = Integer.parseInt(args[0]);
                T = Integer.parseInt(args[1]);
            } catch (NumberFormatException nfe) {
                StdOut.println("Invalid arguments. Proceeding with defaults");
            }
        }

        PercolationStats myStats = new PercolationStats(N, T);
        StdOut.println("Mean                    = " + myStats.mean());
        StdOut.println("Stddev                  = " + myStats.stddev());
        StdOut.println("95% confidence interval = " + myStats.confidenceLo()
                + ", " + myStats.confidenceHi());
    }

}
