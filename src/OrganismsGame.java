
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
	private Random rand;
	
	private Cell[][] grid;
	private ArrayList<Point> boxes;
	private Cell cell;
	//private Constants cons = new ConstantsInGame();
	//private ArrayList<IndividualOrganismData> allIndividuals = new ArrayList(); 
	private HashMap <Player,IndividualOrganismData> individualData;
	private HashMap<Player, Integer> keyMap;
	private GameHelper helper;
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
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Override
	public void initialize(GameConfig game, double p, double q, ArrayList<Player> players) throws InstantiationException, IllegalAccessException {
        int width = 10; 
        int height = 10; 
        cell = null;
		grid = new Cell[width][height];
		boxes = new ArrayList<Point>();
		individualData = new HashMap<>();
		keyMap = new HashMap<>();
			
		helper =  new GameHelper(players, boxes, individualData, grid, keyMap); 
		
		this.p = p;
		this.q = q;
		this.game = (GameConfiguration) game;
		
		
		//create keys for the players, keep a track of those keys
		int key = 1; 
		for (Player pl: players) { 		
			pl.register(game, key);
			keyMap.put(pl,key);
			key++;
		}

		//generate the cells 
		helper.generateGrid(width, height);
		// randomizing the points
		Collections.shuffle(boxes);

		int ret = helper.placePlayersOnGrid();
		if (ret == -1 ){ 
			System.out.println("Too many players, no available spots");
		}
		else {
		
		for (int i = 0; i < 5; i++) {
			// System.out.println("STARTING NEW ROUND");
			playGame();
			
			//reset their alreadymoved boolean for all organisms
			for (Player pl: individualData.keySet()) {
				individualData.get(pl).setAlreadyMovedInRound(false);
				
			}
		
				System.out.println("AFTER ROUND: " + i);
				for (int j = 0; j < 10; j++) { 
					for (int k = 0;k < 10; k++) { 
						if (grid[j][k].isOccupied() == true) {
							System.out.print("1" + "/ " + grid[j][k].getFoodUnits() + "   ");
						} else {
							System.out.print("0" + "/ " + grid[j][k].getFoodUnits() + "   ");
						}
						
					}
					System.out.println();
				}
		
		}
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
				cell = helper.changeFoodInCell(currentCell, p, q);

				if (currentCell.isOccupied() == true) {
					
					individual = individualData.get(currentCell.getResident());	
			
					//delete the organism if energy is 0 
					if (individual.getEnergy() == 0) { 
						
						cell.releaseOccupancy();
						individualData.remove(individual);
					}
					
					else { 
					
					if (individual.hasAlreadyMovedInRound() == true) {
						//skip this, as it has already moved for the round
						continue;
						
					} 
					//if the food units in the current cell are more than 0
					//if the organism on the cell can eat one full unit of food without exceeding the limit, make it eat 1 unit 
					if (currentCell.getFoodUnits() > 0) { 
					if (individual.getEnergy() < game.M() - game.u()) { 			
						individual.setEnergy(game.u());
						currentCell.changeFood(-1);			
					}	
						}
						
						//System.out.println("food on box: "+ currentCell.getFoodUnits());
						//System.out.println("total energy for organism: " + individual.getEnergy());
						//System.out.println("point that individual is on before move: " + individual.getPoint().getX() + ", " + individual.getPoint().getY());
					
					boolean food[] = helper.isFoodPresentInNeighboringCell(currentCell); 
					int neighbors[] = helper.checkNeighbors(currentCell);
					
					Move move = currentCell.getResident().move(food, neighbors, currentCell.getFoodUnits(), individual.getEnergy()); //get the organism to move
					individual.setAlreadyMovedInRound(true); //check so it doesn't move in the same round again 
					
					int movement = move.type();
					
				//	System.out.println("direction chosen: " + movement);
					
					if (movement == 0) { 
						
						individual.setEnergy(-game.s());						
					}
					
					
					//instructions if movement is not about reproducing, or staying put
					if (movement < 5 && movement != 0) {
					cell = helper.checkDirection(movement, currentCell); 
			//		System.out.println("New coordinate: " + cell.getPoint().getX() + " " + cell.getPoint().getY());
					//check if move is legal  - cell is free, and if energy required is available 
					if (cell.isOccupied() == false && individual.getEnergy() >= game.v()) { 
			
						cell.setOccupancy(currentCell.getResident());  //set occupancy in new cell
						individual.setPoint(cell.getPoint()); //set point for the organism 
						individual.setEnergy(-game.v()); //adjust energy for the organism 
						currentCell.releaseOccupancy();	//release energy from the previous cell 
						boxes.add(currentCell.getPoint()); //keeps track of all available boxes 
						boxes.remove(cell.getPoint()); 
					} else { 
						//if move is illegal, force it to stay put 
						individual.setEnergy(-game.s());						
					}
					} 
					//if movement is to reproduce: 
					if (movement == 5) { 
					
						movement = move.childpos(); //check the direction to reproduce in 
						cell = helper.checkDirection(movement, currentCell); //get the cell to move in 
						if (cell.isOccupied() == false && individual.getEnergy() > game.v()) { //check if move is legal 
						Player newPlayer;
						try {
							
							newPlayer = individual.getPlayerInstance().getClass().newInstance();
							newPlayer.register(game, individual.getKey());
							individual.setEnergy(-game.v()); //energy required to reproduce
							int newPlayerEnergy = individual.getEnergy()/2; //remaining energy split by 2 - possible loss of energy in division (int)
							individual.setEnergy(newPlayerEnergy);
							IndividualOrganismData newIndividual = new IndividualOrganismData(newPlayerEnergy, newPlayer, cell.getPoint(), individual.getKey());
							individualData.put(newPlayer, newIndividual);
							cell.setOccupancy(newPlayer);
							boxes.remove(cell);
							newIndividual.setAlreadyMovedInRound(true);
							
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						} else { 
							individual.setEnergy(-game.s()); //stay put. 
							
						}
					}
					
					if (movement == 6) { 
						if (currentCell.getFoodUnits() > 0) { 
							if (individual.getEnergy() < game.M() - game.u()) { 			
								individual.setEnergy(game.u());
								currentCell.changeFood(-1);			
							}	
								}
					}
				}

			}
			}

		}
		return false;
	}

	

	

	
	
	

	
	
	
	/**
	 * Keeps track of the results for all the players
	 * 
	 */
	@Override
	public ArrayList<PlayerRoundData> getResults() {
		
		ArrayList<PlayerRoundData> results = new ArrayList<>();
		
	
		for (Player pl: keyMap.keySet()) { 
			int key = keyMap.get(pl);
			int totalEnergy = 0; 
			int totalCount = 0; 
			
		for (Player play: individualData.keySet()) { 
			
			int key2 = individualData.get(play).getKey(); 
			
			if (key2 == key) { 
				totalEnergy += individualData.get(play).getEnergy();
				totalCount += 1; 
			}
		}
			PlayerData pd = new PlayerData(key, totalEnergy, totalCount);
			results.add(pd);
		}
		
		
		return results;
		
		// TODO Auto-generated method stu
	}

}
