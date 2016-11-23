package core;

public class Perm {
	private int[] v;
	private int[] w;
	private int len;
	private boolean hasNext;

	public Perm(int len) {
		if (len < 2) {
			throw new IllegalArgumentException("Constraint: len >= 2");
		}

		this.len = len;
		v = new int[len];
		w = new int[len];
		reset();
	}

	public int len() {
		return len;
	}

	public boolean hasNext() {
		return hasNext;
	}

	public int[] next() {
		if (!hasNext) {
			throw new IllegalStateException("No more permutations");
		}

		System.arraycopy(v, 0, w, 0, len);

		int i = len - 2;
		while (v[i] > v[i + 1]) {
			if (--i < 0) {
				hasNext = false;
				return w;
			}
		}

		swap(i, swapIndex(i));
		reverse(i + 1);
		return w;
	}

	public void reset() {
		for (int i = 0; i < len; i++) {
			v[i] = i;
		}
		hasNext = true;
	}

	private int swapIndex(int i) {
		int ans = -1;
		int value = len;
		for (int j = i + 1; j < len; j++) {
			if (v[i] < v[j] && v[j] < value) {
				ans = j;
				value = v[j];
			}
		}
		return ans;
	}

	private void swap(int i, int j) {
		int t = v[i];
		v[i] = v[j];
		v[j] = t;
	}

	private void reverse(int i) {
		for (int a = i, z = v.length - 1; a < z; a++, z--) {
			int t = v[a];
			v[a] = v[z];
			v[z] = t;
		}
	}
}
