package Ping;

import ControllerInterface.ControllerInterface;

public class prueba {

	public static void main(String[] args) {
		PingModelInterface p = new PingModel();
		ControllerInterface c = new PingController(p);
	}

}
