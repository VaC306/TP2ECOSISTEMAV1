package simulator.control;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import simulator.model.Animal;
import simulator.model.Animalnfo;
import simulator.model.Entity;
import simulator.model.Simulator;
import simulator.view.SimpleObjectViewer;
import simulator.view.SimpleObjectViewer.ObjInfo;

public class Controller {
	
	protected Simulator _sim;
	
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
	
	public void run(double t, double dt, boolean sv, OutputStream out)
	{
		while(_sim.get_time() > t)
		{
			_sim.advance(dt);
		}
		
		if(sv)
		{
			SimpleObjectViewer view = null;
			view = new SimpleObjectViewer("[ECOSYSTEM]", 800, 600, 15, 20);
			
			while (t<10) {
			t += dt;
			for( Animalnfo a : _sim.get_animals() ) ((Entity) a).update(dt); //revisar esto no creo que bien
			view.update(to_animals_info(_sim.get_animals()), t, dt);
			}
		}
	}
}
