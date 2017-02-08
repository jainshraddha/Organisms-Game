
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This is the starting point of the organisms game.
 * 
 * @author ShadyJ
 *
 */
public class OrganismsGame implements OrganismsGameInterface {

	private final int ROUNDS = 5000;
	private GameConfiguration game;
	private double p;
	private double q;
	// int countOfDoubling = 0;
	
	private ArrayList<Player> players;
	private Cell[][] grid = new Cell[10][10];
	private Random rand;
	private ArrayList<Point> boxes = new ArrayList<Point>();
	private Cell cell = null;
	private Constants cons = new ConstantsInGame();
	private ArrayList<IndividualOrganismData> allIndividuals = new ArrayList(); 

	/**
	 * This method will initialize the game. Each game will run for 5000 rounds.
	 * Each player will start with an energy of 500 at the start of the game.
	 * 
	 * @param game
	 *            the GameConfig to run
	 * @param p
	 *            the secret parameter p - probability of spontaneous appearance
	 *            of food
	 * @param q
	 *            the secret parameter q - probability of food doubling
	 * @param players
	 *            the list of players
	 */
	@Override
	public void initialize(GameConfig game, double p, double q, ArrayList<Player> players) {
dcccccccccccccf                      

		this.p = p;
		this.q = q;
		this.players = players;
		game = new GameConfiguration();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Point point = new Point(i, j);
				boxes.add(point);
				grid[i][j] = new Cell(point);
			}
		}
		// randomizing the points
		Collections.shuffle(boxes);

		placePlayersOnGrid();
		
		
		System.out.println("BEFORE THE GAME");
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (grid[i][j].isOccupied() == true) {
					System.out.print("1" + "/ " + grid[i][j].getFoodUnits() + "   ");
				} else {
					System.out.print("0" + "/ " + grid[i][j].getFoodUnits() + "   ");
				}

			}
			System.out.println();
		}
		
		
		for (int i = 0; i < 4; i++) {
			// System.out.println("STARTING NEW ROUND");
			playGame();

		}
		
		System.out.println();
		System.out.println();
		System.out.println("AFTER THE GAME");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (grid[i][j].isOccupied() == true) {
					System.out.print("1" + "/ " + grid[i][j].getFoodUnits() + "   ");
				} else {
					System.out.print("0" + "/ " + grid[i][j].getFoodUnits() + "   ");
				}

			}
			System.out.println();
		}

	}

	/**
	 * This method conducts the game, keeps the rules etc.
	 */
	@Override
	public boolean playGame() {
		Cell currentCell; 
		int index = 0;
		IndividualOrganismData individual; 
		
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				
				currentCell = grid[j][i];
				
				
				// cell = grid[i][j];
				// System.out.print(cell.getxCoordinate()+ ", " +
				// cell.getyCoordinate() + " ");
				// System.out.print("Before method, food:" + cell.getFoodUnits()
				// + " ");
				
				//check if we really need to return a Cell object from that method 
				
				
				cell = changeFoodInCell(currentCell);

				if (currentCell.isOccupied() == true) {
					System.out.println("Point on axis: " + currentCell.getPoint().getX() + ", " + currentCell.getPoint().getY());
					
					System.out.println("food on box: " + currentCell.getFoodUnits());
					
				//	System.out.println("object address: "+ currentCell.getResident());
					
					for (IndividualOrganismData in: allIndividuals) { 
						if (in.getPlayerInstance() == currentCell.getResident()) { 
							index = allIndividuals.indexOf(in);
							break;
						}
					}
					
					
					
					
				//	System.out.println(index);
					individual = allIndividuals.get(index);
					
						if (currentCell.getFoodUnits() > 0) { 
					if (individual.getEnergy() < 1000 - 500) { 			
						individual.setEnergy(1);
						currentCell.changeFood(-1);			
					}	
						}
						
						System.out.println("food on box: "+ currentCell.getFoodUnits());
						System.out.println("total energy for organism: " + individual.getEnergy());
						System.out.println("point that individual is on: " + individual.getPoint().getX() + ", " + individual.getPoint().getY());
					
					boolean food[] = isFoodPresentInNeighboringCell(currentCell); 
					
					
					
					int neighbors[] = checkNeighbors(currentCell);
					
					for (int k = 0;  k < food.length; k++) { 
						System.out.print("F: " + food[k] + " " + " N: " + neighbors[k]);
						System.out.println();
					}
					
					Move move = currentCell.getResident().move(food, neighbors, currentCell.getFoodUnits(), individual.getEnergy());
					
					int movement = move.type();
					
					if (movement < 5) {
					if (movement == Constants.WEST) { 
						cell = generateWestPoint(currentCell.getPoint().getX(), currentCell.getPoint().getY());
					} 
					else if (movement == Constants.EAST) { 
						cell = generateEastPoint(currentCell.getPoint().getX(), currentCell.getPoint().getY());
					}
					else if (movement == Constants.NORTH) { 
						cell = generateNorthPoint(currentCell.getPoint().getX(), currentCell.getPoint().getY());
					}
					else if (movement == Constants.SOUTH) { 
						cell = generateSouthPoint(currentCell.getPoint().getX(), currentCell.getPoint().getY());
					}
					System.out.println("New coordinate: " + cell.getPoint().getX() + " " + cell.getPoint().getY());
					
					if (cell.isOccupied() == false) { 
						
						cell.setOccupancy(currentCell.getResident());
						individual.setPoint(cell.getPoint());
						individual.setEnergy(-20);
						System.out.println("new total energy for organism: " + individual.getEnergy());
						System.out.println("new point that individual is on: " + individual.getPoint().getX() + ", " + individual.getPoint().getY());
						currentCell.releaseOccupancy();		
						boxes.add(currentCell.getPoint());
						boxes.remove(cell.getPoint());
						System.out.println("previous cell occupancy: "+ currentCell.isOccupied());
					}
					}
				}
				// System.out.print("After method, food:" +cell.getFoodUnits() +
				// " ");
				// System.out.println();

			}

		}
		return false;
	}

	public Cell changeFoodInCell(Cell currentCell) {

		rand = new Random();
		cell = currentCell;
		boolean occupied = cell.isOccupied();
		int foodUnits = cell.getFoodUnits();
	//	System.out.println("food: "+foodUnits);
		if (foodUnits >= 50) { 
			
			//
		}
		// if there is no food or organism on the cell, 1 unit of food appears
		// with probablity = p
		else { 
		if (occupied == false && foodUnits == 0) {
			boolean val = rand.nextInt((int) (1 / p)) == 0;
			if (val == true) {
				cell.changeFood(1);
			}
		}
		// if there is no organism on the cell, but there is food, each unit of
		// food could double with probablity = q
		else if (occupied == false && foodUnits != 0) {
			for (int k = 0; k < foodUnits; k++) {
				boolean val = rand.nextInt((int) (1 / q)) == 0;
				if (val == true) {
					// countOfDoubling++;
					cell.changeFood(1);
				}
			}
		} else {
			// check if anything is to be added here

		}
		}
		return cell;
	}

	private boolean[] isFoodPresentInNeighboringCell(Cell currentCell) {

		boolean foodPresent[] = new boolean[5];
		for (int i = 0; i < foodPresent.length; i++) {
			foodPresent[i] = false;
		}

		if (currentCell.getFoodUnits() > 0) {
			foodPresent[0] = true;
		}
		int x = currentCell.getPoint().getX();
		int y = currentCell.getPoint().getY();

		if (generateWestPoint(x, y).getFoodUnits() > 0) {
			foodPresent[1] = true;
		}

		if (generateEastPoint(x, y).getFoodUnits() > 0) {
			foodPresent[2] = true;
		}
		if (generateNorthPoint(x, y).getFoodUnits() > 0) {
			foodPresent[3] = true;
		}

		if (generateSouthPoint(x, y).getFoodUnits() > 0) {
			foodPresent[4] = true;
		}

		return foodPresent;
	}

	
	
	private int[] checkNeighbors(Cell currentCell) { 
		int neighbors[] = new int[5];
		neighbors[0] = 1; 
		
		int x = currentCell.getPoint().getX();
		int y = currentCell.getPoint().getY();
		
		if (generateWestPoint(x, y).isOccupied() == true) {
			neighbors[1] = 1;
		}

		if (generateEastPoint(x, y).isOccupied() == true) {
			neighbors[2] = 1;
		}
		if (generateNorthPoint(x, y).isOccupied() == true) {
			neighbors[3] = 1;
		}

		if (generateSouthPoint(x, y).isOccupied() == true) {
			neighbors[4] = 1;
		}
		
		return neighbors; 
	}
	
	
	private Cell generateWestPoint(int xCoordinate, int yCoordinate) {
		if (yCoordinate == 0) {
			yCoordinate = 9;
		} else {
			yCoordinate = yCoordinate - 1;
		}

		return grid[xCoordinate][yCoordinate];
	}

	private Cell generateEastPoint(int xCoordinate, int yCoordinate) {
		if (yCoordinate == 9) {
			yCoordinate = 0;
		} else {
			yCoordinate = yCoordinate + 1;
		}

		return grid[xCoordinate][yCoordinate];
	}

	private Cell generateNorthPoint(int xCoordinate, int yCoordinate) {
		if (xCoordinate == 0) {
			xCoordinate = 9;
		} else {
			xCoordinate = xCoordinate - 1;
		}

		return grid[xCoordinate][yCoordinate];
	}

	private Cell generateSouthPoint(int xCoordinate, int yCoordinate) {
		if (xCoordinate == 9) {
			xCoordinate = 0;
		} else {
			xCoordinate = xCoordinate + 1;
		}

		return grid[xCoordinate][yCoordinate];
	}

	private void placePlayersOnGrid() {

		// boolean found = false;

		if (boxes.isEmpty()) {
			// do something if all the boxes are occupied
		}
		for (Player plays : players) {
			System.out.println("player1: "+ plays.name());
			// while (found != true) {
			Point box = boxes.get(0);
			System.out.println("point selected: " + box.getX() + "," + box.getY());
			// if (grid[box.getX()][box.getY()].isOccupied() == true) {
			// boxes.remove(0);
			// continue;
			// } else {
			IndividualOrganismData individual = new IndividualOrganismData(500, plays, box);
			
			allIndividuals.add(individual);
			
			
			grid[box.getX()][box.getY()].setOccupancy(plays);
			
			boxes.remove(box);
			// found = true;
			// }
			// }
			// found = false;
		}

	}

	/**
	 * Keeys track of the results for all the players
	 * 
	 */
	@Override
	public ArrayList<PlayerRoundData> getResults() {
		// TODO Auto-generated method stub
		return null;
	}

}
