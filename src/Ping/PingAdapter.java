package Ping;

import Beat.BPMObserver;
import Beat.BeatModelInterface;
import Beat.BeatObserver;
import Ping.PingModelInterface;

public class PingAdapter implements BeatModelInterface {
	PingModelInterface ping;
 
	public PingAdapter(PingModelInterface ping) {
		this.ping = ping;
	}

    public void initialize() {}
  
    public void on() {
    	ping.onCycle();
    }
  
    public void off() {
    	ping.offCycle();
    }
   
	public int getBPM() {
		return ping.getPing();
	}
  
    public void setBPM(String ip) {
    	ping.setURL(ip);
    }
   
	public void registerObserver(BeatObserver o) {
		ping.registerObserver(o);
	}
    
	public void removeObserver(BeatObserver o) {
		ping.removeObserver(o);
	}
     
	public void registerObserver(BPMObserver o) {
		ping.registerObserver(o);
	}
  
	public void removeObserver(BPMObserver o) {
		ping.removeObserver(o);
	}
}
