package Ping;

import View.PingView;
import ControllerInterface.ControllerInterface;
  
public class PingController implements ControllerInterface {
	PingModelInterface model;
	PingView view;
  
	public PingController(PingModelInterface model) {
		this.model = model;
		view = new PingView(this, model);
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
        int frec = model.getFrec();
        model.setFrec(frec + 1000);
	}
    
	public void decreaseBPM() {
        int frec = model.getFrec();
        model.setFrec(frec - 1000);
  	}
  
 	public void setBPM(int pingSeg, String ip) {
		model.setFrec(pingSeg*1000);
		model.setURL(ip);
	}
}
