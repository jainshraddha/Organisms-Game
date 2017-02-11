/**
 * This class is associated with each instance of an organism It keeps track of
 * the individual data for each organism. This class is only accessed by the
 * game.
 * 
 * @author ShadyJ
 *
 */
public class IndividualOrganismData {

	private int energy;
	private int key;
	private Player playerInstance;
	// check how to add this, this will make your life very easy
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
	 * @param point
	 *            - the point the organism is on
	 * @param key
	 *            - the key of the organism, this is common between all of its
	 *            generations
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

	/**
	 * this is a getter method for the key of the organism
	 * 
	 * @return key - the key of the organism
	 */
	public int getKey() {
		return key;
	}

	/**
	 * This is a getter method that tells the game if the organism has already
	 * moved in the round
	 * 
	 * @return alreadyMovedInRound - boolean to keep track of if the organism
	 *         has already moved in the round
	 */
	public boolean hasAlreadyMovedInRound() {
		return alreadyMovedInRound;
	}

	/**
	 * This is the setter method for alreadyMovedInRound
	 * 
	 * @param alreadyMovedInRound
	 *            - boolean to keep track of if the organism has already moved
	 *            in the round
	 */
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
	 * This method returns the object reference for the organism
	 * 
	 * @return playerInstance - the object reference to the player type.
	 */
	public Player getPlayerInstance() {
		return playerInstance;
	}

	/**
	 * This is the getter method for the point the organism is on
	 * 
	 * @return point - the point the organism is on
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * This is the setter method for the point the organism is on
	 * 
	 * @param point
	 */
	public void setPoint(Point point) {
		this.point = point;
	}

}
