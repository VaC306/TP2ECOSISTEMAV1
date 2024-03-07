package simulator.model;



import java.util.function.Predicate;

import simulator.misc.Utils;
import simulator.misc.Vector2D;



public class Wolf extends Animal{
	
	protected double width = 800.0;
	protected double height = 600.0;
	protected Animal _hunt_target;
	protected SelectionStrategy _hunting_strategy;
	
	public Wolf(SelectionStrategy mate_strategy, SelectionStrategy hunting_strategy, Vector2D pos)
	{
		super("WOLF", Diet.CARNIVORE, 50.0, 60.0, mate_strategy, pos);
		this._pos = pos;
		this._hunting_strategy = hunting_strategy;
		this._mate_strategy = mate_strategy;
	}
	
	protected Wolf(Wolf p1, Animal p2)
	{
		super(p1, p2);
		_hunting_strategy = p1._hunting_strategy;
		p1._hunt_target = null; 
	}
	

	@Override

	public void update(double dt) {
		
		if(this.get_state() == State.DEAD)
		{
			
		}
		else
		{
			actualizar(dt);
			
			
			if(this.get_position().getX() > width || this.get_position().getY() > height || this.get_position().getX() < 0 || this.get_position().getY() < 0)
			{
				double x = 0;
				double y = 0;
			
				while (x >= width) x = (x - width);
				while (x < 0) x = (x + width);
				while (y >= height) y = (y - height);
				while (y < 0) y = (y + height);
				this._pos = new Vector2D(x, y);
			
				this._state = State.NORMAL;
			}
			
			if(this.get_energy() == 0.0 || this.get_age() > 14.0)
				this._state = State.DEAD;
			
			if(this.get_state() != State.DEAD)
			{
				if(this.get_energy() >= 0.0 && this.get_energy() < 100.0)
					this._energy += _region_mngr.get_food(this, dt);
			}
		}
	}

	private void actualizar(double dt)
	{
		//MODO NORMAL
		if(this.get_state() == State.NORMAL)
		{	
			//avanzar
			if(_pos.distanceTo(_dest) < 8.0)
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
				//buscar animal que se considere peligroso
				Predicate<Animal> _cazar = (a)-> a.get_diet()== Diet.HERBIVORE;
				
				_region_mngr.get_animals_in_range(this, _cazar);
				
				//mantenemos una referencia del animal peligroso
				_hunt_target = _hunting_strategy.select(_hunt_target, _region_mngr.get_animals_in_range(this, _cazar));
			}
			if(_hunt_target == null)
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
			}
			else if(_hunt_target != null)
			{
				this._dest = _hunt_target.get_position();
				move(3.0*_speed*dt*Math.exp((_energy-100.0)*0.007));
				this._age += dt;
				
				if(_energy >= 0.0 && _energy <= 100.0)
					this._energy -= 18.0*1.2*dt;
				
				if(_desire >= 0.0 && _desire <= 100.0)
					this._desire += 30.0*dt;
				
				if(_hunt_target.get_position().minus(_pos).magnitude() < 8.0)
				{
					_hunt_target._state = State.DEAD;
					_hunt_target = null;
					if(_energy >= 0.0 && _energy <= 100.0)
						this._energy += 50.0*dt;
				}
			}
			//cambio de estado
			if(this.get_energy() > 50.0)
			{
				if(this._desire < 65.0)
					this._state = State.NORMAL;
				else
					this._state = State.MATE;
			}
			
		}
		
		
		//MODO MATE
		if(this._state == State.MATE)
		{
			if(_mate_target != null)
			{
				if(_mate_target._state == State.DEAD || _mate_target.get_position().minus(_pos).magnitude() > this.get_sight_range())
				{
					_mate_target = null;
				}
			}
			else if(_mate_target == null)
			{
				//buscar animal que se considere peligroso
				Predicate<Animal> MateAnimals = (a)-> a.get_diet()== Diet.CARNIVORE;
				
				_region_mngr.get_animals_in_range(this, MateAnimals);
				
				//mantenemos una referencia del animal mate
				_mate_target =_mate_strategy.select(this, _region_mngr.get_animals_in_range(this, MateAnimals)); 
				
			}
		}
		
		//cambio de estado
		if(this.get_energy() < 50.0)
		{
			this._state = State.HUNGER;
		}
		else
		{
			if(this._desire < 65.0)
				this._state = State.NORMAL;
		}
	}

}

