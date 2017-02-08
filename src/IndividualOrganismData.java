/**
 * This class is associated with each instance of an organism It keeps track of
 * the individual energy level of the organism. It also keeps track of the
 * position on the grid. This class is only accessed by the game.
 * 
 * @author ShadyJ
 *
 */
public class IndividualOrganismData {

	private int energy;
	private int key; 
	private Player playerInstance;
	//check how to add this, this will make your life very easy 
	private Player playertype;
	// private int xCoordinate;
	// private int yCoordinate;
	private Point point;
	private boolean alreadyMovedInRound; 

	/**
	 * This is the constructor for the class. It initializes the values for the
	 * energy level, player object, and its coordinates on the grid
	 * 
	 * @param energy
	 *            - energy level of the individual instance of the organism type
	 * @param playerInstance
	 *            - holds the object reference for the instance created
	 * @param x
	 *            - x coordinate of the organism
	 * @param y
	 *            - y coordinate of the organism
	 */
	public IndividualOrganismData(int energy, Player playerInstance, Point point, int key) {
		this.energy = energy;
		alreadyMovedInRound = false;
		this.playerInstance = playerInstance;
		this.key = key; 
		// xCoordinate = x;
		// yCoordinate = y;
		this.point = point;
	}

	
	
	
	public int getKey() {
		return key;
	}




	public boolean hasAlreadyMovedInRound() {
		return alreadyMovedInRound;
	}




	public void setAlreadyMovedInRound(boolean alreadyMovedInRound) {
		this.alreadyMovedInRound = alreadyMovedInRound;
	}




	/**
	 * This is the getter method for the energy left for the individual organism
	 * 
	 * @return energy - energy left for the individual organism
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * This is the setter method for the individual organism
	 * 
	 * @param energy
	 *            - energy for the individual organism
	 */
	public void setEnergy(int energy) {
		this.energy += energy;
	}

	/**
	 * This is the getter method for the x coordinate for the individual
	 * organism
	 * 
	 * @return xCoordinate - x coordinate of the organism on the grid
	 */
	/*
	 * public int getxCoordinate() { return xCoordinate; }
	 * 
	 * /** This is the setter method for the x coordinate for the individual
	 * organism
	 */
	/*
	 * public void setxCoordinate(int xCoordinate) { this.xCoordinate =
	 * xCoordinate; }
	 * 
	 * /** This is the getter method for the y coordinate for the individual
	 * organism
	 * 
	 * @return yCoordinate - y coordinate of the organism on the grid
	 */
	/*
	 * public int getyCoordinate() { return yCoordinate; }
	 * 
	 * /** This is the setter method for the x coordinate for the individual
	 * organism
	 */
	/*
	 * public void setyCoordinate(int yCoordinate) { this.yCoordinate =
	 * yCoordinate; }
	 * 
	 * /** This method returns the object reference for the organism
	 * 
	 * @return playerInstance - the object reference to the player type.
	 */
	public Player getPlayerInstance() {
		return playerInstance;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

}
