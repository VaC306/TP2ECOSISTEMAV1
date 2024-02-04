package simulator.model;

import simulator.misc.Utils;
import simulator.misc.Vector2D;

public class Sheep extends Animal{
	
	protected Vector2D _pos;
	protected double width = 800.0;
	protected double height = 600.0;
	
	public Sheep(SelectionStrategy mate_strategy, SelectionStrategy danger_strategy, Vector2D pos)
	{
		super("SHEEP", Diet.HERBIVORE, 40.0, 35.0, mate_strategy, pos);
		_pos = pos;
	}

	@Override
	public void update(double dt) {
		
		//MODO NORMAL
		if(_dest.minus(_pos).magnitude() < 8.0)
		{
			double x = Utils._rand.nextDouble(800);
			double y = Utils._rand.nextDouble(600);
			this._dest = new Vector2D(x, y); //elije un nuevo destino
		}
		
		move(_speed*dt*Math.exp((_energy-100.0)*0.007)); //avanza
		
		this._age = _age+dt; // añade dt a la edad
		
		if(_energy >= 0.0 && _energy <= 100.0)
			this._energy = _energy - 20.0*dt;
		
		if(_desire >= 0.0 && _desire <= 100.0)
			this._desire = _desire + 40.0*dt;
		
		
		//mantener al SHEEP en la zona deseada
		double x = 0;
		double y = 0;
				
		while (_pos.getX() >= width) x = (_pos.getX() - width);
		while (_pos.getX() < 0) x = (_pos.getX() + width);
		while (_pos.getY() >= height) y = (_pos.getY() - height);
		while (_pos.getY() < 0) y = (_pos.getY() + height);
		this._pos = new Vector2D(x, y);
	}
	
}
