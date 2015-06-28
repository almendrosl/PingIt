package Tests;

import Beat.BeatController;
import Beat.BeatModel;
import Beat.BeatModelInterface;
import ControllerInterface.ControllerInterface;
import Heart.HeartController;
import Heart.HeartModel;
import Ping.*;

public class TestDrive {

	public static void main(String[] args) {
		PingModelInterface pingmodel = new PingModel();
		ControllerInterface pingcontroller = new PingController(pingmodel);
		BeatModelInterface model = new BeatModel();
		ControllerInterface controller = new BeatController(model);
		HeartModel heartModel = HeartModel.getInstance();
        ControllerInterface heart = new HeartController(heartModel);
	}

}
