package simulator.control;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.factories.Builder;
import simulator.factories.BuilderBasedFactory;
import simulator.factories.DefaultRegionBuilder;
import simulator.factories.DynamicSupplyRegionBuilder;
import simulator.factories.Factory;
import simulator.factories.SelectClosestBuilder;
import simulator.factories.SelectFirstBuilder;
import simulator.factories.SheepBuilder;
import simulator.factories.WolfBuilder;
import simulator.model.Animal;
import simulator.model.Animalnfo;
import simulator.model.Entity;
import simulator.model.Region;
import simulator.model.SelectionStrategy;
import simulator.model.Simulator;
import simulator.view.SimpleObjectViewer;
import simulator.view.SimpleObjectViewer.ObjInfo;

public class Controller {
	
	protected Simulator _sim;
	protected double _time;
	
	private static Factory<Animal> _animal_factory;
	private static Factory<Region> _region_factory;
	
	public Controller(Simulator sim, Factory<Animal> animal_factory, Factory<Region> region_factory) //ver si se puede pasar por parametro las factorias
	{
		_sim = sim;
		_animal_factory = animal_factory;
		_region_factory = region_factory;
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
		
		if(data.has("regions")) {
			JSONArray _regiones = data.getJSONArray("regions");
			
			for(int i = 0; i < _regiones.length(); ++i)
			{
				JSONObject _region = _regiones.getJSONObject(i);
				
				JSONArray _row = _region.getJSONArray("row");
				int rf = _row.getInt(0);
				int rt = _row.getInt(1);
				
				JSONArray _col = _region.getJSONArray("col");
				int cf = _col.getInt(0);
				int ct = _col.getInt(1);
				
				JSONObject spec = _region.getJSONObject("spec");
				
				for (int R = rf; R <= rt; R++) {
	                for (int C = cf; C <= ct; C++) {
	         
	                    _sim.set_region(R, C, spec);
	                }
	            }	
			}
			
		}
		
		
		JSONArray _animales = data.getJSONArray("animals");
		
		for(int i = 0; i < _animales.length(); ++i)
		{
			JSONObject _animal = _animales.getJSONObject(i);
			int cantidad = _animal.getInt("amount");
			
			for(int j = 0; j < cantidad; ++j)
			{
				JSONObject spec = _animal.getJSONObject("spec");
				_sim.add_animal(spec);
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		/*
	    if (data.has("regions")) {
	        JSONArray regions = data.getJSONArray("regions");
	        for (int i = 0; i < regions.length(); i++) {
	            JSONObject region = regions.getJSONObject(i);
	            JSONArray rowRange = region.getJSONArray("row");
	            JSONArray colRange = region.getJSONArray("col");
	            JSONObject spec = region.getJSONObject("spec");

	            int rf = rowRange.getInt(0);
	            int rt = rowRange.getInt(1);
	            int cf = colRange.getInt(0);
	            int ct = colRange.getInt(1);

	            for (int R = rf; R <= rt; R++) {
	                for (int C = cf; C <= ct; C++) {
	                    // Simulador no tiene un método set_region que acepte JSONObject directamente,
	                    // por lo que podrías crear la región usando el Factory<Region> y luego establecerla
	                    // en el simulador.
	         
	                    Region newRegion = _region_factory.create_instance(spec);// Crea la región usando Factory<Region> y spec
	                    _sim.set_region(R, C, newRegion);
	                }
	            }
	        }
	    }

	    if (data.has("animals")) {
	        JSONArray animals = data.getJSONArray("animals");
	        for (int i = 0; i < animals.length(); i++) {
	            JSONObject animal = animals.getJSONObject(i);
	            int amount = animal.getInt("amount");
	            JSONObject spec = animal.getJSONObject("spec");

	            for (int j = 0; j < amount; j++) {
	                // Simulador no tiene un método add_animal que acepte JSONObject directamente,
	                // por lo que podrías crear el animal usando el Factory<Animal> y luego agregarlo
	                // al simulador.
	            	
	            	
	            	
	                Animal newAnimal = _animal_factory.create_instance(spec);// Crea el animal usando Factory<Animal> y spec
	                _sim.add_animal(newAnimal);
	            }
	        }
	    }*/
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
