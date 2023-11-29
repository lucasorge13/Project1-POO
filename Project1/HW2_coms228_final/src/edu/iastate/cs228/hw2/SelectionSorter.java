package edu.iastate.cs228.hw2;

/**
 *  
 * @author Lucas Sorge
 *
 */

/**
 * 
 * This class implements the selection sort algorithm.
 * It extends the AbstractSorter class and provides the mechanism to sort an array
 * of points using the selection sort technique. 
 *
 */

public class SelectionSorter extends AbstractSorter
{
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		algorithm = "selection sort";
	}	

	/** 
	 * Implement the selection sort algorithm on the array of points.
	 * For every index in the array, it finds the smallest item from that index to the end
	 * and swaps them.
	 */
	@Override 
	public void sort()
	{
		for(int i = 0; i < points.length - 1; i++) {
			int min_index = i;
			for (int j = i + 1; j < points.length; j++) {
				if (pointComparator.compare(points[j], points[min_index]) < 0) {
					min_index = j;
				}
			}
			
			swap(i, min_index);
		}
	}	
}
