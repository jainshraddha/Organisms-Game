/**
 * This class represents a point on the grid. It holds the x and y coordinates
 * of the point
 * 
 * @author ShadyJ
 *
 */
public class Point {
	private int x;
	private int y;

	/**
	 * This is the constructor for the class
	 * 
	 * @param x
	 *            - the x coordinate
	 * @param y
	 *            - the y coordinate
	 */
	public Point(int x, int y) {

		this.x = x;
		this.y = y;

	}

	/**
	 * This is the getter method for the x coordinate of the point
	 * 
	 * @return x - the x coordinate of the point
	 */
	public int getX() {
		return x;
	}

	/**
	 * This is the getter method for the y coordinate of the point
	 * 
	 * @return y - the y coordinate of the point
	 */
	public int getY() {
		return y;
	}
}
