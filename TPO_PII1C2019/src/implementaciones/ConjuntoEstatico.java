package implementaciones;

public class ConjuntoEstatico implements tdas.ConjuntoTDA {

	/*
	 * NodoConjunto c;
	 */

	String[] a;
	int cant;

	@Override
	public void inicializar() {
		/*
		 * c = null;
		 */
		a = new String[100];
		cant = 0;
	}

	@Override
	public boolean conjuntoVacio() {
		/*
		 * return (c == null);
		 */
		return (cant == 0);
	}

	@Override
	public void agregar(String valor) {
		/*
		 * if(!this.pertenece(valor)){ NodoConjunto aux = new NodoConjunto(); aux.info =
		 * valor; aux.sig = c; c = aux; }
		 */
		if (!this.pertenece(valor)) {
			a[cant] = valor;
			cant++;
		}
	}

	@Override
	public String elegir() {
		/*
		 * return c.info;
		 */
		return a[cant - 1];
	}

	@Override
	public void sacar(String valor) {
		/*
		 * if(c != null) { if(c.info.equalsIgnoreCase(valor)) { c = c.sig; }else {
		 * NodoConjunto aux = c; while(aux.sig != null &&
		 * !aux.sig.info.equalsIgnoreCase(valor)) { aux = aux.sig; if( aux.sig != null)
		 * { aux.sig = aux.sig.sig; } } } }
		 */
		int i = 0;
		while (i < cant && !a[i].equalsIgnoreCase(valor))
			i++;
		if (i < cant) {
			a[i] = a[cant - 1];
			cant--;
		}
	}

	@Override
	public boolean pertenece(String valor) {
		/*
		 * NodoConjunto aux = c; while((aux != null) &&
		 * (!aux.info.equalsIgnoreCase(valor))) { aux = aux.sig; } return (aux != null);
		 */
		int i = 0;
		while (i < cant && !a[i].equalsIgnoreCase(valor))
			i++;
		return (i < cant);
	}
}
