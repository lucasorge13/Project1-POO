package edu.iastate.cs228.hw2;

/**
 * 
 * @author Lucas Sorge
 *
 */

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;


/**
 * 
 * This class is responsible for scanning a set of points to determine the median coordinate point.
 * It uses various sorting algorithms to determine the median x and y coordinates and provides
 * mechanisms to compare the performance of these sorting algorithms.
 * 
 */
public class PointScanner 
{
	private Point[] points; // Array of points to be scanned.
	
	public Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of the x coordinates and y coordinates of those points in the array points[].
	
	private Algorithm sortingAlgorithm; // The sorting algorithm to be used.
	
	private String outputFileName; // File to which the results should be written.
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if(pts == null || pts.length == 0) {
			throw new IllegalArgumentException("Points array is null or empty");
		}
		
		points = new Point[pts.length];
		
		for (int i = 0; i < pts.length; i++) {
			Point p = new Point(pts[i]);
			points[i] = p;
		}
		
		sortingAlgorithm = algo;
		if (sortingAlgorithm == Algorithm.InsertionSort) {
			outputFileName = "insert.txt";
		} else if (sortingAlgorithm == Algorithm.MergeSort) {
			outputFileName = "merge.txt";
		} else if (sortingAlgorithm == Algorithm.QuickSort) {
			outputFileName = "quick.txt";
		} else if (sortingAlgorithm == Algorithm.SelectionSort) {
			outputFileName = "selection.txt";
		}
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		
	sortingAlgorithm = algo;
	if (sortingAlgorithm == Algorithm.InsertionSort) {
		outputFileName = "insert.txt";
	} else if (sortingAlgorithm == Algorithm.MergeSort) {
		outputFileName = "merge.txt";
	} else if (sortingAlgorithm == Algorithm.QuickSort) {
		outputFileName = "quick.txt";
	} else if (sortingAlgorithm == Algorithm.SelectionSort) {
		outputFileName = "selection.txt";
	}
	
	File file = new File(inputFileName);
	Scanner scnr = new Scanner(file);
	Scanner scnr2 = new Scanner(file);
	
	int count = 0;
	int length = 0;
	
	while (scnr.hasNextInt()) {
		scnr.nextInt();
		count++;
	}
	if (count % 2 != 0) {
		scnr.close();
		scnr2.close();
		throw new InputMismatchException("File contains an odd number of integers");
	}
	
	length = count / 2;
	points = new Point[length];
	
	for (int i = 0; i < length; i++) {
		points[i] = new Point(scnr2.nextInt(), scnr2.nextInt());
	}
	
	scnr.close();
	scnr2.close();
	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan() throws IllegalStateException
	{
				if(points == null || points.length == 0) {
			throw new IllegalStateException("No point to scan");
		}
		
		AbstractSorter aSorter; 
        switch (sortingAlgorithm)
        {
            case SelectionSort:
                aSorter = new SelectionSorter(points);
                break;
            case InsertionSort:
            	aSorter = new InsertionSorter(points);
                break;
            case MergeSort:
            	aSorter = new MergeSorter(points);
                break;
            case QuickSort:
            	aSorter = new QuickSorter(points);
                break;
            default:
                throw new IllegalStateException("Unknown sorting algorithm.");
        }
		
        // Sort by x-coordinate and get the median x-coordinate
        aSorter.setComparator(0);  // x-coordinate
        long startTime = System.nanoTime();
        aSorter.sort();
        long endTime = System.nanoTime();
        scanTime = endTime - startTime;
        int medianX = aSorter.getMedian().getX();
        
        // Sort by y-coordinate and get the median y-coordinate
        aSorter.setComparator(1);  // y-coordinate
        startTime = System.nanoTime();
        aSorter.sort();
        endTime = System.nanoTime();
        scanTime += endTime - startTime;
        int medianY = aSorter.getMedian().getY();
        
        medianCoordinatePoint = new Point(medianX, medianY);	
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String returnString = String.format("%-17s %-10d %-10d", sortingAlgorithm, points.length, scanTime);
		return returnString;
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		String returnString = "";
		for (int i = 0; i< points.length; i++) {
			returnString += points[i].getX() + " " + points[i].getY() + "\n";
		}
		
		return returnString;
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		File file = new File(outputFileName);
		PrintWriter pw = new PrintWriter(file);
		pw.write(this.toString());
		pw.close();
	}			
}
