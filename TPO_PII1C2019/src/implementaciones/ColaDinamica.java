package implementaciones;

public class ColaDinamica implements tdas.ColaTDA {

	NodoCola primero;
	NodoCola ultimo;
	
	@Override
	public void inicilizar() {
		primero = null;
		ultimo = null;
	}

	@Override
	public void acolar(String valor) {
		NodoCola aux = new NodoCola();
		aux.info = valor;
		aux.sig = null;
		if(ultimo != null)
			ultimo.sig = aux;
		ultimo = aux;
		if(primero == null)
			primero = ultimo;
		
	}

	@Override
	public void desacolar() {
		primero = primero.sig;
		if(primero == null)
			ultimo = null;
	}

	@Override
	public String primero() {
		return primero.info;
	}

	@Override
	public boolean colaVacia() {
		return (ultimo == null);
	}

}
