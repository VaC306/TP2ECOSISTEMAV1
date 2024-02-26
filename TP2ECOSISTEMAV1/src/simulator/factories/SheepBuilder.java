package simulator.factories;

import org.json.JSONObject;

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
		
		
		//return new Sheep();
		return null;
	}

}
