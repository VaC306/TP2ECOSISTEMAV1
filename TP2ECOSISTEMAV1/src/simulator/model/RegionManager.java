package simulator.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.json.JSONArray;
import org.json.JSONObject;

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
		
		if ( _width % _cols != 0 || _height % _rows != 0) 
			throw new IllegalArgumentException("la anchura y el grosor no son divisibles por las columnas y filas");
		
		_region_width = _width / _cols;
		_region_height = _height / _rows;
		
		this._regions = new DefaultRegion[rows][cols];
		
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++)
				this._regions[i][j] = new DefaultRegion();
		
		this._animal_region = new HashMap <Animal, Region>();
		
	}
	
	void set_region(int row, int col, Region r)
	{
		//actualizar lista de animales de la region
		this._regions[row][col] = (DefaultRegion) r;
	}
	
	void update_animal_region(Animal a)
	{
		Region _region = _regions[(int) (a.get_position().getY() / _region_height)][(int)(a.get_position().getX() / _region_width)];
		
		if(_region.equals(_animal_region.get(a))) //revisar condicion
		{
			_animal_region.remove(a, _animal_region.get(a));
			_region.add_animal(a);
			_animal_region.put(a, _region);
		}
	}
	
	void register_animal(Animal a)
	{
		a.init(this);
		
		Region _region = _regions[(int) (a.get_position().getY() / _region_height)][(int)(a.get_position().getX() / _region_width)];
		_region.add_animal(a);
		_animal_region.put(a, _region);
	}
	
	void unregister_animal(Animal a)
	{
		Region _region = _regions[(int) a.get_position().getX()][(int) a.get_position().getY()];
		_region.remove_animal(a);
		_animal_region.remove(a, _region);
	}
	
	void update_all_regions(double dt)
	{
		for(int i = 0; i < _rows; ++i)
		{
			for(int j = 0; j < _cols; ++j)
			{
				_regions[i][j].update(dt);
			}
		}
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
	
	public JSONObject as_JSON()
	{
		JSONObject ret = new JSONObject();
		JSONArray _regiones = new JSONArray();
		JSONObject _region = new JSONObject();
		
		ret.put("regiones", _regiones);
		for(int i = 0; i < _rows; ++i)
		{
			for(int j = 0; j < _cols; ++j)
			{
				_region.put("row", i);
				_region.put("col", j);
				//_region.put("data", as_JSON()); //ver como hacerlo
				_regiones.put( _region);
			}
		}
		return ret;
	}
}
