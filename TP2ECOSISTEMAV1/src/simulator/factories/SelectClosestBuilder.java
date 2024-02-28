package simulator.factories;

import org.json.JSONObject;

import simulator.model.Animal;
import simulator.model.SelectClosest;
import simulator.model.SelectionStrategy;

public class SelectClosestBuilder extends Builder<SelectionStrategy>{
	
	public SelectClosestBuilder()
	{
		super("closest", "a");
	}
	
	public SelectClosestBuilder(String type_tag, String desc) {
		super(type_tag, desc);
	}

	@Override
	protected SelectionStrategy create_instance(JSONObject data) {
		return new SelectClosest();
	}

}
