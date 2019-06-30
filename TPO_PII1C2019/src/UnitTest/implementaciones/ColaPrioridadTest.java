package UnitTest.implementaciones;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import implementaciones.ColaPrioridadEstatica;

public class ColaPrioridadTest {

	ColaPrioridadEstatica colaPrioridad;

	@Before
	public void setUp() throws Exception {
		colaPrioridad = new ColaPrioridadEstatica();
		colaPrioridad.inicializar();
	}

	@Test
	public void testColaVacia() {
		// Operaci�n
		boolean colaPrioridadVacia = colaPrioridad.colaVacia();

		// Validaci�n
		Assert.assertTrue(colaPrioridadVacia);
	}

	@Test
	public void testColaNoVacia() {
		// Inicializaci�n
		colaPrioridad.acolar("un abogado", "10:30");

		// Operaci�n
		boolean colaPrioridadVacia = colaPrioridad.colaVacia();

		// Validaci�n
		Assert.assertFalse(colaPrioridadVacia);
	}

	@Test
	public void testPrimerValor() {
		// Inicializaci�n
		colaPrioridad.acolar("un abogado que atiende 10am", "10:30");
		colaPrioridad.acolar("un abogado que atiende 12am", "12:00");
		colaPrioridad.acolar("un abogado que atiende 9am", "09:00");
		colaPrioridad.acolar("un abogado que atiende 9pm", "21:00");

		// Operaci�n
		String abogadoQueAtiendePrimero = colaPrioridad.primero();

		// Validaci�n
		Assert.assertEquals("un abogado que atiende 9am", abogadoQueAtiendePrimero);
	}

	@Test
	public void testSegundoValor() {
		// Inicializaci�n
		colaPrioridad.acolar("un abogado que atiende 10.30am", "10:30");
		colaPrioridad.acolar("un abogado que atiende 12am", "12:00");
		colaPrioridad.acolar("un abogado que atiende 9am", "09:00");
		colaPrioridad.acolar("un abogado que atiende 9pm", "21:00");

		// Operaci�n
		colaPrioridad.desacolar();
		String abogadoQueAtiendeSegundo = colaPrioridad.primero();

		// Validaci�n
		Assert.assertEquals("un abogado que atiende 10.30am", abogadoQueAtiendeSegundo);
	}

	@Test
	public void testDosValoresEnLaMismaPrioridad() {
		// Inicializaci�n
		colaPrioridad.acolar("un abogado que atiende 10.30am", "10:30");
		colaPrioridad.acolar("un abogado que atiende 12am", "12:00");
		colaPrioridad.acolar("un abogado que atiende 9am", "09:00");
		colaPrioridad.acolar("un abogado que atiende 9pm", "21:00");
		colaPrioridad.acolar("otro abogado que atiende 9am", "09:00");

		// Operaci�n
		String unAbogadoQueAtiendeEnElPrimerHorario = colaPrioridad.primero();
		colaPrioridad.desacolar();
		String otroAbogadoQueAtiendeEnElPrimerHorario = colaPrioridad.primero();
		colaPrioridad.desacolar();
		String unAbogadoQueAtiendeEnElSegundoHorario = colaPrioridad.primero();

		// Validaci�n
		Assert.assertEquals("un abogado que atiende 9am", unAbogadoQueAtiendeEnElPrimerHorario);
		Assert.assertEquals("otro abogado que atiende 9am", otroAbogadoQueAtiendeEnElPrimerHorario);
		Assert.assertEquals("un abogado que atiende 10.30am", unAbogadoQueAtiendeEnElSegundoHorario);
	}

	@Test
	public void testPrioridad() {
		// Inicializaci�n
		colaPrioridad.acolar("un abogado que atiende 10.30am", "10:30");
		colaPrioridad.acolar("un abogado que atiende 12am", "12:00");
		colaPrioridad.acolar("un abogado que atiende 9am", "09:00");
		colaPrioridad.acolar("un abogado que atiende 9pm", "21:00");
		colaPrioridad.acolar("otro abogado que atiende 9am", "09:00");

		// Operaci�n
		String primeraPrioridad = colaPrioridad.prioridad();
		colaPrioridad.desacolar();
		String segundaPrioridad = colaPrioridad.prioridad();
		colaPrioridad.desacolar();
		String terceraPrioridad = colaPrioridad.prioridad();
		colaPrioridad.desacolar();
		String cuartaPrioridad = colaPrioridad.prioridad();
		colaPrioridad.desacolar();
		String quintaPrioridad = colaPrioridad.prioridad();
		colaPrioridad.desacolar();
		
		// Validaci�n
		Assert.assertEquals("09:00", primeraPrioridad);
		Assert.assertEquals("09:00", segundaPrioridad);
		Assert.assertEquals("10:30", terceraPrioridad);
		Assert.assertEquals("12:00", cuartaPrioridad);
		Assert.assertEquals("21:00", quintaPrioridad);
	}

}
