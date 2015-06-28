package Heart;

import View.DJView;
import ControllerInterface.ControllerInterface;
  
public class HeartController implements ControllerInterface {
	HeartModelInterface model;
	DJView view;
  
	public HeartController(HeartModelInterface model) {
		this.model = model;
		model.notifyInstanciasObservers();
	}
  
	public void start() {}
 
	public void stop() {}
    
	public void increaseBPM() {
		HeartModel heartModel = HeartModel.getInstance();
		model.notifyInstanciasObservers();
	}
    
	public void decreaseBPM() {}
  
 	public void setBPM(int bpm) {}
}



