package simulator.factories;

import org.json.JSONObject;

import simulator.model.Animal;
import simulator.model.SelectYoungest;
import simulator.model.SelectionStrategy;

public class SelectYoungestBuilder extends Builder<SelectionStrategy>{
	
	public SelectYoungestBuilder()
	{
		super("youngest", "a");
	}
	
	public SelectYoungestBuilder(String type_tag, String desc) {
		super(type_tag, desc);
	}

	@Override
	protected SelectionStrategy create_instance(JSONObject data) {
		return new SelectYoungest();
	}

}
