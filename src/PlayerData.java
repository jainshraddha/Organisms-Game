import java.util.HashMap;

/**
 * This class implements the PlayerRoundData class It provides data of how an
 * organism does in each round of the game
 * 
 * @author ShadyJ
 *
 */

public class PlayerData implements PlayerRoundData {

	private int playerID;
	private int energy;
	private int count;
	private HashMap <Player,IndividualOrganismData> individualData;
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
	public PlayerData(int playerID, int energy, int count, HashMap <Player,IndividualOrganismData> individualData) {
		this.playerID = playerID;
		this.individualData = individualData;
		this.energy = energy;
		this.count = count;

	}


	/**
	 * This is the getter method for the unique player ID
	 */
	@Override
	public int getPlayerId() {
		// TODO Auto-generated method stub
		return playerID;
	}

	/**
	 * This is the getter method for the total energy remaining for the organism
	 */
	@Override
	public int getEnergy() {
		
		for (Player pl: individualData.keySet()) { 
			
			int id = individualData.get(pl).getKey();
			
			if (id == playerID) {
				System.out.println("Existing energy on record: " + energy);
			int energy1 = individualData.get(pl).getEnergy();
			System.out.println("Energy for: " + individualData.get(pl).getPlayerInstance() + " = " + energy1);
			this.energy += energy1; 
			}		
		}
		// TODO Auto-generated method stub
		return energy;
	}

	/**
	 * This is the getter method for the total count remaining for the type of
	 * organism
	 */
	@Override
	public int getCount() {
		
		for (Player pl: individualData.keySet()) { 
			
			int id = individualData.get(pl).getKey();
			
			if (id == playerID) {
			this.count += 1;
			}		
		}
		// TODO Auto-generated method stub
		return count;
	}

	
}
