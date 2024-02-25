package simulator.factories;

import org.json.JSONObject;

import simulator.model.Sheep;

public class SheepBuilder extends Builder<Sheep>{
	
	public SheepBuilder()
	{
		super("sheep",""); //ver que poner en data
	}
	
	public SheepBuilder(String type_tag, String desc) {
		super(type_tag, desc);
	}

	@Override
	protected Sheep create_instance(JSONObject data) {
		return null;
	}

}
