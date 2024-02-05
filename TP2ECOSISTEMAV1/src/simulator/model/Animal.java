package simulator.model;

import simulator.misc.Utils;
import simulator.misc.Vector2D;

public abstract class Animal implements Animalnfo, Entity{
	
	protected String  _genetic_code;
	protected Diet _diet;
	protected double _sight_range;
	protected Vector2D _pos;
	protected SelectionStrategy _mate_strategy;
	protected double _speed;
	protected State _state;
	protected double _energy;
	protected Vector2D _dest;
	protected double _age;
	protected double _desire;
	protected Animal _mate_target;
	protected Animal _baby;
	protected AnimalMapView _region_mngr;
	
	protected Animal(String genetic_code, Diet diet, double sight_range, double init_speed, SelectionStrategy mate_strategy, Vector2D pos)
	{
		if (genetic_code == null)
			throw new IllegalArgumentException("genetic_code no puede estar vacío");
		if(sight_range < 0)
			throw new IllegalArgumentException("sight_range tiene que ser positivo");
		if(init_speed < 0)
			throw new IllegalArgumentException("init_speed tiene que ser positivo");
		if(mate_strategy == null)
			//throw new IllegalArgumentException("mate_strategy no puede estar vacío");
		//if(pos == null)
			//ver que
		
		this._genetic_code = genetic_code;
		this._diet = diet;
		this._sight_range = sight_range;
		this._pos = pos;
		this._mate_strategy = mate_strategy;
		this._speed = Utils.get_randomized_parameter(init_speed, 0.1);
		this._state = State.NORMAL;
		this._energy = 100.0;
		this._age = 0.0;
		this._desire = 0.0;
		this._mate_target = null;
		this._baby = null;
		this._region_mngr = null;
		double x = Utils._rand.nextDouble(800);
		double y = Utils._rand.nextDouble(600);
		this._dest = new Vector2D(x, y);

	}
	
	protected void move(double speed)
	{
		_pos = _pos.plus(_dest.minus(_pos).direction().scale(speed));
	}
	
	public State get_state()
	{
		return this._state;
	}
	
	public Vector2D get_position()
	{
		return this._pos;
	}
	
	public String get_genetic_code()
	{
		return this._genetic_code;
	}
	
	public Diet get_diet()
	{
		return _diet;
	}
	
	
	public double get_speed()
	{
		return _speed;
	}
	
	public double get_sight_range()
	{
		return _sight_range;
	}
	
	public double get_energy()
	{
		return _energy;
	}
	
	public double get_age()
	{
		return _age;
	}
	
	public Vector2D get_destination()
	{
		return _dest;
	}
	
	public boolean is_pregnent()
	{
		return false; //ver que poner
	}
}
