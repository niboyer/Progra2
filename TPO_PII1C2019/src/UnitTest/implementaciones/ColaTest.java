package UnitTest.implementaciones;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import implementaciones.ColaEstatica;

public class ColaTest {

	ColaEstatica cola;
	
	@Before
	public void setUp() throws Exception {
		cola = new ColaEstatica();
		cola.inicilizar();
	}
	
	@Test
	public void testColaVacia() {
		// Operaci�n
		boolean colaVacia = cola.colaVacia();
		
		// Validaci�n
		Assert.assertTrue(colaVacia);
	}
	
	@Test
	public void testColaNoVacia() {
		// Inicializaci�n
		cola.acolar("un valor");
		
		// Operaci�n
		boolean colaVacia = cola.colaVacia();
		
		// Validaci�n
		Assert.assertFalse(colaVacia);
	}

	@Test
	public void testAcolar() {
		// Inicializaci�n
		cola.acolar("primer valor");
		cola.acolar("segundo valor");
		cola.acolar("tercer valor");
		
		// Operaci�n
		String primerValor = cola.primero();
		cola.desacolar();
		String segundoValor = cola.primero();
		cola.desacolar();
		String tercerValor = cola.primero();
		cola.desacolar();
		
		// Validaci�n
		Assert.assertTrue(cola.colaVacia());
		Assert.assertEquals("primer valor", primerValor);
		Assert.assertEquals("segundo valor", segundoValor);
		Assert.assertEquals("tercer valor", tercerValor);
		
	}

}
