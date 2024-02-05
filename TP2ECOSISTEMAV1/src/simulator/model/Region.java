package simulator.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Region implements Entity, FoodSupplier, RegionInfo{
	
	protected List<Animal> l;
	
	public Region()
	{
		this.l = new LinkedList<>();
	}
	
	final void add_animal(Animal a)
	{
		l.add(a);
	}
	
	final void remove_animal(Animal a)
	{
		l.remove(a);
	}
	
	final List<Animal> getAnimals()
	{
		return Collections.unmodifiableList(l);
	}
}
