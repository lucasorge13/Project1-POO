package edu.iastate.cs228.hw2;

/**
 *  
 * @author Lucas Sorge
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 

/**
 * This class is designed to execute and compare four different sorting algorithms: 
 * selection sort, insertion sort, mergesort, and quicksort. It runs these algorithms 
 * on point data either generated randomly or read from a file, and then compares 
 * their execution times.
 */
public class CompareSorters 
{
	
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{	
		// The array of points to be sorted.
		Point[] points = null;
		// Array of scanners, one for each sorting algorithm.
		PointScanner[] scanners = new PointScanner[4];
		
		int trials = 1; // tracks number of trials
		int input = 0; // User's input choice.
		
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)");
		
		while (input != 3) {
			System.out.print("Trial " + trials + ": ");
			input = scnr.nextInt();

			if (input == 1) {
				System.out.print("Enter number of random points: ");
				int number = scnr.nextInt();
				points = generateRandomPoints(number, new Random());
				scanners[0] = new PointScanner(points, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(points, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(points, Algorithm.MergeSort);
				scanners[3] = new PointScanner(points, Algorithm.QuickSort);
			} else if (input == 2) {
				System.out.println("Points from a file");
				System.out.print("File name: ");
				String fileName = scnr.next();
				scanners[0] = new PointScanner(fileName, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(fileName, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(fileName, Algorithm.MergeSort);
				scanners[3] = new PointScanner(fileName, Algorithm.QuickSort);
			} else {
				break;
			}
			
			System.out.println("");
			System.out.printf("%-17s %-10s %-10s \n", "algorithm", "size", "time (ns)");
			System.out.println("--------------------------------------");
			for (int i = 0; i < scanners.length; i++) {
				scanners[i].scan();
				scanners[i].writeMCPToFile();
				System.out.println(scanners[i].stats());
			}
			System.out.println("----------------------------------");
			trials++;
		}
		scnr.close();
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		if (numPts < 1) {
			throw new IllegalArgumentException("Illegal Argument! numPts is less than 1");
		}
		
		Point[] point = new Point[numPts];
		int x;
		int y;
		
		for(int i = 0; i < numPts; i++) {
			x = rand.nextInt(101) - 50;
			y = rand.nextInt(101) - 50;
			
			point[i] = new Point(x, y);
		}
		return point;
	}	
}