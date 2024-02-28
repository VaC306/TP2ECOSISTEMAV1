package simulator.factories;

import org.json.JSONObject;

import simulator.model.Animal;
import simulator.model.SelectFirst;
import simulator.model.SelectionStrategy;

public class SelectFirstBuilder extends Builder<SelectionStrategy>{
	
	public SelectFirstBuilder()
	{
		super("first", "a");
	}
	
	public SelectFirstBuilder(String type_tag, String desc) {
		super(type_tag, desc);
	}

	@Override
	protected SelectionStrategy create_instance(JSONObject data) throws IllegalArgumentException{
		
		return new SelectFirst();
	}

}
