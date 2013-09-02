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

	public void open(int row, int column) {
		isWithinBoundary(row, column);
		int siteIndex = getIndex(row, column);
		if (!sites[siteIndex]) {
			sites[siteIndex] = true;

			connectWithLeftNeighbor(row, column);
			connectWithRightNeighbor(row, column);
			connectWithTopNeighbor(row, column);
			connectWithBottomNeighbor(row, column);

			if (row == 0) {
				weightedQUF.union(siteIndex, virtualTop);
			} else if ((row == siteLength - 1) && isFull(row, column)) {
				weightedQUF.union(siteIndex, virtualBottom);
			}

		}
	}

	public boolean percolates() {
		return weightedQUF.connected(virtualTop, virtualBottom);
	}

	public boolean isFull(int row, int column) {
		isWithinBoundary(row, column);
		int siteIndex = getIndex(row, column);
		return weightedQUF.connected(virtualTop, siteIndex);
	}

	public boolean isOpen(int row, int column) {
		isWithinBoundary(row, column);
		return sites[getIndex(row, column)];
	}

	private boolean isWithinBoundary(int row, int column) {
		if (row < 0 || row > siteLength)
			throw new ArrayIndexOutOfBoundsException("row index " + row
					+ " must be between 0 and " + (siteLength - 1));

		if (column < 0 || column > siteLength) {
			throw new ArrayIndexOutOfBoundsException("row index " + column
					+ " must be between 0 and " + (siteLength - 1));
		}
		return true;
	}

	private int getIndex(int row, int column) {
		return (row * siteLength) + (column);
	}

	private void connectWithLeftNeighbor(int row, int column) {
		if (column != 0 && isOpen(row, column - 1))
			weightedQUF.union(getIndex(row, column), getIndex(row, column - 1));

	}

	private void connectWithRightNeighbor(int row, int column) {
		if (column != siteLength - 1 && isOpen(row, column + 1))
			weightedQUF.union(getIndex(row, column), getIndex(row, column + 1));

	}

	private void connectWithTopNeighbor(int row, int column) {
		if (row != 0 && isOpen(row - 1, column))
			weightedQUF.union(getIndex(row, column), getIndex(row - 1, column));

	}

	private void connectWithBottomNeighbor(int row, int column) {
		if (row != siteLength - 1 && isOpen(row + 1, column))
			weightedQUF.union(getIndex(row, column), getIndex(row + 1, column));

	}
}
