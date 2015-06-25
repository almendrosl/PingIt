package Heart;

import Beat.BPMObserver;
import Beat.BeatObserver;

public interface HeartModelInterface {
	int getHeartRate();
	int getNumeroInstancias();
	void notifyInstanciasObservers();
	void registerObserver(BeatObserver o);
	void removeObserver(BeatObserver o);
	void registerObserver(BPMObserver o);
	void removeObserver(BPMObserver o);
}
