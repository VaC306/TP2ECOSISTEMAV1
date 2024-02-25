package simulator.factories;

import org.json.JSONObject;

import simulator.model.Animal;

public class SelectFirstBuilder extends Builder<Animal>{
	
	public SelectFirstBuilder()
	{
		super("first", "");
	}
	
	public SelectFirstBuilder(String type_tag, String desc) {
		super(type_tag, desc);
	}

	@Override
	protected Animal create_instance(JSONObject data) {
		
		return null;
	}

}
