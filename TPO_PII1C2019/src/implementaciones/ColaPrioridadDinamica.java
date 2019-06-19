package implementaciones;

public class ColaPrioridadDinamica implements tdas.ColaPrioridadTDA {

	NodoColaPrioridad mayorPrioridad;
	
	@Override
	public void inicializar() {
		mayorPrioridad = null;
	}

	@Override
	public void acolar(String valor, String prioridad) {
		NodoColaPrioridad nuevo = new NodoColaPrioridad();
		nuevo.info = valor;
		nuevo.prioridad = prioridad;
		
		if(mayorPrioridad == null || prioridad.compareToIgnoreCase(mayorPrioridad.prioridad) > 0) {
			nuevo.sig = mayorPrioridad;
			mayorPrioridad = nuevo;
		}
		else {
			NodoColaPrioridad aux = mayorPrioridad;
			while(aux.sig != null && aux.sig.prioridad.compareToIgnoreCase(prioridad) >= 0) {
				aux = aux.sig;
			}
			nuevo.sig = aux.sig;
			aux.sig = nuevo;
		}
	}

	@Override
	public void dasacolar() {
		mayorPrioridad = mayorPrioridad.sig;
	}

	@Override
	public String primero() {
		return mayorPrioridad.info;				
	}

	@Override
	public String prioridad() {
		return mayorPrioridad.prioridad;
	}

	@Override
	public boolean colaVacia() {
		return (mayorPrioridad == null);
	}

}
