package simulator.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
	
	public JSONObject as_JSON()
	{
		JSONObject animals = new JSONObject();
		JSONArray list = new JSONArray();
		
		for(Animal a : getAnimals())
		{
			list.put(a);
		}
		
		animals.put("animals", list);
		return animals;
	}
}
