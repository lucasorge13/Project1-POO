package edu.iastate.cs228.hw2;

/**
 *  
 * @author Lucas Sorge
 *
 */

/**
 * 
 * This class is an implementation of the merge sort algorithm for sorting arrays of Point objects.
 * It extends the abstract class AbstractSorter. 
 *
 */

public class MergeSorter extends AbstractSorter
{	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		
		algorithm = "mergesort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(points); 
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{		
		if (pts.length <= 1) {
			return;
		}
		
		Point[] left = new Point[pts.length / 2];
		Point[] right = new Point[pts.length - (pts.length / 2)];
		
		for (int i = 0; i < left.length; i++) {
			left[i] = pts[i];
		}
		
		for(int i = 0; i < right.length; i++) {
			right[i] = pts[left.length + i];
		}
		
		mergeSortRec(left);
		mergeSortRec(right);
		merge(pts, left, right);
	}

	private void merge(Point[] pts, Point[] left, Point[] right) {
		int firstLeft = 0, firstRight = 0, currentIndex = 0; 
		
		while ((firstLeft < left.length) && (firstRight < right.length)) {
			if (pointComparator.compare(left[firstLeft], right[firstRight]) < 0) {
				pts[currentIndex] = left[firstLeft++];
			} else {
				pts[currentIndex] = right[firstRight++];
			}
			currentIndex++;
		}
		
		while (firstLeft < left.length) {
			pts[currentIndex++] = left[firstLeft++];
		}
		while (firstRight < right.length) {
			pts[currentIndex++] = right[firstRight++];
		}
	}
}
