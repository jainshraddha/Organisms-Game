import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		final double P = 0.1;
		final double Q = 0.2;
		
		ArrayList<Player> players = new ArrayList<Player>(); 
		
		OrganismsGame organisms = new OrganismsGame(); 
		
		//Player random1 = new RandomPlayer(); 
		Player human1 = new HumanPlayer(); 
		//Player random2 = new RandomPlayer(); 
		Player human2 = new HumanPlayer(); 
		
		players.add(human1);
		//players.add(random1);
		players.add(human2);
		//players.add(random2);
		
		GameConfig game = null; 
		
		organisms.initialize(game, P, Q, players);
		
		
		
	}

}
