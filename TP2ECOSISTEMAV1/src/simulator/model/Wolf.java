package simulator.model;



import simulator.misc.Utils;
import simulator.misc.Vector2D;



public class Wolf extends Animal{
	
	protected Vector2D _pos;
	protected double width = 800.0;
	protected double height = 600.0;
	protected Animal _hunt_target;
	protected SelectionStrategy _hunting_strategy;
	
	public Wolf(SelectionStrategy mate_strategy, SelectionStrategy hunting_strategy, Vector2D pos)
	{
		super("WOLF", Diet.CARNIVORE, 50.0, 60.0, mate_strategy, pos);
		_pos = pos;
		_hunting_strategy = hunting_strategy;
		_mate_strategy = mate_strategy;
	}
	
	protected Wolf(Wolf p1, Animal p2)
	{
		super(p1, p2);
		_hunting_strategy = p1._hunting_strategy;
		_hunt_target = null; //ver si es de este o p1
	}
	

	@Override

	public void update(double dt) {
		
		if(this.get_state() == State.DEAD)
		{
			
		}
		
		actualizar(dt);
			
		
		if(this.get_position().getX() > width || this.get_position().getY() > height || this.get_position().getX() < 0 || this.get_position().getY() < 0)
		{
			//mantener al WOLF en la zona deseada
			double x = 0;
			double y = 0;
		
			while (_pos.getX() >= width) x = (_pos.getX() - width);
			while (_pos.getX() < 0) x = (_pos.getX() + width);
			while (_pos.getY() >= height) y = (_pos.getY() - height);
			while (_pos.getY() < 0) y = (_pos.getY() + height);
			this._pos = new Vector2D(x, y);
		
			this._state = State.NORMAL;
		}
		
		//mantener al WOLF en la zona deseada
		double x = 0;
		double y = 0;
		
		while (_pos.getX() >= width) x = (_pos.getX() - width);
		while (_pos.getX() < 0) x = (_pos.getX() + width);
		while (_pos.getY() >= height) y = (_pos.getY() - height);
		while (_pos.getY() < 0) y = (_pos.getY() + height);
		this._pos = new Vector2D(x, y);
		
		if(this.get_energy() == 0.0 && this.get_age() > 14.0)
			this._state = State.DEAD;
		
		if(this.get_state() != State.DEAD)
		{
			if(this.get_energy() >= 0.0 && this.get_energy() < 100.0)
				this._energy += _region_mngr.get_food(this, dt);
		}
		
	}

	private void actualizar(double dt)
	{
		//MODO NORMAL
		if(this.get_state() == State.NORMAL)
		{	
			//avanzar
			if(_dest.minus(_pos).magnitude() < 8.0)
			{
				double x = Utils._rand.nextDouble(800);
				double y = Utils._rand.nextDouble(600);
				this._dest = new Vector2D(x, y); //elije un nuevo destino
			}
					
			move(this._speed*dt*Math.exp((_energy-100.0)*0.007));//avanza
							
			this._age += dt; // a�ade dt a la edad
							
			if(_energy >= 0.0 && _energy <= 100.0)
				this._energy -= 18.0*dt;
							
			if(_desire >= 0.0 && _desire <= 100.0)
				this._desire += 30.0*dt;
			
			//cambio de estado
			if(this.get_energy() < 50.0)
			{
				this._state = State.HUNGER;
			}
			else if(this.get_energy() >= 50.0 && this._desire > 65.0)
			{
				this._state = State.MATE;
			}
		}
		
		
		//MODO HUNGER
		if(this.get_state() == State.HUNGER)
		{
			if(_hunt_target == null || _hunt_target.get_state() == State.DEAD 
					|| this.get_position().minus(_hunt_target.get_position()).magnitude() > this.get_sight_range())
			{
				
			}
			
		}
	}

}

