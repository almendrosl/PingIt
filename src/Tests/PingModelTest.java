package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Ping.PingModel;
import Ping.PingModelInterface;

public class PingModelTest {
	PingModelInterface model;
	@Before
	public void setUp() throws Exception {
		model = new PingModel();
	}

	@Test
	public void testOnCycle() {
		
	}

}
