package TestAgenda;

import algoritmos.Algoritmo;
import implementaciones.AgendaCitas;
import implementaciones.ArbolCitas;
import implementaciones.ColaEstatica;
import implementaciones.ColaPrioridadEstatica;
import implementaciones.ConjuntoEstatico;
import tdas.AgendaCitasTDA;
import tdas.ArbolCitasTDA;
import tdas.ColaPrioridadTDA;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class Main {

	public static void main(String[] args) {
		ColaTDA testCola = new ColaEstatica();
		ColaPrioridadTDA testColaPrioridad = new ColaPrioridadEstatica();
		ConjuntoTDA testConjunto = new ConjuntoEstatico();
		ArbolCitasTDA testArbolCitas = new ArbolCitas();
		AgendaCitasTDA testAgendaCitas = new AgendaCitas();
		Algoritmo testAlg = new Algoritmo();

		testCola.inicilizar();
		testColaPrioridad.inicializar();
		testConjunto.inicializar();
		testArbolCitas.inicializar();
		testAgendaCitas.inicializar();

		testCola.acolar("Juan Alberto Zaragosa");
		testCola.acolar("Pedro Joaquin Testro");
		testCola.acolar("Nicolas Matias Boyer");
		testCola.acolar("Nicolas Matias BOYER");

		MostrarCola(testCola);

		testColaPrioridad.acolar("Juan Alberto Zaragosa", "08:00");
		testColaPrioridad.acolar("Pedro Joaquin Testro", "08:00");
		testColaPrioridad.acolar("Nicolas Matias Boyer", "08:30");
		testColaPrioridad.acolar("Sebastian", "09:00");
		testColaPrioridad.acolar("Fran", "09:00");
		testColaPrioridad.acolar("Sol", "08:30");
		testColaPrioridad.acolar("Tito", "08:00");

		MostrarColaPrioridad(testColaPrioridad);

		while (!testCola.colaVacia()) {
			testConjunto.agregar(testCola.primero());
			testCola.desacolar();
		}
		
		MostrarConjunto(testConjunto);
		
		testArbolCitas.agregar("08:00", "Juan");
		testArbolCitas.agregar("09:00", "Sebastian");
		testArbolCitas.agregar("07:00", "Pablo");
		testArbolCitas.agregar("07:30", "Joaquin");
		testArbolCitas.agregar("06:30", "Nicolas");
		testArbolCitas.agregar("08:30", "Pedro");
		testArbolCitas.agregar("09:30", "Benito");
		testArbolCitas.eliminar("08:00", "Juan");
		
		testAgendaCitas.agregarNuevoDia("Abogado1", "Lunes", "2019/06/24");
		testAgendaCitas.agregarNuevoDia("Abogado2", "Lunes", "2019/06/24");
		testAgendaCitas.agregarNuevoDia("Abogado2", "Martes", "2019/06/25");
		testAgendaCitas.agregarNuevoDia("Abogado2", "Miercoles", "2019/06/26");
		testAgendaCitas.agregarNuevoDia("Abogado3", "Lunes", "2019/06/24");
		testAgendaCitas.agregarNuevoDia("Abogado3", "Martes", "2019/06/25");
		testAgendaCitas.agregarNuevoDia("Abogado3", "Miercoles", "2019/06/26");
		
		testAgendaCitas.agregarNuevaCita("Abogado1", "2019/06/24", "09:30", "Cliente1");
		testAgendaCitas.agregarNuevaCita("Abogado1", "2019/06/24", "08:00", "Cliente2");
		testAgendaCitas.agregarNuevaCita("Abogado1", "2019/06/24", "08:30", "Cliente3");
		testAgendaCitas.agregarNuevaCita("Abogado2", "2019/06/24", "07:30", "Cliente4");
		testAgendaCitas.agregarNuevaCita("Abogado2", "2019/06/25", "07:00", "Cliente5");
		testAgendaCitas.agregarNuevaCita("Abogado2", "2019/06/25", "10:00", "Cliente6");
		testAgendaCitas.agregarNuevaCita("Abogado2", "2019/06/25", "10:30", "Cliente7");
		
		testAgendaCitas.eliminarAbogado("Abogado1");
		testAgendaCitas.eliminarFecha("Abogado2", "2019/06/24");
		testAgendaCitas.eliminarCita("Abogado2", "2019/06/25", "07:00", "Cliente5");
		testAgendaCitas.existeCita("Abogado2", "2019/06/25", "09:00");
		testAgendaCitas.clienteEnCita("Abogado2", "2019/06/25", "10:30");
		
		testAgendaCitas.agregarNuevaCita("Abogado2", "2019/06/25", "07:00", "Cliente5");
		testAgendaCitas.agregarNuevaCita("Abogado2", "2019/06/25", "06:30", "Cliente6");
		testAgendaCitas.agregarNuevaCita("Abogado2", "2019/06/25", "07:30", "Cliente7");
		testAgendaCitas.agregarNuevaCita("Abogado2", "2019/06/25", "08:00", "Cliente5");
		testAgendaCitas.agregarNuevaCita("Abogado2", "2019/06/25", "10:00", "Cliente6");
		testAgendaCitas.agregarNuevaCita("Abogado2", "2019/06/25", "09:30", "Cliente7");
		
		System.out.println("Primero: " + testAgendaCitas.turnos("Abogado2", "2019/06/25").primero());
		
		testAlg.disponible(testAgendaCitas, "Abogado2", "2019/06/25", "2019/06/25");
		testAlg.libresTotal(testAgendaCitas, "2019/07/01");
		
		System.out.println("Inicio Test masCitas");
		testAgendaCitas.inicializar();
		
		testAgendaCitas.agregarNuevoDia("Abogado4", "Jueves", "2019/06/27");
		testAgendaCitas.agregarNuevoDia("Abogado4", "Martes", "2019/06/25");
		testAgendaCitas.agregarNuevoDia("Abogado4", "Lunes", "2019/06/24");
		testAgendaCitas.agregarNuevoDia("Abogado4", "Miercoles", "2019/06/26");
		testAgendaCitas.agregarNuevoDia("Abogado2", "Lunes", "2019/06/24");
		testAgendaCitas.agregarNuevoDia("Abogado2", "Martes", "2019/06/25");
		
		testAgendaCitas.agregarNuevaCita("Abogado2", "2019/06/24", "07:00", "Cliente5");
		testAgendaCitas.agregarNuevaCita("Abogado4", "2019/06/24", "07:00", "Cliente9");
		testAgendaCitas.agregarNuevaCita("Abogado2", "2019/06/25", "06:30", "Cliente9");
		testAgendaCitas.agregarNuevaCita("Abogado2", "2019/06/25", "07:30", "Cliente9");
		testAgendaCitas.agregarNuevaCita("Abogado2", "2019/06/25", "07:00", "Cliente9");
		testAgendaCitas.agregarNuevaCita("Abogado4", "2019/06/25", "10:00", "Cliente9");
		testAgendaCitas.agregarNuevaCita("Abogado4", "2019/06/25", "09:30", "Cliente9");
		testAgendaCitas.agregarNuevaCita("Abogado4", "2019/06/26", "06:30", "Cliente9");
		testAgendaCitas.agregarNuevaCita("Abogado4", "2019/06/26", "07:30", "Cliente9");
		testAgendaCitas.agregarNuevaCita("Abogado4", "2019/06/27", "08:00", "Cliente9");
		testAgendaCitas.agregarNuevaCita("Abogado4", "2019/06/27", "10:00", "Cliente9");
		testAgendaCitas.agregarNuevaCita("Abogado4", "2019/06/27", "09:30", "Cliente9");
		
		testConjunto.inicializar();
		testConjunto = testAlg.masCitas(testAgendaCitas, "2019/06/22", "2019/06/27");
		MostrarConjunto(testConjunto);		
		System.out.println("Fin Test masCitas");
		
		
		testAgendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		testAgendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "09:00", "un cliente");
		testAgendaCitas.agregarNuevaCita("un abogado", "2019/01/01", "10:30", "un cliente");
		testAgendaCitas.agregarNuevoDia("otro abogado", "martes", "2019/01/01");
		testAgendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "09:00", "otro cliente");
		testAgendaCitas.agregarNuevaCita("otro abogado", "2019/01/01", "10:30", "un cliente");
		testAgendaCitas.agregarNuevoDia("un abogado", "lunes", "2018/11/30");
		testAgendaCitas.agregarNuevaCita("un abogado", "2018/11/30", "11:30", "un cliente");
		testAgendaCitas.agregarNuevaCita("un abogado", "2018/11/30", "10:30", "otro cliente");
		testAgendaCitas.agregarNuevoDia("un abogado", "martes", "2018/12/28");
		testAgendaCitas.agregarNuevaCita("un abogado", "2018/12/28", "09:00", "un cliente");
		testAgendaCitas.agregarNuevaCita("un abogado", "2018/12/28", "10:30", "un cliente");
		testAgendaCitas.agregarNuevoDia("un abogado", "martes", "2019/02/13");
		testAgendaCitas.agregarNuevaCita("un abogado", "2019/02/13", "09:00", "un cliente");
		testAgendaCitas.agregarNuevaCita("un abogado", "2019/02/13", "10:30", "un cliente");
		testAgendaCitas.agregarNuevoDia("un abogado", "martes", "2019/01/02");
		testAgendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "11:30", "un cliente");
		testAgendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "14:30", "un cliente");
		testAgendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "08:00", "un cliente");
		testAgendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "08:30", "un cliente");
		testAgendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "22:00", "un cliente");
		testAgendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "17:30", "un cliente");
		testAgendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "14:0", "un cliente");
		testAgendaCitas.agregarNuevaCita("un abogado", "2019/01/02", "10:30", "un cliente");

		// Operaci�n
		//String[][] reuniones = testAlg.conQuienSeReunio(testAgendaCitas, "un cliente");
		
	}

	private static void MostrarCola(ColaTDA cola) {
		ColaTDA aux = new ColaEstatica();
		aux.inicilizar();
		while (!cola.colaVacia()) {
			System.out.println(cola.primero());
			aux.acolar(cola.primero());
			cola.desacolar();
		}
		while(!aux.colaVacia()) {
			cola.acolar(aux.primero());
			aux.desacolar();
		}

	}

	private static void MostrarColaPrioridad(ColaPrioridadTDA cola) {
		ColaPrioridadTDA aux = cola;
		
		while (!aux.colaVacia()) {
			System.out.println(aux.primero() + " | " + aux.prioridad());
			aux.desacolar();
		}
	}

	private static void MostrarConjunto(ConjuntoTDA conjunto) {
		ConjuntoTDA aux = conjunto;
		String valor;
		
		while(!aux.conjuntoVacio()) {
			valor = aux.elegir();
			aux.sacar(valor);
			System.out.println(valor);
		}
	}
	
	private static void mostrarCitas(String citas[][]) {
		int i = 1;
		for(String cita[] : citas) {

				System.out.println("Registro " + i++);
				System.out.println("Dia: " + cita[0]);
				System.out.println("Hora: " + cita[1]);
				System.out.println("Cliente: " + cita[2]);				
			}
		
	}
}
