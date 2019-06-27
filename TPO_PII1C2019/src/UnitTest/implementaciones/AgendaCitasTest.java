package UnitTest.implementaciones;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import implementaciones.AgendaCitas;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class AgendaCitasTest {

	AgendaCitas agendaCitas;

	@Before
	public void setUp() throws Exception {
		agendaCitas = new AgendaCitas();
		agendaCitas.inicializar();
	}

	@Test
	public void testConjuntoAbogadosVacio() {
		// Operaci�n
		ConjuntoTDA abogados = agendaCitas.abogados();

		// Validaci�n
		Assert.assertTrue(abogados.conjuntoVacio());
	}

	@Test
	public void testConjuntoAbogadosNoVacioDespuesDeAgregarDia() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");

		// Operaci�n
		ConjuntoTDA abogados = agendaCitas.abogados();
		boolean conjuntoAbogadosNoVacioAntesDeEliminarUno = abogados.conjuntoVacio();
		String abogado = abogados.elegir();
		abogados.sacar(abogado);

		// Validaci�n
		Assert.assertFalse(conjuntoAbogadosNoVacioAntesDeEliminarUno);
		Assert.assertTrue(abogados.conjuntoVacio());
		Assert.assertEquals("un abogado", abogado);
	}

	@Test
	public void testConjuntoTurnosVacioConDiaAgregado() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");

		// Operaci�n
		ColaTDA turnos = agendaCitas.turnos("un abogado", "2019/01/01");

		// Validaci�n
		Assert.assertTrue(turnos.colaVacia());
	}

	@Test
	public void testConjuntoTurnosVacioConAbogadoNoExistente() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:00", "un cliente");

		// Operaci�n
		ColaTDA turnos = agendaCitas.turnos("abogado no existente", "2019/01/01");

		// Validaci�n
		Assert.assertTrue(turnos.colaVacia());
	}

	@Test
	public void testConjuntoTurnosVacioConDiaNoExistente() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:00", "un cliente");

		// Operaci�n
		ColaTDA turnos = agendaCitas.turnos("un abogado", "2019/01/02");

		// Validaci�n
		Assert.assertTrue(turnos.colaVacia());
	}

	@Test
	public void testConjuntoTurnosConDosTurnos() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "09:00", "otro cliente");

		// Operaci�n
		ColaTDA turnos = agendaCitas.turnos("un abogado", "2019/01/01");
		String primerCliente = turnos.primero();
		turnos.desacolar();
		String segundoCliente = turnos.primero();
		turnos.desacolar();

		// Validaci�n
		Assert.assertTrue(turnos.colaVacia());
		Assert.assertEquals("otro cliente", primerCliente);
		Assert.assertEquals("un cliente", segundoCliente);
	}

	@Test
	public void testConjuntoAbogadosVacioDespuesDeEliminarlo() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "09:00", "otro cliente");

		// Operaci�n
		agendaCitas.eliminarAbogado("un abogado");
		ConjuntoTDA abogados = agendaCitas.abogados();

		// Validaci�n
		Assert.assertTrue(abogados.conjuntoVacio());
	}
	
	@Test
	public void testConjuntoAbogadosNoVacioDespuesDeEliminarUnoDeDosAbogados() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("otro abogado", "martes", "2019/01/01");
		
		// Operaci�n
		agendaCitas.eliminarAbogado("un abogado");
		ConjuntoTDA abogados = agendaCitas.abogados();

		// Validaci�n
		Assert.assertFalse(abogados.conjuntoVacio());
		Assert.assertFalse(abogados.pertenece("un abogado"));
		Assert.assertTrue(abogados.pertenece("otro abogado"));
	}

	@Test
	public void testEliminarUnicaFechaParaUnAbogado() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("otro abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "09:00", "otro cliente");

		// Operaci�n
		agendaCitas.eliminarFecha("un abogado", "2019/01/01");
		ConjuntoTDA abogados = agendaCitas.abogados();

		// Validaci�n
		Assert.assertFalse(abogados.pertenece("un abogado"));
		Assert.assertTrue(abogados.pertenece("otro abogado"));
	}

	@Test
	public void testEliminarUnaFechaParaUnAbogadoTeniendoOtraFecha() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("un abogado", "miercoles", "2019/01/02");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("otro abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "09:00", "otro cliente");

		// Operaci�n
		agendaCitas.eliminarFecha("un abogado", "2019/01/01");
		ConjuntoTDA abogados = agendaCitas.abogados();
		ColaTDA noDeberiaHaberTurnos = agendaCitas.turnos("un abogado", "2019/01/01");
		ColaTDA deberianHaberTurnos = agendaCitas.turnos("un abogado", "2019/01/02");

		// Validaci�n
		Assert.assertTrue(abogados.pertenece("un abogado"));
		Assert.assertTrue(abogados.pertenece("otro abogado"));
		Assert.assertTrue(noDeberiaHaberTurnos.colaVacia());
		Assert.assertFalse(deberianHaberTurnos.colaVacia());
	}
	
	@Test
	public void testExistenCitasAgregadasParaAmbosAbogados() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("un abogado", "miercoles", "2019/01/02");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("otro abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "09:00", "otro cliente");

		// Operaci�n
		boolean existeCitaUnAbogadoNueveAm = agendaCitas.existeCita("un abogado", "2019/01/01", "09:00");
		boolean existeCitaOtroAbogadoDiezAm = agendaCitas.existeCita("otro abogado", "2019/01/01", "10:00");

		// Validaci�n
		Assert.assertTrue(existeCitaUnAbogadoNueveAm);
		Assert.assertTrue(existeCitaOtroAbogadoDiezAm);
	}
	
	@Test
	public void testNoExisteCitaParaAbogadoNoExistente() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("un abogado", "miercoles", "2019/01/02");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("otro abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "09:00", "otro cliente");

		// Operaci�n
		boolean existeCitaUnAbogadoNoExistenteNueveAm = agendaCitas.existeCita("un abogado no existente", "2019/01/01", "09:00");

		// Validaci�n
		Assert.assertFalse(existeCitaUnAbogadoNoExistenteNueveAm);
	}
	
	@Test
	public void testNoExisteCitaAgregadasParaHorarioNoExistente() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("un abogado", "miercoles", "2019/01/02");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("otro abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "09:00", "otro cliente");

		// Operaci�n
		boolean existeCitaUnAbogadoNueveYMediaAm = agendaCitas.existeCita("un abogado", "2019/01/01", "09:30");

		// Validaci�n
		Assert.assertFalse(existeCitaUnAbogadoNueveYMediaAm);
	}
	
	@Test
	public void testNoExisteCitaCuandoSeEliminaElAbogado() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("un abogado", "miercoles", "2019/01/02");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("otro abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "09:00", "otro cliente");

		// Operaci�n
		agendaCitas.eliminarAbogado("otro abogado");
		boolean existeCitaUnAbogadoNueveAm = agendaCitas.existeCita("un abogado", "2019/01/01", "09:00");
		boolean existeCitaOtroAbogadoDiezAm = agendaCitas.existeCita("otro abogado", "2019/01/01", "10:00");

		// Validaci�n
		Assert.assertTrue(existeCitaUnAbogadoNueveAm);
		Assert.assertFalse(existeCitaOtroAbogadoDiezAm);
	}
	
	@Test
	public void testNoExisteCitaCuandoSeEliminaLaFecha() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("un abogado", "miercoles", "2019/01/02");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("otro abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "09:00", "otro cliente");

		// Operaci�n
		agendaCitas.eliminarFecha("un abogado", "2019/01/01");
		boolean existeCitaUnAbogadoNueveAmDiaEliminado = agendaCitas.existeCita("un abogado", "2019/01/01", "09:00");
		boolean existeCitaUnAbogadoNueveAmDiaExistente = agendaCitas.existeCita("un abogado", "2019/01/02", "09:00");

		// Validaci�n
		Assert.assertFalse(existeCitaUnAbogadoNueveAmDiaEliminado);
		Assert.assertTrue(existeCitaUnAbogadoNueveAmDiaExistente);
	}
	
	@Test
	public void testNoExisteCitaCuandoSeEliminaLaCita() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("un abogado", "miercoles", "2019/01/02");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "09:00", "otro cliente");
		agendaCitas.agregarNuevoDia("otro abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "10:00", "un cliente");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "09:00", "otro cliente");

		// Operaci�n
		agendaCitas.eliminarCita("un abogado", "2019/01/01", "09:00", "otro cliente");
		boolean existeCitaUnAbogadoNueveAm = agendaCitas.existeCita("un abogado", "2019/01/01", "09:00");
		boolean existeCitaUnAbogadoDiezAm = agendaCitas.existeCita("un abogado", "2019/01/01", "10:00");

		// Validaci�n
		Assert.assertFalse(existeCitaUnAbogadoNueveAm);
		Assert.assertTrue(existeCitaUnAbogadoDiezAm);
	}
	
	@Test
	public void testObtenerClientesEnCitas() {
		// Inicializaci�n
		agendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:00", "primer cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "09:00", "segundo cliente");
		agendaCitas.agregarNuevoDia("un abogado", "miercoles", "2019/01/02");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "10:00", "tercer cliente");
		agendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "09:00", "cuarto cliente");
		agendaCitas.agregarNuevoDia("otro abogado", "martes", "2019/01/01");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "10:00", "quinto cliente");
		agendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "09:00", "sexto cliente");

		// Operaci�n
		String primerCliente = agendaCitas.clienteEnCita("un abogado", "2019/01/01", "10:00");
		String segundoCliente = agendaCitas.clienteEnCita("un abogado", "2019/01/01", "09:00");
		String tercerCliente = agendaCitas.clienteEnCita("un abogado", "2019/01/02", "10:00");
		String cuartoCliente = agendaCitas.clienteEnCita("un abogado", "2019/01/02", "09:00");
		String quintoCliente = agendaCitas.clienteEnCita("otro abogado", "2019/01/01", "10:00");
		String sextoCliente = agendaCitas.clienteEnCita("otro abogado", "2019/01/01", "09:00");

		// Validaci�n
		Assert.assertEquals("primer cliente", primerCliente);
		Assert.assertEquals("segundo cliente", segundoCliente);
		Assert.assertEquals("tercer cliente", tercerCliente);
		Assert.assertEquals("cuarto cliente", cuartoCliente);
		Assert.assertEquals("quinto cliente", quintoCliente);
		Assert.assertEquals("sexto cliente", sextoCliente);
	}

}
