package edu.princeton.algorithms.part1.week1;

import edu.princeton.algorithms.alg4.WeightedQuickUnionUF;

public class Percolation {

	private boolean sites[];
	private int sitesCount;
	private WeightedQuickUnionUF weightedQUF;
	private int virtualTop;
	private int virtualBottom;
	private int siteLength;

	public Percolation(int n) {
		siteLength = n;
		sitesCount = n * n;
		sites = new boolean[sitesCount];

		weightedQUF = new WeightedQuickUnionUF(sitesCount + 2);

		virtualTop = sitesCount;
		virtualBottom = sitesCount + 1;
	}

	/**
	 * @param row
	 * @param column
	 */
	public void open(int row, int column) {
		if (isWithinBoundary(row, column)) {
			int siteIndex = getIndex(row, column);
			if (!sites[siteIndex]) {
				System.out.println("Opening site# " + siteIndex);
				sites[siteIndex] = true;
				if (row == 1) {
					weightedQUF.union(siteIndex, virtualTop);
				} else if (row == siteLength) {
					weightedQUF.union(siteIndex, virtualBottom);
				}

				int neighbourIndex = 0;
				neighbourIndex = getLeftNeighbourIndex(row, column);
				joinNeighbours(siteIndex, neighbourIndex);

				neighbourIndex = getRightNeighbourIndex(row, column);
				joinNeighbours(siteIndex, neighbourIndex);

				neighbourIndex = getTopNeighbourIndex(row, column);
				joinNeighbours(siteIndex, neighbourIndex);

				neighbourIndex = getBottomNeighbourIndex(row, column);
				joinNeighbours(siteIndex, neighbourIndex);

			} else {
				System.out.println("The requested site# " + siteIndex
						+ " is already open");
			}
		}
	}

	public boolean percolates() {
		return weightedQUF.connected(virtualTop, virtualBottom);
	}

	private boolean isWithinBoundary(int row, int column) {
		if (row <= 0 || row > siteLength || column <= 0 || column > siteLength) {
			throw new ArrayIndexOutOfBoundsException("Invalid index");
		}
		return true;
	}

	private void joinNeighbours(int sitesIndex, int neighbourIndex) {
		if (neighbourIndex != -1 && sites[neighbourIndex]) {
			System.out.println("Joining Neighbours " + sitesIndex + " and " + neighbourIndex);
			weightedQUF.union(sitesIndex, neighbourIndex);
		}
	}

	private int getIndex(int row, int column) {
		return (row - 1) * siteLength + (column - 1);
	}

	private int getLeftNeighbourIndex(int row, int column) {
		if (column != 1) {
			return getIndex(row, column - 1);
		}
		return -1;
	}

	private int getRightNeighbourIndex(int row, int column) {
		if (column != siteLength) {
			return getIndex(row, column + 1);
		}
		return -1;
	}

	private int getTopNeighbourIndex(int row, int column) {
		if (row != 1) {
			return getIndex(row - 1, column);
		}
		return -1;
	}

	private int getBottomNeighbourIndex(int row, int column) {
		if (row != siteLength) {
			return getIndex(row + 1, column);
		}
		return -1;
	}

	public static void main(String[] args) {
		Percolation _this = new Percolation(5);
		_this.open(1, 5);
		_this.open(2, 5);
		_this.open(3, 5);
		_this.open(4, 5);
		_this.open(5, 5);
		System.out.println(_this.percolates());
	}

}
