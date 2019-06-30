package UnitTest.implementaciones;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import implementaciones.ArbolCitas;
import tdas.ArbolCitasTDA;

public class ArbolCitasTest {

	ArbolCitas arbolCitas;

	@Before
	public void setUp() throws Exception {
		arbolCitas = new ArbolCitas();
		arbolCitas.inicializar();
	}

	@Test
	public void testArbolVacio() {
		// Operaci�n
		boolean arbolCitasVacio = arbolCitas.arbolVacio();

		// Validaci�n
		Assert.assertTrue(arbolCitasVacio);
	}

	@Test
	public void testDevolverHoraUnicoNodo() {
		// Inicializaci�n
		arbolCitas.agregar("04:30", "cliente 4.30am");

		// Operaci�n
		String hora = arbolCitas.hora();

		// Validaci�n
		Assert.assertEquals("04:30", hora);
	}

	@Test
	public void testDevolverClienteUnicoNodo() {
		// Inicializaci�n
		arbolCitas.agregar("04:30", "cliente 4.30am");

		// Operaci�n
		String cliente = arbolCitas.cliente();

		// Validaci�n
		Assert.assertEquals("cliente 4.30am", cliente);
	}

	@Test
	public void testHijoIzquierdoVacio() {
		// Inicializaci�n
		arbolCitas.agregar("04:30", "cliente 4.30am");

		// Operaci�n
		ArbolCitasTDA hijoIzquierdo = arbolCitas.hijoIzquierdo();

		// Validaci�n
		Assert.assertTrue(hijoIzquierdo.arbolVacio());
	}

	@Test
	public void testHijoDerechoVacio() {
		// Inicializaci�n
		arbolCitas.agregar("04:30", "cliente 4.30am");

		// Operaci�n
		ArbolCitasTDA hijoDerecho = arbolCitas.hijoDerecho();

		// Validaci�n
		Assert.assertTrue(hijoDerecho.arbolVacio());
	}

	@Test
	public void testDevolverHoraRaizHijoIzquierdo() {
		// Inicializaci�n
		arbolCitas.agregar("04:30", "cliente 4.30am");
		arbolCitas.agregar("02:00", "cliente 2am");
		arbolCitas.agregar("07:30", "cliente 7.30am");

		// Operaci�n
		String hora = arbolCitas.hijoIzquierdo().hora();

		// Validaci�n
		Assert.assertEquals("02:00", hora);
	}

	@Test
	public void testDevolverClienteRaizHijoIzquierdo() {
		// Inicializaci�n
		arbolCitas.agregar("04:30", "cliente 4.30am");
		arbolCitas.agregar("02:00", "cliente 2am");
		arbolCitas.agregar("07:30", "cliente 7.30am");

		// Operaci�n
		String cliente = arbolCitas.hijoIzquierdo().cliente();

		// Validaci�n
		Assert.assertEquals("cliente 2am", cliente);
	}

	@Test
	public void testDevolverHoraRaizHijoDerecho() {
		// Inicializaci�n
		arbolCitas.agregar("04:30", "cliente 4.30am");
		arbolCitas.agregar("02:00", "cliente 2am");
		arbolCitas.agregar("07:30", "cliente 7.30am");

		// Operaci�n
		String hora = arbolCitas.hijoDerecho().hora();

		// Validaci�n
		Assert.assertEquals("07:30", hora);
	}

	@Test
	public void testDevolverClienteRaizHijoDerecho() {
		// Inicializaci�n
		arbolCitas.agregar("04:30", "cliente 4.30am");
		arbolCitas.agregar("02:00", "cliente 2am");
		arbolCitas.agregar("07:30", "cliente 7.30am");

		// Operaci�n
		String cliente = arbolCitas.hijoDerecho().cliente();

		// Validaci�n
		Assert.assertEquals("cliente 7.30am", cliente);
	}

	@Test
	public void testEliminarHoja() {
		// Inicializaci�n
		arbolCitas.agregar("04:30", "cliente 4.30am");
		arbolCitas.agregar("02:00", "cliente 2am");
		arbolCitas.agregar("07:30", "cliente 7.30am");
		
		// Operaci�n
		arbolCitas.eliminar("07:30", "cliente 7.30am");
		
		// Validaci�n
		Assert.assertTrue(arbolCitas.hijoDerecho().arbolVacio());
	}
	
	@Test
	public void testEliminarNodoClienteNoExistente() {
		// Inicializaci�n
		arbolCitas.agregar("04:30", "cliente 4.30am");
		arbolCitas.agregar("02:00", "cliente 2am");
		arbolCitas.agregar("07:30", "cliente 7.30am");
		
		// Operaci�n
		arbolCitas.eliminar("07:30", "cliente de mentira");
		
		// Validaci�n
		Assert.assertFalse(arbolCitas.hijoDerecho().arbolVacio());
	}
	
	@Test
	public void testEliminarNodoHoraNoExistente() {
		// Inicializaci�n
		arbolCitas.agregar("04:30", "cliente 4.30am");
		arbolCitas.agregar("02:00", "cliente 2am");
		arbolCitas.agregar("07:30", "cliente 7.30am");
		
		// Operaci�n
		arbolCitas.eliminar("08:30", "cliente 7.30am");
		
		// Validaci�n
		Assert.assertFalse(arbolCitas.hijoDerecho().arbolVacio());
	}
	
	@Test
	public void testEliminarNodoIntermedioConHijoDerecho() {
		// Inicializaci�n
		arbolCitas.agregar("04:30", "cliente 4.30am");
		arbolCitas.agregar("02:00", "cliente 2am");
		arbolCitas.agregar("07:30", "cliente 7.30am");
		arbolCitas.agregar("08:30", "cliente 8.30am");
		
		// Operaci�n
		arbolCitas.eliminar("07:30", "cliente 7.30am");
		
		// Validaci�n
		Assert.assertFalse(arbolCitas.hijoDerecho().arbolVacio());
		Assert.assertTrue(arbolCitas.hijoDerecho().hijoDerecho().arbolVacio());
	}
	
	@Test
	public void testEliminarNodoIntermedioConHijoIzquierdo() {
		// Inicializaci�n
		arbolCitas.agregar("04:30", "cliente 4.30am");
		arbolCitas.agregar("02:00", "cliente 2am");
		arbolCitas.agregar("07:30", "cliente 7.30am");
		arbolCitas.agregar("07:00", "cliente 7am");
		
		// Operaci�n
		arbolCitas.eliminar("07:30", "cliente 7.30am");
		
		// Validaci�n
		Assert.assertFalse(arbolCitas.hijoDerecho().arbolVacio());
		Assert.assertTrue(arbolCitas.hijoDerecho().hijoIzquierdo().arbolVacio());
	}

}
