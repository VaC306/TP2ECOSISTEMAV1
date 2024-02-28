package simulator.model;

import java.util.Random;

public class DynamicSupplyRegion extends Region implements FoodSupplier{
	
	protected double _food;
	protected double _factor;
	
	public DynamicSupplyRegion(double food, double factor)
	{
		if(food < 0 || factor < 0)
			throw new IllegalArgumentException();
		
		
		_food = food;
		_factor = factor;
	}

	@Override
	public double get_food(Animal a, double dt) {
		
		int n=0;
		for(Animal b: getAnimals()) {
			if(b.get_diet()== Diet.HERBIVORE) {
				n++;
			}
		}
		
		
		if(a.get_diet() == Diet.CARNIVORE)
			return 0.0;
		else
		{
			return Math.min(_food,60.0*Math.exp(-Math.max(0,n-5.0)*2.0)*dt);
			
		}
	}

	@Override
	public void update(double dt) {
		Random random = new Random();
		
		double probabilidad = random.nextDouble();
		
		if(probabilidad < 0.5)
			_food += dt*_factor;
		
	}
	
}
