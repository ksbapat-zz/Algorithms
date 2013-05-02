package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Lines {
	private static String inputFilename = "input.txt";

	public static void main(String[] args) {
		Point[] points;
		try {
			points = getInputPoints(inputFilename);
			if (null != points && points.length > 4) {
				Set<String> lines = getLines(points);
				print(lines);
			} else {
				System.out.println("Invalid input or insufficient points");
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	/**
	 * Returns all lines with minimum 4 points
	 * 
	 * @param points
	 */
	private static Set<String> getLines(Point[] points) {
		Set<String> lines = new HashSet<String>();
		for (int i = 0; i < points.length; i++) {
			Point point = points[i];
			Map<Double, List<Point>> slopeMap = new HashMap<Double, List<Point>>();

			for (int j = 0; j < points.length; j++) {
				double slope = point.slopeTo(points[j]);
				if (point == points[j]) {
					// will add current point later to all the line segments
				} else if (slopeMap.containsKey(slope)) {
					slopeMap.get(slope).add(points[j]);
				} else {
					List<Point> list = new ArrayList<Point>();
					list.add(points[j]);
					slopeMap.put(new Double(slope), list);
				}
			}

			Iterator<List<Point>> iterator = slopeMap.values().iterator();
			while (iterator.hasNext()) {
				List<Point> itrList = iterator.next();
				if (itrList.size() >= 3) {
					itrList.add(point);
					Point[] line = itrList.toArray(new Point[itrList.size()]);
					MergeSort.sort(line);
					lines.add(convertPointsArrToString(line));
				}
			}
		}
		return lines;
	}

	private static void print(Set<String> lines) {
		for (Iterator iterator = lines.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
		}
	}

	private static void printListOfPointArrs(List<Point[]> lines) {
		for (Iterator iterator = lines.iterator(); iterator.hasNext();) {
			Point[] points = (Point[]) iterator.next();
			System.out.println(convertPointsArrToString(points));
		}
	}

	private static String convertPointsArrToString(Point[] points) {
		StringBuffer line = new StringBuffer();
		for (int i = 0; i < points.length; i++) {
			line.append(points[i]);
			if (i != points.length - 1) {
				line.append(" -> ");
			}
		}
		return line.toString();
	}

	/**
	 * Returns the set of points from the input file
	 * 
	 * @param fileName
	 * @return
	 */
	private static Point[] getInputPoints(String fileName) throws Exception {
		BufferedReader br = null;
		String line = null;
		try {
			File inputFile = new File(fileName);
			FileReader fileRdr = new FileReader(inputFile);
			br = new BufferedReader(fileRdr);
			line = br.readLine();
		} catch (FileNotFoundException fnfEx) {
			throw new Exception("File Not Found - " + fileName);
		} catch (IOException ioEx) {
			throw new Exception("Unable to read file - " + fileName);
		}
		int numOfPoints;
		try {
			numOfPoints = Integer.valueOf(line).intValue();
		} catch (NumberFormatException nfEx) {
			throw new Exception("Invalid Input - " + line.toString());
		}
		Point[] points = new Point[numOfPoints];
		try {
			int x, y;
			int idx = 0;
			line = br.readLine();
			while (null != line && idx < numOfPoints) {
				// create point from the line in the inputfile
				StringTokenizer strTok = new StringTokenizer(line);
				x = Integer.valueOf(strTok.nextToken()).intValue();
				y = Integer.valueOf(strTok.nextToken()).intValue();
				points[idx] = new Point(x, y);

				idx++;
				line = br.readLine();
			}
			if (idx != numOfPoints) {
				throw new Exception(
						"Invalid Input - Expected lines in the input file - "
								+ numOfPoints);
			}
		} catch (IOException ioEx) {
			throw new Exception("Unable to read file - " + fileName);
		} catch (NumberFormatException nfEx) {
			throw new Exception("Invalid Input - " + line.toString());
		} catch (Exception ex) {
			throw ex;
		}
		return points;
	}
}