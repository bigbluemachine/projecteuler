package s100;

import java.util.LinkedList;

public class P186 {
	static final LinkedList<Integer> L = new LinkedList<Integer>();
	static final int M = 1000000, PM = 524287, TARGET = 990000;
	static final int[] F = new int[M];
	static final Node[] N = new Node[M / 2 + 1];

	static boolean STOP = false;
	static int INDEX = 1, CALLS = 0;

	static void process(int a, int b) {
		CALLS++;

		if (F[a] == 0) {
			if (F[b] == 0) {
				N[F[a] = F[b] = INDEX] = new Node(INDEX++, 2);
			} else {
				N[F[b]].call(a);
			}
		} else {
			if (F[b] == 0) {
				N[F[a]].call(b);
			} else {
				N[F[a]].merge(N[F[b]]);
			}
		}
	}

	static class Node {
		int root, count;

		Node(int r, int c) {
			root = r;
			count = c;
		}

		Node getRoot() {
			int x = root;
			Node r = N[root];
			while (x != r.root) {
				r = N[x = r.root];
			}
			return r;
		}

		void bubbleDown(int r) {
			if (root != r) {
				N[root].bubbleDown(root = r);
			}
		}

		void add(int i) {
			count += i;
			if (count >= TARGET && F[PM] > 0) {
				Node t = getRoot(), n = N[F[PM]].getRoot();
				if (t.root == n.root) {
					STOP = true;
				}
			}
		}

		void call(int a) {
			Node r = getRoot();
			F[a] = r.root;
			r.add(1);
			bubbleDown(r.root);
		}

		void merge(Node other) {
			Node r = getRoot(), o = other.getRoot();
			if (o.root != r.root) {
				r.add(o.count);
				bubbleDown(r.root);
				other.bubbleDown(r.root);
			}
		}
	}

	static int run() {
		for (int k = 1; k <= 55; k++) {
			L.add((int) ((100003L - 200003L * k + 300007L * k * k * k) % M));
		}

		for (int i = 0; i < 54; i += 2) {
			process(L.get(i), L.get(i + 1));
		}

		int a, b;
		L.add(a = (L.removeFirst() + L.get(30)) % M);
		process(L.get(53), a);

		while (!STOP) {
			L.add(a = (L.removeFirst() + L.get(30)) % M);
			L.add(b = (L.removeFirst() + L.get(30)) % M);
			if (a != b) {
				process(a, b);
			}
		}

		return CALLS;
	}

	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		int ans = run();
		System.out.printf("Answer: %d\n", ans);
		System.out.printf("Time: %d ms\n", System.currentTimeMillis() - t);
	}
}
