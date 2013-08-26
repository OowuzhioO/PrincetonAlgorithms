package edu.princeton.algorithms.part1.lecture1;

public class QuickFindImpl {

	private int[] numbers;

	public QuickFindImpl(int n) {
		numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = i;
		}
	}

	public boolean isConnected(int p, int q) {
		return numbers[p] == numbers[q];
	}

	public void union(int p, int q) {
		int pid = numbers[p];
		int qid = numbers[q];

		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == pid) {
				numbers[i] = qid;
			}
		}
	}

	public static void main(String[] args) {
		QuickFindImpl anImpl = new QuickFindImpl(10);

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
	}

}
