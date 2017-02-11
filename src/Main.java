import java.util.ArrayList;
import java.util.Random;

/**
 * This is the main method for the game. It initializes the game, and a few
 * players. It gets the final results and displays the results
 * 
 * @author ShadyJ
 *
 */
public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {

		final double P = 0.1;
		final double Q = 0.2;

		ArrayList<Player> players = new ArrayList<Player>();

		OrganismsGame playgame = new OrganismsGame();

		// Player human1 = new HumanPlayer();
		// Player random1 = new RandomPlayer();
		// Player human2 = new HumanPlayer();

		Player computer1 = new ComputerPlayer();
		Player computer2 = new ComputerPlayer();
		Player computer3 = new ComputerPlayer();
		// players.add(human1);

		// players.add(human2);
		players.add(computer1);
		players.add(computer2);
		players.add(computer3);
		// players.add(random1);

		GameConfig game = new GameConfiguration();

		playgame.initialize(game, P, Q, players);

		ArrayList<PlayerRoundData> prd = playgame.getResults();
		System.out.println("The final result is: ");
		for (PlayerRoundData p : prd) {

			System.out.println(
					"Player ID: " + p.getPlayerId() + ", count: " + p.getCount() + ", energy:  " + p.getEnergy());

		}

	}

}
