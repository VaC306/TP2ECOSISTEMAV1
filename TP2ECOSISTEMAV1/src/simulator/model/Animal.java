package simulator.model;

import org.json.JSONArray;
import org.json.JSONObject;

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
		if (genetic_code.equals(null))
			throw new IllegalArgumentException("genetic_code no puede estar vac�o");
		if(sight_range < 0)
			throw new IllegalArgumentException("sight_range tiene que ser positivo");
		if(init_speed < 0)
			throw new IllegalArgumentException("init_speed tiene que ser positivo");
		if(mate_strategy.equals(null))
			throw new IllegalArgumentException("mate_strategy no puede estar vacio");
		//if(pos == null)
			//funcion init
		
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
	
	protected Animal(Animal p1, Animal p2){
		this._age=0.0;
		
		this._baby=null;
		this._dest=null;
		this._mate_strategy=null;
		this._region_mngr=null;
		
		this._state = State.NORMAL;
		this._desire=0.0;
		
		this._genetic_code = p1.get_genetic_code();
		this._diet=p1.get_diet();
		
		this._mate_strategy=p2.getMate_strategy();
		
		this._energy = (p1.get_energy()+p2.get_energy())/2;
		
		this._pos = p1.get_position().plus(Vector2D.get_random_vector(-1,1).scale(60.0*(Utils._rand
				.nextGaussian()+1)));
		
		this._sight_range = Utils.get_randomized_parameter((p1.get_sight_range()+p2.get_sight_range())/2, 0.2);
		
		this._speed = Utils.get_randomized_parameter((p1.get_speed()+p2.get_speed())/2, 0.2);
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
	
	public SelectionStrategy getMate_strategy() {
		return _mate_strategy;
	}
	
	public Vector2D get_destination()
	{
		return _dest;
	}
	
	public boolean is_pregnent()
	{
		return _baby == null;
	}
	
	public JSONObject as_JSON()
	{
		JSONObject data = new JSONObject();
		
		JSONArray JSONpos = new JSONArray();
		
		JSONpos.put(get_position().getX());
		JSONpos.put(get_position().getY());
		data.put("pos", JSONpos);
		
		data.put("gcode", this.get_genetic_code());
		data.put("diet", this.get_diet());
		data.put("state", this.get_state());
	
		return data;
		
	}
}
