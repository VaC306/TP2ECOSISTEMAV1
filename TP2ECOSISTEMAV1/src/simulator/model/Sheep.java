package simulator.model;

import simulator.misc.Utils;
import simulator.misc.Vector2D;

public class Sheep extends Animal{
	
	
	protected Vector2D _pos;
	protected double width = 800.0;
	protected double height = 600.0;
	protected Animal _danger_source;
	protected SelectionStrategy _danger_strategy_;
	
	
	public Sheep(SelectionStrategy mate_strategy, SelectionStrategy danger_strategy, Vector2D pos)
	{
		super("SHEEP", Diet.HERBIVORE, 40.0, 35.0, mate_strategy, pos);
		this._pos = pos;
		this._danger_strategy_= danger_strategy;
	}
	
	protected Sheep(Sheep p1, Animal p2) {
		super(p1, p2);
		this._danger_source= null;
		this._danger_strategy_ = p1._danger_strategy_;
		
	}
	
	@Override
	public void update(double dt) {
		
		//si le estado es DEAD no hace nada
		if(this._state == State.DEAD) {
			
		}
		
		actualizar(dt);
		
		//mantener al SHEEP en la zona deseada
		double x = 0;
		double y = 0;
				
		while (_pos.getX() >= width) x = (_pos.getX() - width);
		while (_pos.getX() < 0) x = (_pos.getX() + width);
		while (_pos.getY() >= height) y = (_pos.getY() - height);
		while (_pos.getY() < 0) y = (_pos.getY() + height);
		this._pos = new Vector2D(x, y);
		
		
		if(_energy == 0.0 || _age > 8.0)
			_state = State.DEAD;
		if(_state != State.DEAD)
			_region_mngr.get_food(this, dt);
	}
	
	
	
	private void actualizar(double dt)
	{
		//MODO NORMAL
		if(this._state == State.NORMAL) {
			
			avanzapaso1(dt);
			//buscar un nuevo animal si danger es null
			if(this._danger_source == null) 
			{
				if(this._desire > 65.0) 
				{
					this._state = State.MATE;
				}
				else
				{
					//buscar animal que se considere peligroso
					Predicate<Animal> firstAnimalInRangeFilter = ;
					_region_mngr.get_animals_in_range(this, Diet.CARNIVORE);
				}
			}
			else
			{
				this._state = State.DANGER;
			}
		
		}
		
		
		//MODO DANGER
		if(this._state == State.DANGER) {
			//si el animal peligroso ya ha muerto por alguna otra razon
			if(this._danger_source != null && this._state ==State.DEAD) {
				this._danger_source =null;
			}
			if(this._danger_source==null) {
				avanzapaso1(dt);
			}
			
			if(this._danger_source != null) {
				this._dest =_pos.plus(_pos.minus(_danger_source.get_position()).direction());
				
				avanzandopaso2(dt);
				
				//queda por terminar
			}
			
		}
		
		//MODO MATE
		if(this._state == State.MATE) {
			//mirar lo del campo de vision, no esta contemplado aqui
			if(this._mate_target != null && this._state ==State.DEAD) {
				this._mate_target =null;
			}
			if(this._mate_target==null) {
				//buscar animal para emparejarse y su no avanza como paso1
				avanzapaso1(dt);
			}
			
			if(this._danger_source != null) {
				this._dest =_mate_target.get_position();
				
				avanzandopaso2(dt);
				//falta
				if(_mate_target._pos.minus(_pos).magnitude() < 8.0) {
					
				}
				
				//queda por terminar
			}
			
		}
	}
	
	private void avanzapaso1(double dt) {
		//elije un nuevo destino
		if(_dest.minus(_pos).magnitude() < 8.0)
		{
			double x = Utils._rand.nextDouble(800);
			double y = Utils._rand.nextDouble(600);
			this._dest = new Vector2D(x, y); 
		}
		
		//avanza
		move(_speed*dt*Math.exp((_energy-100.0)*0.007)); 
		
		// añade dt a la edad
		this._age += dt; 
		
		//quita energia
		if(_energy >= 00.0 && _energy <= 100.0)
			this._energy -= 20.0*dt;
		
		//suma deseo
		if(_desire >= 0.0 && _desire <= 100.0)
			this._desire = _desire + 40.0*dt;
		
	}
	
	private void avanzandopaso2(double dt){
		//avanza
		move(2.0*_speed*dt*Math.exp((_energy-100.0)*0.007)); 
				
		// aï¿½ade dt a la edad
		this._age = _age+dt; 
				
		//quita energia
		if(_energy >= 0.0 && _energy <= 100.0)
			this._energy = _energy - 20.0*1.2*dt;
				
		//suma deseo
		if(_desire >= 0.0 && _desire <= 100.0)
			this._desire = _desire + 40.0*dt;
				
	}
	
}
