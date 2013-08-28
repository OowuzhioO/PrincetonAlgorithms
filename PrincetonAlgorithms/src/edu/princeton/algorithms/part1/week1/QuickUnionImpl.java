package edu.princeton.algorithms.part1.week1;

public class QuickUnionImpl {

	private int[] numbers;

	public QuickUnionImpl(int n) {
		numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = i;
		}
	}

	private int root(int i) {
		while (i != numbers[i]) {
			i = numbers[i];
		}

		return i;
	}

	public boolean isConnected(int i, int j) {
		return root(i) == root(j);
	}

	public void union(int i, int j) {
		numbers[root(i)] = root(j);
	}

	public static void main(String[] args) {
	}

}
