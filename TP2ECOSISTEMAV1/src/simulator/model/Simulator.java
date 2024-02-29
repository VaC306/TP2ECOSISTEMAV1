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
	protected Factory<Animal> _animals_factory;
	protected Factory<Region> _regions_factory;
	
	
	public Simulator(int cols, int rows, int width, int height, Factory<Animal> animals_factory, Factory<Region> regions_factory)
	{
		
		_regmanager = new RegionManager(cols, rows, width, height);
		l = new LinkedList<>();
		this._cols = cols;
		this._rows = rows;
		this._width = width;
		this._height = height;
		this._time = 0.0;
		this._animals_factory = animals_factory;
		this._regions_factory = regions_factory;
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
	
	public void add_animal(JSONObject a_json)
	{
		Animal nuevo_animal = _animals_factory.create_instance(a_json);
		add_animal(nuevo_animal);
	}
	
	private void set_region(int row, int col, Region r)
	{
		_regmanager.set_region(row, col, r);
	}
	
	public void set_region(int row, int col, JSONObject r_json)
	{
		Region nueva_region = _regions_factory.create_instance(r_json);
		set_region(row, col, nueva_region);
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
		this._time += dt;
		
		for(Animal a: l)
		{
			//quitar los animales muertos
			if(a.get_state() == State.DEAD)
			{
				l.remove(a);
				_regmanager.unregister_animal(a);
			}
			
			a.update(dt); //actualizar cada animal
			_regmanager.update_animal_region(a); //actualizar la región del animal
			
			if(a.is_pregnent())
			{
				Animal _baby = a.deliver_baby();
				if(_baby != null)
					add_animal(_baby);
			}
		}
		
		_regmanager.update_all_regions(dt);
		
		
	}
	
	public JSONObject as_JSON()
	{
		JSONObject _obj = new JSONObject();
		
		_obj.put("time", _time);
		_obj.put("state", _regmanager.as_JSON());
		
		return _obj;
	}
	
}
