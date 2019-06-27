package implementaciones;

public class ColaEstatica implements tdas.ColaTDA {

	/*
	 * NodoCola primero; NodoCola ultimo;
	 */

	String[] a;
	int indice;

	@Override
	public void inicilizar() {
		/*
		 * primero = null; ultimo = null;
		 */
		a = new String[100];
		indice = 0;
	}

	@Override
	public void acolar(String valor) {
		/*
		 * NodoCola aux = new NodoCola(); aux.info = valor; aux.sig = null; if(ultimo !=
		 * null) ultimo.sig = aux; ultimo = aux; if(primero == null) primero = ultimo;
		 */
		for (int i = indice - 1; i >= 0; i--)
			a[i + 1] = a[i];
		a[0] = valor;
		indice++;
	}

	@Override
	public void desacolar() {
		/*
		 * primero = primero.sig; if(primero == null) ultimo = null;
		 */
		indice--;
	}

	@Override
	public String primero() {
		/*
		 * return primero.info;
		 */
		return a[indice - 1];
	}

	@Override
	public boolean colaVacia() {
		return (indice == 0);
		/*
		 * return (ultimo == null);
		 */
	}

}
