package Ping;

import Beat.BPMObserver;
import Beat.BeatObserver;

public interface PingModelInterface {
	
	void onCycle();
  
	void offCycle();
  
    void setURL(String ip);
  
	int getPing();
  
	void registerObserver(BeatObserver o);
  
	void removeObserver(BeatObserver o);
  
	void registerObserver(BPMObserver o);

	void removeObserver(BPMObserver o);
	
	void getCommand();
	
	void doCommand();
	
	void parsing(String line);
	
	int promedio(String tms);
	
}
