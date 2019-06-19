package implementaciones;

public class ConjuntoDinamico implements tdas.ConjuntoTDA {

	NodoConjunto c;

	@Override
	public void inicializar() {
		c = null;
	}

	@Override
	public boolean conjuntoVacio() {
		return (c == null);
	}

	@Override
	public void agregar(String valor) {
		if(!this.pertenece(valor)){
			NodoConjunto aux = new NodoConjunto();
			aux.info = valor;
			aux.sig = c;
			c = aux;
		}
	}

	@Override
	public String elegir() {
		return c.info;
	}

	@Override
	public void sacar(String valor) {
		if(c != null) {
			if(c.info.equalsIgnoreCase(valor)) {
				c = c.sig;
			}else {
				NodoConjunto aux = c;
				while(aux.sig != null && !aux.sig.info.equalsIgnoreCase(valor)) {
					aux = aux.sig;
					if( aux.sig != null) {
						aux.sig = aux.sig.sig;
					}
				}
			}
		}
	}

	@Override
	public boolean pertenece(String valor) {
		NodoConjunto aux = c;
		while((aux != null) && (!aux.info.equalsIgnoreCase(valor))) {
			aux = aux.sig;
		}
		return (aux != null);
	}
}
