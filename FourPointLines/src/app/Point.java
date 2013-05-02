package app;

import java.util.Comparator;

public class Point implements Comparable<Point> {
	private int x, y;

	// compare points by slope to this point
	public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

		// Comparing the slopes with respect to the current Point object
		public int compare(Point p1, Point p2) {
			Point p0 = new Point(x, y);
			if (p1.slopeTo(p0) > p2.slopeTo(p0)) {
				return 1; // p1 is more than p2
			} else if (p1.slopeTo(p0) < p2.slopeTo(p0)) {
				return -1; // p1 is less than p2
			} else if (p1.slopeTo(p0) == p2.slopeTo(p0)) {
				return 0; // same line segment
			}
			return 0;
		}
	};

	// construct the point (x, y)
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// string representation (x,y)
	public String toString() {
		return new StringBuffer().append("(").append(x).append(", ").append(y)
				.append(")").toString();
	}

	// is this point lexicographically smaller than that point?
	public int compareTo(Point that) {
		int result = 0;
		if (this.y < that.y) {
			result = 1; // slope is less
		} else if (this.y > that.y) {
			result = -1; // slope is more
		} else if (this.x < that.x) {
			result = -1; // slope is more
		} else if (this.x > that.x) {
			result = 1; // slope is less
		} else {
			result = 0; // points are same
		}
		return result;
	}

	// the slope between this point and that point
	public double slopeTo(Point that) {
		double ydiff = (double) (that.y - this.y);
		double xdiff = (double) (that.x - this.x);
		double slope = 0.0;
		if (0 == ydiff && 0 == xdiff) {
			slope = Double.NEGATIVE_INFINITY;
		} else if (0 == ydiff) {
			// same y coords (0 == ydiff)
			// horizontal line
			// slope = 0;
		} else if (0 == xdiff) {
			// same x coords (0 == xdiff)
			// slope = (that.y - this.y) / (that.x - this.x);
			// +div by zero infinity
			slope = Double.POSITIVE_INFINITY;
		} else {
			slope = ydiff / xdiff;
		}
		return slope;
	}
}
