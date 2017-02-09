import java.util.Random;

public class ComputerPlayer implements Player {
	private final int EAT = 6;
	private static final String NAME = "Computer Player";
	private int key;
	private GameConfig game;
	private int direction = Constants.STAYPUT;

	@Override
	public void register(GameConfig game, int key) {

		this.game = game;
		this.key = key;

	}

	@Override
	public String name() {

		return NAME;
	}

	@Override
	public Move move(boolean[] food, int[] neighbors, int foodleft, int energyleft) {
		Move move = null;
		int childpos = Brain(food, neighbors, foodleft, energyleft);
		// if don't have energy to move or reproduce, and there is more food in
		// the cell you eat
		System.out.println("energy left for computer: " + energyleft);
		System.out.println("food left for computer" + foodleft);
		System.out.println("direction selected by computer: " + direction);
		System.out.println("childposition selected by computer: " + childpos);

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
			move = new Move(direction, childpos, key);
			break;
		case 6:
			move = new Move(EAT);
			break;
		}
		return move;
	}

	private int Brain(boolean[] food, int[] neighbors, int foodleft, int energyleft) {

		int childpos = Constants.STAYPUT;
		System.out.println("direction of childpos" + childpos);
		System.out.println("Maximum allowed energy: " + game.M());

		if (energyleft < game.v() && foodleft > 0) {
			direction = EAT;
		}
		// if energy left is less than half of max, and food is left
		// eat
		else if (energyleft < game.M() / 2 && foodleft > 0) {
			System.out.println("if energy left is less than half of max, and food is left eat");
			direction = EAT;
		}
		// if energy left is less than half, there is no food on the cell
		// and the energy left is more than that for moving
		// move to a cell with food, or select any any cell to move to

		else if (energyleft <= game.M() / 2 && energyleft > game.v() && foodleft == 0) {

			for (int i = 1; i < food.length; i++) {
				System.out.println("direction: " + i + "food" + food[i]);
				if (food[i] == true) {
					if (neighbors[i] == 0)
						direction = i;
					break;
				} else {

					for (int j = 1; j < neighbors.length; j++) {
						if (neighbors[j] == 0) {
							direction = j;
							break;
						}
						direction = Constants.STAYPUT;
					}
				}
			}
		}

		// if energy left is more than half, and there is no food on the cell
		// and the energy is more than required to reproduce
		// reproduce on a cell that has food.
		// else reproduce on any cell that is not occupied
		// if all cells are occupied, stay put.
		else if (energyleft > game.M() / 2 && foodleft == 0 && energyleft > game.v()) {

			for (int i = 1; i < neighbors.length; i++) {
				if (neighbors[i] == 0) {
					if (food[i] == true) {
						direction = Constants.REPRODUCE;
						childpos = i;
						break;
					}
					childpos = i;
				} else {
					direction = Constants.STAYPUT;
				}
			}

		} else {
			direction = Constants.STAYPUT;
			childpos = Constants.STAYPUT;
		}
		return childpos;

	}

}
