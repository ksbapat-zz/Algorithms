package app;

public class MergeSort {

	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	// Recursion for sorting
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	// Merge the sorted arrays using aux
	public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid,
			int hi) {
		// copy to aux[]
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		// merge back to a[]
		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
		// now a[lo .. hi] is sorted
	}

	// Is v < w ?

	private static boolean less(Comparable v, Comparable w) {
		return (v.compareTo(w) < 0);
	}
}
