package Ping;

import View.DJView;
import ControllerInterface.ControllerInterface;
  
public class PingController implements ControllerInterface {
	PingModelInterface model;
	DJView view;
  
	public PingController(PingModelInterface model,DJView view) {
		this.model = model;
		this.view = view;
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
        int frec = model.getFrec();
        model.setFrec(frec + 1000);
	}
    
	public void decreaseBPM() {
        int frec = model.getFrec();
        model.setFrec(frec - 1000);
  	}
  
 	public void setBPM(int bpm) {
		model.setFrec(bpm*1000);
	}
}
