package simulator.factories;

import org.json.JSONObject;

import simulator.model.Animal;

public class SelectYoungestBuilder extends Builder<Animal>{
	
	public SelectYoungestBuilder()
	{
		super("youngest", "");
	}
	
	public SelectYoungestBuilder(String type_tag, String desc) {
		super(type_tag, desc);
	}

	@Override
	protected Animal create_instance(JSONObject data) {
		return null;
	}

}
