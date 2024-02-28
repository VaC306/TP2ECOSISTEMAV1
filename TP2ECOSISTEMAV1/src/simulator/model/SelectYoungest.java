package simulator.model;

import java.util.List;

public class SelectYoungest implements SelectionStrategy{

	@Override
	public Animal select(Animal a, List<Animal> as) {
		int youngestAge = Integer.MAX_VALUE;
		
		for (Animal animal : as) {
            if (animal.get_age() < youngestAge) {
                youngestAge = (int) animal.get_age();
                a = animal;
            }
        }
		
		return a;
	}

}
