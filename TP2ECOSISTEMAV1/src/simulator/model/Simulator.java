package simulator.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

import simulator.factories.Factory;

public class Simulator implements JSONable{
	
	protected int _cols;
	protected int _rows;
	protected int _width;
	protected int _height;
	protected double _time;
	protected RegionManager _regmanager;
	protected List<Animal> l;
	
	
	
	public Simulator(int cols, int rows, int width, int height, Factory<Animal> animals_factory, Factory<Region> regions_factory)
	{
		
		_regmanager = new RegionManager(cols, rows, width, height);
		l = new LinkedList<>();
		this._cols = cols;
		this._rows = rows;
		this._width = width;
		this._height = height;
		this._time = 0.0;
	}
	
	public double get_time()
	{
		return this._time;
	}
	
	private void add_animal(Animal a)
	{
		_regmanager.register_animal(a);
		l.add(a);
	}
	
	public MapInfo get_map_info()
	{
		return _regmanager;
	}
	
	public List<? extends Animalnfo> get_animals()
	{
		return Collections.unmodifiableList(l);
	}
	
	public void advance(double dt)
	{
		this._time = _time + dt;
		
		for(Animal a: l)
		{
			//quitar los animales muertos
			a.update(dt); //actualizar cada animal
			_regmanager.update_animal_region(a);
		}
		
		_regmanager.update_all_regions(dt);
		
		
	}
	
}
