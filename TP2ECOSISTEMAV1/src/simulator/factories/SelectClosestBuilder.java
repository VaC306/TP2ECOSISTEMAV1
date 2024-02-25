package simulator.factories;

import org.json.JSONObject;

import simulator.model.Animal;

public class SelectClosestBuilder extends Builder<Animal>{
	
	public SelectClosestBuilder()
	{
		super("closest", "");
	}
	
	public SelectClosestBuilder(String type_tag, String desc) {
		super(type_tag, desc);
	}

	@Override
	protected Animal create_instance(JSONObject data) {
		return null;
	}

}
