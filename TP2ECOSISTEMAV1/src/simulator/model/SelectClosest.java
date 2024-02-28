package simulator.model;

import java.util.List;

public class SelectClosest implements SelectionStrategy{

	@Override
	public Animal select(Animal a, List<Animal> as) {
		int ind = 0;
		for(Animal b: as)
		{
			//if(b.get_position())
		}
		return as.get(ind);
	}

}
