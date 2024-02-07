package simulator.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class RegionManager implements AnimalMapView{
	
	protected int _cols;
	protected int _rows;
	protected int _width;
	protected int _height;
	protected int _region_height;
	protected int _region_width;
	protected DefaultRegion[][] _regions;
	protected Map<Animal, Region> _animal_region;
	
	public RegionManager(int cols, int rows, int width, int height)
	{
		this._cols = cols;
		this._rows = rows;
		this._width = width;
		this._height = height;
		this._region_height = height / rows;
		this._region_width = width / cols;
		this._regions = new DefaultRegion[rows][cols];
		this._animal_region = new HashMap <Animal, Region>();
		
	}
	
	void register_animal(Animal a)
	{
		Region _region = _regions[(int) a.get_position().getX()][(int) a.get_position().getY()];
		_region.add_animal(a);
		_animal_region.put(a, _region);
	}
	
	void unregister_animal(Animal a)
	{
		Region _region = _regions[(int) a.get_position().getX()][(int) a.get_position().getY()];
		_region.remove_animal(a);
		_animal_region.remove(a, _region);
	}
	
	public List<Animal> get_animals_in_range(Animal a, Predicate<Animal> filter)
	{
		List<Animal> _animals_in_range = new LinkedList<>();
		Region _region = _regions[(int) a.get_position().getX()][(int) a.get_position().getY()];
		for(Animal b : _region.getAnimals())
		{
			if(b.get_position().distanceTo(a.get_position()) <= a.get_sight_range())
				_animals_in_range.add(b);
		}
		return _animals_in_range;	
	}

	@Override
	public int get_cols() {
		return _cols;
	}

	@Override
	public int get_rows() {
		return _rows;
	}

	@Override
	public int get_width() {
		return _width;
	}

	@Override
	public int get_height() {
		return _height;
	}

	@Override
	public int get_region_width() {
		return _region_width;
	}

	@Override
	public int get_region_height() {
		return _region_height;
	}

	@Override
	public double get_food(Animal a, double dt) {
		Region _region = _regions[(int) a.get_position().getX()][(int) a.get_position().getY()];
		_region.get_food(a, dt);
		return 0;
	}
}
