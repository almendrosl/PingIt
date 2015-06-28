package Heart;

import View.PingView;
import ControllerInterface.ControllerInterface;
  
public class HeartController implements ControllerInterface {
	HeartModelInterface model;
	PingView view;
  
	public HeartController(HeartModelInterface model) {
		this.model = model;
		view = new PingView(this, new HeartAdapter(model));
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



