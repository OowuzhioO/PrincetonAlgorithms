


public class PercolationStats {

	private int siteLength;
	private int siteCount;
	private int simulationCount;
	private double[] openedSitesRatio;

	public PercolationStats(int N, int T) {
		siteLength = N;
		simulationCount = T;
		siteCount = N * N;

		startSimulation();
	}

	private void startSimulation() {
		openedSitesRatio = new double[simulationCount];

		for (int i = 0; i < simulationCount; i++) {
			openedSitesRatio[i] = run() / siteCount;
		}
	}

	public double mean() {
		double totalOpenedSitesRatio = 0;
		for (int i = 0; i < simulationCount; i++) {
			totalOpenedSitesRatio += openedSitesRatio[i];
		}
		return totalOpenedSitesRatio / simulationCount;
	}

	public double stddev() {
		double meanValue = mean();
		double variance = 0;
		double meanDifference = 0;
		for (int i = 0; i < openedSitesRatio.length; i++) {
			meanDifference += Math.pow(meanValue - openedSitesRatio[i], 2);
		}
		variance = meanDifference / simulationCount;

		return Math.sqrt(variance);
	}

	public double run() {
		double openedSites = 0;
		Percolation perc = new Percolation(siteLength);
		while (!perc.percolates()) {
			int i = StdRandom.uniform(siteLength);
			int j = StdRandom.uniform(siteLength);
			if (!perc.isOpen(i, j)) {
				perc.open(i, j);
				openedSites++;
			}
		}
		System.out.println("System percolated after opening " + openedSites
				+ " sites");
		return openedSites;

	}

	public double confidenceLo() {
		return mean() - (1.96 * (stddev() / Math.sqrt(simulationCount)));
	}

	public double confidenceHi() {
		return mean() + (1.96 * (stddev() / Math.sqrt(simulationCount)));
	}

	public static void main(String[] args) {
		PercolationStats myStats = new PercolationStats(5, 1000);
		System.out.println(myStats.mean());
		System.out.println(myStats.stddev());
		System.out.println(myStats.confidenceLo());
		System.out.println(myStats.confidenceHi());
	}

}
