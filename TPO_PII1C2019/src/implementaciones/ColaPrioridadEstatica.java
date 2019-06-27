package implementaciones;

public class ColaPrioridadEstatica implements tdas.ColaPrioridadTDA {

	/*
	 * NodoColaPrioridad mayorPrioridad;
	 */

	class Elemento {
		String valor;
		String prioridad;
	}

	Elemento[] elementos;
	int indice;

	@Override
	public void inicializar() {
		/*
		 * mayorPrioridad = null;
		 */
		indice = 0;
		elementos = new Elemento[100];
	}

	@Override
	public void acolar(String valor, String prioridad) {
		/*
		 * NodoColaPrioridad nuevo = new NodoColaPrioridad(); nuevo.info = valor;
		 * nuevo.prioridad = prioridad;
		 * 
		 * if (mayorPrioridad == null ||
		 * prioridad.compareToIgnoreCase(mayorPrioridad.prioridad) < 0) { nuevo.sig =
		 * mayorPrioridad; mayorPrioridad = nuevo; } else { NodoColaPrioridad aux =
		 * mayorPrioridad; while (aux.sig != null &&
		 * prioridad.compareToIgnoreCase(aux.sig.prioridad) >= 0) { aux = aux.sig; }
		 * nuevo.sig = aux.sig; aux.sig = nuevo; }
		 */
		int j = indice;
		for (; j > 0 && elementos[j - 1].prioridad.compareToIgnoreCase(prioridad) <= 0; j--) {
			elementos[j] = elementos[j - 1];
		}
		elementos[j] = new Elemento();
		elementos[j].valor = valor;
		elementos[j].prioridad = prioridad;
		indice++;
	}

	@Override
	public void desacolar() {
		/*
		 * mayorPrioridad = mayorPrioridad.sig;
		 */
		elementos[indice - 1] = null;
		indice--;
	}

	@Override
	public String primero() {
		/*
		 * return mayorPrioridad.info;
		 */
		return elementos[indice - 1].valor;
	}

	@Override
	public String prioridad() {
		/*
		 * return mayorPrioridad.prioridad;
		 */
		return elementos[indice - 1].prioridad;
	}

	@Override
	public boolean colaVacia() {
		/*
		 * return (mayorPrioridad == null);
		 */
		return (indice == 0);
	}

}
