import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameHelper {

	
	private Cell[][] grid;
	private ArrayList<Point> boxes;
	private HashMap <Player,IndividualOrganismData> individualData;
	ArrayList<Player> players;
	private HashMap<Player, Integer> keyMap;
	private int height; 
	private int width; 
	
	public GameHelper(ArrayList<Player> players, ArrayList<Point> boxes, HashMap <Player,IndividualOrganismData> individualData, Cell[][] grid, HashMap<Player, Integer> keyMap) { 
		
		this.grid = grid; 
		this.players = players; 
		this.boxes = boxes; 
		this.individualData = individualData;
		this.keyMap = keyMap;
	}
	
	
	public int placePlayersOnGrid() {
		final int STARTENERGY = 500;
		int retValue = 0; 
		// boolean found = false;

		if (boxes.isEmpty()) {
			// do something if all the boxes are occupied
			retValue = -1;
		}
		else {
		for (Player plays : players) {
			System.out.println("player1: "+ plays.name());
		
			Point box = boxes.get(0);
			System.out.println("point selected: " + box.getX() + "," + box.getY());
		
			IndividualOrganismData individual = new IndividualOrganismData(STARTENERGY, plays, box, keyMap.get(plays));
			
			//allIndividuals.add(individual);
			individualData.put(plays, individual);
			
			grid[box.getX()][box.getY()].setOccupancy(plays);
			
			boxes.remove(box);
		
		}
		}	
		return retValue;
	}
	
	public void generateGrid(int width, int height) { 
		this.height = height; 
		this.width = width;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Point point = new Point(i, j);
				boxes.add(point);
				grid[i][j] = new Cell(point);
			}
		}
	
	}
	
	public Cell changeFoodInCell(Cell currentCell, double p, double q) {

		Random rand = new Random();
		Cell cell = currentCell;
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
	
	public boolean[] isFoodPresentInNeighboringCell(Cell currentCell) {

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
	

	public int[] checkNeighbors(Cell currentCell) { 
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
	
	
	public Cell checkDirection(int movement, Cell currentCell) { 
		Cell newcell = null; 
		if (movement == Constants.WEST) { 
			newcell = generateWestPoint(currentCell.getPoint().getX(), currentCell.getPoint().getY());
		} 
		else if (movement == Constants.EAST) { 
			newcell = generateEastPoint(currentCell.getPoint().getX(), currentCell.getPoint().getY());
		}
		else if (movement == Constants.NORTH) { 
			newcell = generateNorthPoint(currentCell.getPoint().getX(), currentCell.getPoint().getY());
		}
		else if (movement == Constants.SOUTH) { 
			newcell = generateSouthPoint(currentCell.getPoint().getX(), currentCell.getPoint().getY());
		} else if (movement == Constants.STAYPUT){
			newcell = currentCell;
		}
		
		return newcell; 
	}
	

	private Cell generateWestPoint(int xCoordinate, int yCoordinate) {
		if (yCoordinate == 0) {
			yCoordinate = height - 1;
		} else {
			yCoordinate = yCoordinate - 1;
		}

		return grid[xCoordinate][yCoordinate];
	}

	private Cell generateEastPoint(int xCoordinate, int yCoordinate) {
		if (yCoordinate == height-1) {
			yCoordinate = 0;
		} else {
			yCoordinate = yCoordinate + 1;
		}

		return grid[xCoordinate][yCoordinate];
	}

	private Cell generateNorthPoint(int xCoordinate, int yCoordinate) {
		if (xCoordinate == 0) {
			xCoordinate = width - 1;
		} else {
			xCoordinate = xCoordinate - 1;
		}

		return grid[xCoordinate][yCoordinate];
	}

	private Cell generateSouthPoint(int xCoordinate, int yCoordinate) {
		if (xCoordinate == width - 1) {
			xCoordinate = 0;
		} else {
			xCoordinate = xCoordinate + 1;
		}

		return grid[xCoordinate][yCoordinate];
	}

	


	
}
