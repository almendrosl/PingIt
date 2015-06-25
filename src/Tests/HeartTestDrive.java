package Tests;

import ControllerInterface.ControllerInterface;
import Heart.HeartController;
import Heart.HeartModel;
  
public class HeartTestDrive {

    public static void main (String[] args) {
		HeartModel heartModel = HeartModel.getInstance();
        ControllerInterface model = new HeartController(heartModel);
    }
}
