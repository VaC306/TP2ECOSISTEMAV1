package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Animal;
import simulator.model.SelectionStrategy;
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
	protected Animal create_instance(JSONObject data) throws IllegalArgumentException{
		
		JSONArray arrayx =data.getJSONArray("x_range");
		JSONArray arrayy =data.getJSONArray("y_range");
		
		Vector2D _pos = new Vector2D();
		//return new Wolf(, _pos);
		return null;
	}

}
