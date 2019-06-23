package TestAgenda;

import implementaciones.ArbolCitas;
import implementaciones.ColaDinamica;
import implementaciones.ColaPrioridadDinamica;
import implementaciones.ConjuntoDinamico;
import tdas.ArbolCitasTDA;
import tdas.ColaPrioridadTDA;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class Main {

	public static void main(String[] args) {
		ColaTDA testCola = new ColaDinamica();
		ColaPrioridadTDA testColaPrioridad = new ColaPrioridadDinamica();
		ConjuntoTDA testConjunto = new ConjuntoDinamico();
		ArbolCitasTDA testArbolCitas = new ArbolCitas();

		testCola.inicilizar();
		testColaPrioridad.inicializar();
		testConjunto.inicializar();
		testArbolCitas.inicializar();

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
		
		testArbolCitas.agregar("08:00", "Juan Alberto Zaragosa");
		testArbolCitas.agregar("09:00", "Sebastian");
		testArbolCitas.agregar("07:00", "Pablo");
		testArbolCitas.agregar("07:30", "Joaquin");
		testArbolCitas.agregar("06:30", "Nicolas");
		testArbolCitas.agregar("08:30", "Pedro");
		testArbolCitas.agregar("09:30", "Benito");
		
		testArbolCitas.eliminar("08:00", "Juan Alberto Zaragosa");
		
	}

	private static void MostrarCola(ColaTDA cola) {
		ColaTDA aux = new ColaDinamica();

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
}
