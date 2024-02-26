package simulator.factories;

import org.json.JSONObject;

import simulator.model.FoodSupplier;
import simulator.model.Region;

public class DynamicSupplyRegionBuilder extends Builder<Region>{

	public DynamicSupplyRegionBuilder()
	{
		super("default", "a");
	}
	
	public DynamicSupplyRegionBuilder(String type_tag, String desc) {
		super(type_tag, desc);
	}

	@Override
	protected Region create_instance(JSONObject data) {
		return null;
	}
	
	@Override
	protected void fill_in_data(JSONObject o) {
		o.put("factor","Food increase factor (optional with default 2.0)");
		o.put("food", "Initial amount of food (optional with default 1000.0");
	}
	
}
