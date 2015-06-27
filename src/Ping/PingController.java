package Ping;

import View.DJView;
import ControllerInterface.ControllerInterface;
  
public class PingController implements ControllerInterface {
	PingModelInterface model;
	DJView view;
  
	public PingController(PingModelInterface model) {
		this.model = model;
		view = new DJView(this, new PingAdapter(model));
        view.createView();
        view.createControls();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
	}
  
	public void start() {
		model.onCycle();
		view.disableStartMenuItem();
		view.enableStopMenuItem();
	}
  
	public void stop() {
		model.offCycle();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
	}
    
	public void increaseBPM() {
//        int bpm = model.getBPM();
//        model.setBPM(bpm + 1);
	}
    
	public void decreaseBPM() {
//        int bpm = model.getBPM();
//        model.setBPM(bpm - 1);
  	}
  
 	public void setBPM(int bpm) {
		model.setFrec(bpm);
	}
}
