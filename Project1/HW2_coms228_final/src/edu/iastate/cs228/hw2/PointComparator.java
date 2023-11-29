package edu.iastate.cs228.hw2;

/**
 * 
 * @author Lucas Sorge
 * 
 **/

import java.util.Comparator;

public class PointComparator implements Comparator<Point>{

	@Override
	public int compare(Point p1, Point p2) {		
		return p1.compareTo(p2);
	}
}