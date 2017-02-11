import java.util.HashMap;

/**
 * This class implements the PlayerRoundData class It provides data of how a
 * player does in the game
 * 
 * @author ShadyJ
 *
 */

public class PlayerData implements PlayerRoundData {

	private int playerID;
	private int energy;
	private int count;

	/**
	 * This is the constructor for the class. It initializes the data values for
	 * each of the organisms
	 * 
	 * @param playerID
	 *            - unique ID of the player
	 * @param energy
	 *            - total energy left for the organism
	 * @param count
	 *            - total count of the type of organism
	 */
	public PlayerData(int playerID, int energy, int count) {
		this.playerID = playerID;

		this.energy = energy;
		this.count = count;

	}

	/**
	 * This is the getter method for the unique player ID
	 */
	@Override
	public int getPlayerId() {

		return playerID;
	}

	/**
	 * This is the getter method for the total energy remaining for the organism
	 */
	@Override
	public int getEnergy() {

		return energy;
	}

	/**
	 * This is the getter method for the total count remaining for the type of
	 * organism
	 */
	@Override
	public int getCount() {

		return count;
	}

}
