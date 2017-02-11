import java.util.*;

/**
 * This is the class that represents a human player in the game of Organisms It
 * implements the Player class
 * 
 * @author ShadyJ
 *
 */
public class HumanPlayer implements Player {
	private static final String NAME = "Human Player";
	private Random rand;
	private GameConfig game;
	private int key;

	/**
	 * This method is called when the Organism is created.
	 * 
	 * @param key
	 *            - The key is the value that is passed to this organism by its
	 *            parent.
	 * @param game
	 *            - The configurations of the game that is passed to the newly
	 *            created organism
	 */
	@Override
	public void register(GameConfig game, int key) {
		rand = new Random();
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

	/**
	 * This method is called to ask the human player to make a move for the
	 * round.
	 */
	@Override
	public Move move(boolean[] food, int[] neighbors, int foodleft, int energyleft) {

		Move move = null;
		int direction;
		Scanner in = new Scanner(System.in);
		try {
			direction = in.nextInt();
			System.out.println("you chose: " + direction);
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
				direction = in.nextInt();
				// if this organism will reproduce:
				// the second argument to the constructor is the direction to
				// which
				// the offspring should be born
				// the third argument is the initial value for that organism's
				// state
				// variable (passed to its register function)
				if (direction == 1)
					move = new Move(Constants.REPRODUCE, Constants.WEST, key);
				else if (direction == 2)
					move = new Move(Constants.REPRODUCE, Constants.EAST, key);
				else if (direction == 3)
					move = new Move(Constants.REPRODUCE, Constants.NORTH, key);
				else if (direction == 4)
					move = new Move(Constants.REPRODUCE, Constants.SOUTH, key);
				else
					move = new Move(Constants.STAYPUT);
			default: 
					move = new Move((Constants.STAYPUT));
					
			}
		} catch (InputMismatchException ime) {
			move = new Move(Constants.STAYPUT);
			System.out.println("Your input was invalid. You lose this turn.");
		}

		return move;
	}

}
