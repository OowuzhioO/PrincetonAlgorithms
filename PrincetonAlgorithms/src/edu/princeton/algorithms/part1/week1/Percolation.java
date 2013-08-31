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
		sites = new boolean[sitesCount + 2];

		weightedQUF = new WeightedQuickUnionUF(sitesCount + 2);

		virtualTop = sitesCount;
		virtualBottom = sitesCount + 1;

		sites[virtualTop] = true;
		sites[virtualBottom] = true;
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
					joinNeighbours(siteIndex, virtualTop);
				} else if (row == siteLength) {
					joinNeighbours(siteIndex, virtualBottom);
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

	// is site (row i, column j) full?
	public boolean isFull(int row, int column) {
		if (isWithinBoundary(row, column)) {
			int siteIndex = getIndex(row, column);
			return (weightedQUF.connected(virtualTop, siteIndex) || weightedQUF
					.connected(virtualBottom, siteIndex));
		}
		return false;
	}

	private boolean isWithinBoundary(int row, int column) {
		if (row <= 0 || row > siteLength || column <= 0 || column > siteLength) {
			throw new ArrayIndexOutOfBoundsException("Invalid index");
		}
		return true;
	}

	private void joinNeighbours(int sitesIndex, int neighbourIndex) {
		if (neighbourIndex != -1 && sites[neighbourIndex]) {
			System.out.println("Joining Neighbours " + sitesIndex + " and "
					+ neighbourIndex);
			weightedQUF.union(sitesIndex, neighbourIndex);
		}
	}

	private int getIndex(int row, int column) {
		return (row - 1) * siteLength + (column - 1);
	}

	private int getLeftNeighbourIndex(int row, int column) {
		return (column != 1) ? getIndex(row, column - 1) : -1;
	}

	private int getRightNeighbourIndex(int row, int column) {
		return (column != siteLength) ? getIndex(row, column + 1) : -1;
	}

	private int getTopNeighbourIndex(int row, int column) {
		return (row != 1) ? getIndex(row - 1, column) : -1;
	}

	private int getBottomNeighbourIndex(int row, int column) {
		return (row != siteLength) ? getIndex(row + 1, column) : -1;
	}

	public static void main(String[] args) {
		Percolation _this = new Percolation(5);
		_this.open(3, 3);
		System.out.println(_this.isFull(3, 3));

		_this.open(1, 1);
		_this.open(1, 2);
		_this.open(2, 2);
		_this.open(3, 2);
		_this.open(5, 3);
		_this.open(4, 3);
		System.out.println(_this.isFull(3, 3));

		System.out.println(_this.percolates());
	}

}
