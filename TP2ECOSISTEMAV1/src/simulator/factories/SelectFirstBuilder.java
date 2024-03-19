package simulator.factories;

import org.json.JSONObject;

import simulator.model.SelectFirst;
import simulator.model.SelectionStrategy;

public class SelectFirstBuilder extends Builder<SelectionStrategy>{
	
	public SelectFirstBuilder()
	{
		super("first", "a");
	}

	@Override
	protected SelectionStrategy create_instance(JSONObject data) throws IllegalArgumentException{
		return new SelectFirst();
	}

}
