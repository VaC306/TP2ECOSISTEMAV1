package simulator.model;

import java.util.List;

public class SelectClosest implements SelectionStrategy{

	@Override
	public Animal select(Animal a, List<Animal> as) {
		Animal _animal_cercano = null;
		double _minima_distancia = Double.MAX_VALUE;
		
		if(as == null)
			return null;
		
		for(Animal b: as)
		{
			if(a.get_position().distanceTo(b.get_position()) < _minima_distancia)
				_animal_cercano = b;
		}
		return _animal_cercano;
	}

}
