package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Animal;
import simulator.model.SelectionStrategy;
import simulator.model.Sheep;

public class SheepBuilder extends Builder<Animal>{
	
	private Factory<SelectionStrategy> _selection_strategy_factory;
	
	public SheepBuilder(Factory<SelectionStrategy> selection_strategy_factory)
	{
		super("sheep","a"); //ver que poner en data
		_selection_strategy_factory = selection_strategy_factory;
	}
	
	public SheepBuilder(String type_tag, String desc) {
		super(type_tag, desc);
	}

	@Override
	protected Animal create_instance(JSONObject data) {
		
		//valid data
		if(data == null)
			throw new IllegalArgumentException();
			
		SelectionStrategy _select_strat;
		
		if(!data.has("mate_strategy"))
		{
			_select_strat = _selection_strategy_factory.create_instance(data); //ver como selectfirst
		}
		
		if(!data.has("danger_strategy"))
		{
			_select_strat = _selection_strategy_factory.create_instance(data); //ver como selectfirst
		}
		
		Vector2D _pos = new Vector2D();
		JSONArray _x_range = data.getJSONArray("x_range");
		JSONArray _y_range = data.getJSONArray("y_range");
		
		//se comrpueba q sean 2d
		if(_x_range.length()!=2 || _y_range.length()!=2 ) {
					
			throw new IllegalArgumentException();
		}
		
		if(!data.has("pos"))
		{
			_pos = null;
		}
		else
		{
			assert(_pos.getX() >= _x_range.getDouble(0) && _pos.getX() <= _x_range.getDouble(1));
			assert(_pos.getY() >= _y_range.getDouble(0) && _pos.getY() <= _y_range.getDouble(1));
		}
		
		return null;
	}

}
