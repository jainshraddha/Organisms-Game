/**
 * This class implements the GameConfig Interface It holds the constant values
 * for the various parameters of the class
 * 
 * @author ShadyJ
 *
 */
public class GameConfiguration implements GameConfig {

	public GameConfiguration() {
		// think about what the strategies would be if attemping extra credit
	}

	/**
	 * The energy consumed in staying put
	 * 
	 * @return should always return 1 (other parameters scale)
	 */
	@Override
	public int s() {
		final int stayingPut = 1;// TODO Auto-generated method stub
		return stayingPut;
	}

	/**
	 * The energy consumed in moving or reproducing
	 * 
	 * @return the value of v
	 */
	@Override
	public int v() {
		final int moveOrReproduce = 15;// TODO Auto-generated method stub
		return moveOrReproduce;
	}

	/**
	 * The energy per unit of food
	 * 
	 * @return the value of v
	 */
	@Override
	public int u() {
		final int energyPerUnitOfFood = 500;
		// TODO Auto-generated method stub
		return energyPerUnitOfFood;
	}

	/**
	 * The maximum energy per organisms
	 * 
	 * @return the value of M
	 */
	@Override
	public int M() {
		final int maximumEnergy = 1000;
		// TODO Auto-generated method stub
		return maximumEnergy;
	}

	/**
	 * The maximum food units per cell
	 * 
	 * @return the value of K
	 */
	@Override
	public int K() {
		final int maximumFood = 50;
		// TODO Auto-generated method stub
		return maximumFood;
	}

}
