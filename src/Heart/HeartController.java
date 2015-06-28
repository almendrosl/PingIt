package Heart;

import View.DJView;
import ControllerInterface.ControllerInterface;
  
public class HeartController implements ControllerInterface {
	HeartModelInterface model;
	DJView view;
  
	public HeartController(HeartModelInterface model) {
		this.model = model;
		view = DJView.getInstance();
        view.createView();
        view.createControls();
		view.disableStopMenuItem();
		view.disableStartMenuItem();
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



