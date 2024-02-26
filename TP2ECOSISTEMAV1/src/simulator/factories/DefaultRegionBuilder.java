package simulator.factories;

import org.json.JSONObject;

import simulator.model.DefaultRegion;
import simulator.model.Region;

public class DefaultRegionBuilder extends Builder<Region>{
	
	public DefaultRegionBuilder()
	{
		super("default", "a");
	}
	
	public DefaultRegionBuilder(String type_tag, String desc) {
		super(type_tag, desc);
	}

	@Override
	protected Region create_instance(JSONObject data) {
		return null;
	}

}
