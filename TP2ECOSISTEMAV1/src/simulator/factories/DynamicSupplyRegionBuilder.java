package simulator.factories;

import org.json.JSONObject;

import simulator.model.DefaultRegion;
import simulator.model.DynamicSupplyRegion;
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
		
		if(data == null)
			throw new IllegalArgumentException();
		
		//valores por defecto
		double factor = 2.0;
		double food = 1000.0;
		
		if(data.has("factor"))
			factor = data.getDouble("factor");
		
		if(data.has("food"))
			factor = data.getDouble("food");
		
		return new DynamicSupplyRegion(food, factor);
	}
	
	@Override
	protected void fill_in_data(JSONObject o) {
		o.put("factor","Food increase factor (optional with default 2.0)");
		o.put("food", "Initial amount of food (optional with default 1000.0");
	}
	
}