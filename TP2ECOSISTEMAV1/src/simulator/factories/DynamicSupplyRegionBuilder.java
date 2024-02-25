package simulator.factories;

import org.json.JSONObject;

import simulator.model.FoodSupplier;

public class DynamicSupplyRegionBuilder extends Builder<FoodSupplier>{

	public DynamicSupplyRegionBuilder()
	{
		super("default", "");
	}
	
	public DynamicSupplyRegionBuilder(String type_tag, String desc) {
		super(type_tag, desc);
	}

	@Override
	protected FoodSupplier create_instance(JSONObject data) {
		return null;
	}

}
