package simulator.factories;

import org.json.JSONObject;

import simulator.model.Wolf;

public class WolfBuilder extends Builder<Wolf>{
	
	public WolfBuilder()
	{
		super("wolf",""); //ver que poner en data
	}
	
	public WolfBuilder(String type_tag, String desc) {
		super(type_tag, desc);
	}

	@Override
	protected Wolf create_instance(JSONObject data) {
		return null;
	}

}
