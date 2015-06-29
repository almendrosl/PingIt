package Tests;

import ControllerInterface.ControllerInterface;
import Ping.*;

public class PingTestDrive {

	public static void main(String[] args) {
		PingModelInterface model = new PingModel();
		ControllerInterface controller = new PingController(model);
	}

}
