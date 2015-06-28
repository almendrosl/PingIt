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
	
	public String getCommand();
	
	void doCommand();
	
	void parsing(String line);
	
	int promedio(String tms);
	
	void setFrec(int frec);
	
	int getFrec();
	
	public String getURL();
	
	public int getEnviados();
	
	public int getRecibidos();
	
	public int getPerdidos();
	
	public int getPingMedio();
	String getPing2();
	
	
}
