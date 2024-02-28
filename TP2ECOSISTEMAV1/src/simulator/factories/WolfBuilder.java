package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Utils;
import simulator.misc.Vector2D;
import simulator.model.Animal;
import simulator.model.SelectionStrategy;
import simulator.model.Sheep;
import simulator.model.Wolf;

public class WolfBuilder extends Builder<Animal>{
	
	private Factory<SelectionStrategy> _selection_strategy_factory;
	
	public WolfBuilder(Factory<SelectionStrategy> selection_strategy_factory)
	{
		super("wolf","a"); //ver que poner en data
		_selection_strategy_factory = selection_strategy_factory;
	}
	
	public WolfBuilder(String type_tag, String desc) {
		super(type_tag, desc);
	}

	@Override
	protected Animal create_instance(JSONObject data) {
		
		JSONObject _first_select = new JSONObject();
		_first_select.put("type", "first");
		
		//valid data
		if(data == null)
			throw new IllegalArgumentException();
			
		SelectionStrategy _mate_strategy = null;
		SelectionStrategy _danger_strategy = null;
		
		if(!data.has("mate_strategy"))
		{
			_mate_strategy = _selection_strategy_factory.create_instance(_first_select); //ver como selectfirst
		}
		
		if(!data.has("danger_strategy"))
		{
			_danger_strategy = _selection_strategy_factory.create_instance(_first_select); //ver como selectfirst
		}
		
		Vector2D _pos = new Vector2D();
		
		
		if(!data.has("pos"))
		{
			//_pos = null;
			double x = Utils._rand.nextDouble(800);
			double y = Utils._rand.nextDouble(600);
			_pos = new Vector2D(x, y);
		}
		else
		{
			
			JSONArray _x_range = data.getJSONArray("x_range");
			JSONArray _y_range = data.getJSONArray("y_range");
			
			//se comrpueba q sean 2d
			if(_x_range.length()!=2 || _y_range.length()!=2 ) {
						
				throw new IllegalArgumentException();
			}
			
			double x = Utils._rand.nextDouble(_x_range.getDouble(1));
			double y = Utils._rand.nextDouble(_y_range.getDouble(1));
			
			_pos = new Vector2D(x,y);
			
			assert(_pos.getX() >= _x_range.getDouble(0) && _pos.getX() <= _x_range.getDouble(1));
			assert(_pos.getY() >= _y_range.getDouble(0) && _pos.getY() <= _y_range.getDouble(1));
		}
		
		return new Wolf(_mate_strategy, _danger_strategy, _pos);
	}

}
