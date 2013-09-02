package edu.princeton.algorithms.part1.week1;

import edu.princeton.algorithms.alg4.StdRandom;

public class PercolationStats {

	private int siteLength;
	private int simulationCount;

	public PercolationStats(int N, int T) {
		siteLength = N;
		simulationCount = T;
	}

	public void startSimulation() {
		int[] openedSitesCount = new int[simulationCount];
		int totalOpenedSitesCount = 0;

		for (int i = 0; i < simulationCount; i++) {
			openedSitesCount[i] = run();
			totalOpenedSitesCount += openedSitesCount[i];
		}
		System.out.println("Total mean opened sites for entire simulation is "
				+ totalOpenedSitesCount / simulationCount);
	}

	public int run() {
		int opened = 0;
		Percolation perc = new Percolation(siteLength);
		while (!perc.percolates()) {
			int i = StdRandom.uniform(siteLength);
			int j = StdRandom.uniform(siteLength);
			if (!perc.isOpen(i, j)) {
				perc.open(i, j);
				opened++;
			}
		}
		System.out.println("System percolated after opening " + opened
				+ " sites");
		return opened;

	}

	public static void main(String[] args) {
		new PercolationStats(20, 40).startSimulation();
	}

}
