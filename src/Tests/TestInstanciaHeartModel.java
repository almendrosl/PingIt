package Tests;

import static org.junit.Assert.*;

import org.junit.*;

import Heart.HeartModel;

public class TestInstanciaHeartModel {
	
	private HeartModel heart;
	@Before
	public void setUp() throws Exception {
		heart = HeartModel.getInstance();
	}
	
	//Probamos que solo se puede instanciar 1 vez el HeartModel tratando de hecer diferentes
	//instancias y comparando para ver si son iguales
	@Test
	public void instanciar() {
		for (int i = 0; i < 100; i++) {
			assertSame(heart, HeartModel.getInstance()); 
		}
	}
	
	//Testeamos el numero de instentos de instancia
	@Test
	public void numInstancias() {
		for(int i = 0; i<100; i++) {
			heart = HeartModel.getInstance();
		}
		int real = heart.getNumeroInstancias();
		int esperado = 101;
		assertEquals(esperado,real);
	}
	
}
