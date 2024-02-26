package simulator.control;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Animal;
import simulator.model.Animalnfo;
import simulator.model.Entity;
import simulator.model.Simulator;
import simulator.view.SimpleObjectViewer;
import simulator.view.SimpleObjectViewer.ObjInfo;

public class Controller {
	
	protected Simulator _sim;
	protected double _time;
	
	public Controller(Simulator sim)
	{
		_sim = sim;
	}
	
	private static List<ObjInfo> to_animals_info(List<? extends Animalnfo> animals) {
		List<ObjInfo> ol = new ArrayList<>(animals.size());
		for (Animalnfo a : animals)
		ol.add(new ObjInfo(a.get_genetic_code(),
		(int) a.get_position().getX(),
		(int) a.get_position().getY(),8));
		return ol;
		}
	
	 	public void load_data(JSONObject data) {
	        if (data.has("regions")) {
	            JSONArray regiones = data.getJSONArray("regions");
	            for (int i = 0; i < regiones.length(); i++) {
	                JSONObject region = regiones.getJSONObject(i);
	                JSONArray rowRange = region.getJSONArray("row");
	                JSONArray colRange = region.getJSONArray("col");
	                JSONObject spec = region.getJSONObject("spec");

	                int rf = rowRange.getInt(0);
	                int rt = rowRange.getInt(1);
	                int cf = colRange.getInt(0);
	                int ct = colRange.getInt(1);

	                for (int R = rf; R <= rt; R++) {
	                    for (int C = cf; C <= ct; C++) {
	                        //_sim.set_region(R, C, spec);
	                    }
	                }
	            }
	        }

	        if (data.has("animals")) {
	            JSONArray animales = data.getJSONArray("animals");
	            for (int i = 0; i < animales.length(); i++) {
	                JSONObject animal = animales.getJSONObject(i);
	                int cantidad = animal.getInt("amount");
	                JSONObject spec = animal.getJSONObject("spec");

	                for (int j = 0; j < cantidad; j++) {
	                    //_sim.add_animal(spec);
	                }
	            }
	        }
	 	}
	
	public void run(double t, double dt, boolean sv, OutputStream out)
	{
		_time = _sim.get_time();
		while(_time > t)
		{
			_sim.advance(dt);
		}
		
		if(sv)
		{
			SimpleObjectViewer view = null;
			view = new SimpleObjectViewer("[ECOSYSTEM]", _sim.get_map_info().get_width(), _sim.get_map_info().get_height(), 
					_sim.get_map_info().get_cols(), _sim.get_map_info().get_rows());
			
			while (_time<t) {
			_time += dt;
			for( Animalnfo a : _sim.get_animals() ) ((Entity) a).update(dt); //revisar esto no creo que bien
			view.update(to_animals_info(_sim.get_animals()), _time, dt);
			}
		}
	}
}
