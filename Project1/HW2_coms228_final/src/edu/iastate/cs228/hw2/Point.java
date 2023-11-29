 package edu.iastate.cs228.hw2;

/**
 *  
 * @author Lucas Sorge
 *
 */

 /**
  * This class represents a 2D point with integer x and y coordinates.
  * The class provides mechanisms to compare two Point objects based on 
  * either x-coordinate or y-coordinate.
  * 
  */
public class Point implements Comparable<Point>
{
	private int x; // x-coordinate of the point
	private int y; // y-coordinate of the point

	public static boolean xORy;  // compare x coordinates if xORy == true and y coordinates otherwise 
	                             // To set its value, use Point.xORy = true or false. 
	
	/**
	 * Default constructor, initializes a point at the origin (0,0).
	 */
	public Point() 
	{
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Constructs a point with the specified x and y coordinates.
	 * 
	 * @param x The x-coordinate of the point.
	 * @param y The y-coordinate of the point.
	 */
	public Point(int x, int y)
	{
		this.x = x;  
		this.y = y;   
	}
	
	/**
	 * Copy constructor. Constructs a new point with the same x and y coordinates as the given point.
	 * 
	 * @param p The point to copy.
	 */
	public Point(Point p) { // copy constructor
		x = p.getX();
		y = p.getY();
	}

	public int getX()   
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	/** 
	 * Set the value of the static instance variable xORy. 
	 * @param xORy
	 */
	public static void setXorY(boolean xORy)
	{
		Point.xORy = xORy;
	}
	
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
    
		Point other = (Point) obj;
		return x == other.x && y == other.y;   
	}

	/**
	 * Compare this point with a second point q depending on the value of the static variable xORy 
	 * @param 	q 
	 * @return  -1  if (xORy == true && (this.x < q.x || (this.x == q.x && this.y < q.y))) 
	 *                || (xORy == false && (this.y < q.y || (this.y == q.y && this.x < q.x)))
	 * 		    0   if this.x == q.x && this.y == q.y)  
	 * 			1	otherwise 
	 */
	public int compareTo(Point q)
	{	
		if ((xORy == true && (this.x < q.x || (this.x == q.x && this.y < q.y))) || (xORy == false && (this.y < q.y || (this.y == q.y && this.x < q.x)))) {
			return -1;
		} 
		
		if (this.x == q.x && this.y == q.y) {
			return 0;
		}
		
		return 1;
	}
	
	
	/**
	 * Output a point in the standard form (x, y). 
	 */
	@Override
    public String toString() 
	{
		return ("(" + getX() + ", " + getY() + ")"); 
	}
}
