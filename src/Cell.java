/**
 * This class represents each cell in the grid. 
 * It contains information about the cell. 
 * @author ShadyJ
 *
 */
public class Cell {
	private Point point; 
	//private int xCoordinate;
	//private int yCoordinate;
	private boolean occupied;
	private Player resident;
	// private double totalFood;
	private int foodUnits;
/**
 * This is the constructor for the cell. 
 * It initializes the cell with the coordinates. 
 * It initializes the rest of the global variables to 0 or null. 
 * @param x
 * @param y
 */
	public Cell(Point p) {
		point = p; 
		occupied = false;
		foodUnits = 0;
		resident = null;
		// totalFood = 0;
	}
/**
 * This is the getter method for the type of the player residing in the cell 
 * @return
 */
	public Player getResident() {
		return resident;
	}
/**
 * This is the settee method that allows the game to set occupancy for the cell. 
 * It also allows the game to set the type of player occupying the cell. 
 * @param type - the type of Player occupying the cell 
 */
	public void setOccupancy(Player type) {
		resident = type;
		occupied = true;
	}
/**
 * This is the setter method that allows the game to release the occupancy of the cell. 
 * It can be used in the event that a player decides to move from this cell 
 * or if a player dies.  
 */
	public void releaseOccupancy() {
		resident = null; 
		occupied = false;

	}
	
/**
 * This method allows the game to increase the amount of food on the cell. 
 * @param increaseBy - the factor by which to increase the food 
 */
	public void changeFood(int increaseBy) {
		foodUnits += increaseBy;
	}
/**
 * This is the getter method for the x coordinate of the cell 
 * @return xCoordinate - the x coordinate of the cell on the grid
 */
	/*public int getxCoordinate() {
		return xCoordinate;
	}
/**
 * This is the getter method for the y coordinate for the cell 
 * @return yCoordinate = the y coordinate of the cell on the grid 
 */
/*	public int getyCoordinate() {
		return yCoordinate;
	}
/**
 * This is the getter method to determine if the cell is occupied or not 
 * @return - occupied - boolean value that returns true if the cell is occupied 
 */
	public boolean isOccupied() {
		return occupied;
	}

	/*
	 * public double getTotalFood() { return totalFood; }
	 */
	
	public Point getPoint() {
	return point;
}
	//check if this should be allowed
	public void setPoint(Point point) {
	this.point = point;
}
	/**
	 * This is the getter method that returns the total units of food available on the cell 
	 * @return foodUnits - total units of food on the cell 
	 */
	public int getFoodUnits() {
		return foodUnits;
	}

}
