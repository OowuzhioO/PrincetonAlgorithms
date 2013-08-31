package edu.princeton.algorithms.part1.week1;

public class WeightedQuickUnionImpl {

	private int[] numbers;
	private int[] size;

	public WeightedQuickUnionImpl(int n) {
		numbers = new int[n];
		size = new int[n];

		for (int i = 0; i < n; i++) {
			numbers[i] = i;
			size[i] = 1;
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
		if (size[i] <= size[j]) {
			numbers[root(i)] = root(j);
			size[j] += size[i];
		} else {
			numbers[root(j)] = root(i);
			size[i] += size[j];
		}
	}

	public static void main(String[] args) {
		WeightedQuickUnionImpl anImpl = new WeightedQuickUnionImpl(10);

		anImpl.union(1, 2);
		anImpl.union(5, 6);
		anImpl.union(0, 5);
		anImpl.union(6, 1);
		anImpl.union(2, 7);

		anImpl.union(3, 8);
		anImpl.union(3, 4);
		anImpl.union(4, 9);

		System.out.println(anImpl.isConnected(0, 7));
		System.out.println(anImpl.isConnected(0, 9));
		System.out.println(anImpl.isConnected(8, 9));
		System.out.println(anImpl.isConnected(8, 2));
		System.out.println(anImpl.isConnected(1, 9));

	}

}
