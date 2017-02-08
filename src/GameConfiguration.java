
public class GameConfiguration implements GameConfig {

	
	public  GameConfiguration() { 
		//think about what the strategies would be if attemping extra credit
	}
	
	
	
	@Override
	public int s() {
		final int stayingPut = 1;// TODO Auto-generated method stub
		return stayingPut;
	}

	@Override
	public int v() {
		final int moveOrReproduce = 15;// TODO Auto-generated method stub
		return moveOrReproduce;
	}

	@Override
	public int u() {
		final int energyPerUnitOfFood = 500;
		// TODO Auto-generated method stub
		return energyPerUnitOfFood;
	}

	@Override
	public int M() {
		final int maximumEnergy = 1000;
		// TODO Auto-generated method stub
		return maximumEnergy;
	}

	@Override
	public int K() {
		final int maximumFood = 50;
		// TODO Auto-generated method stub
		return maximumFood;
	}

}
 