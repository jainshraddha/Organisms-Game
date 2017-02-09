import java.util.*;
/**
 * This is the class that represents a human player in the game of Organisms 
 * It implements the Player class 
 * @author ShadyJ
 *
 */
public class HumanPlayer implements Player {
	private static final String NAME = "Human Player";
	private Random rand;
	private int state;
	private GameConfig game;
	private int key; 
	private int energyLeft; 
	private int foodLeft; 
	/**
	 * This method is called when the Organism is created.
	 * @param key -  The key is the value that is passed to this organism by its parent.
	 * @param game - The configurations of the game that is passed to the newly created organism 
	 */
	@Override
	public void register(GameConfig game, int key) {
		rand = new Random();
		state = rand.nextInt(256);
		this.game = game; 
		this.key = key; 
	}
/**
 * This is the getter method for the name of the player
 */
	@Override
	public String name() {
		
		return NAME;
	}

	@Override
	public Move move(boolean[] food, int[] neighbors, int foodleft, int energyleft) {
	
		Move move = null; 
		int direction = rand.nextInt(6);

		switch (direction) {
		case 0:
			move = new Move(Constants.STAYPUT);
			break;
		case 1:
			move = new Move(Constants.WEST);
			break;
		case 2:
			move = new Move(Constants.EAST);
			break;
		case 3:
			move = new Move(Constants.NORTH);
			break;
		case 4:
			move = new Move(Constants.SOUTH);
			break;
		case 5:
			direction = rand.nextInt(4);
			// if this organism will reproduce:
			// the second argument to the constructor is the direction to which
			// the offspring should be born
			// the third argument is the initial value for that organism's state
			// variable (passed to its register function)
			if (direction == 0)
				move = new Move(Constants.REPRODUCE, Constants.WEST, state);
			else if (direction == 1)
				move = new Move(Constants.REPRODUCE, Constants.EAST, state);
			else if (direction == 2)
				move = new Move(Constants.REPRODUCE, Constants.NORTH, state);
			else
				move = new Move(Constants.REPRODUCE, Constants.SOUTH, state);
		}
		return move;
	}
		
}
