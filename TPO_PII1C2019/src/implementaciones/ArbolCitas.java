package implementaciones;

public class ArbolCitas implements tdas.ArbolCitasTDA {
	NodoArbol raiz;

	@Override
	public void inicializar() {
		raiz = null;
	}

	@Override
	public void agregar(String hora, String cliente) {
		if(raiz == null) {
			raiz = new NodoArbol();
			raiz.hora = hora;
			raiz.cliente = cliente;
			raiz.hijoIzq = new ArbolCitas();
			raiz.hijoIzq.inicializar();
			raiz.hijoDer = new ArbolCitas();
			raiz.hijoDer.inicializar();
		}
		else if(raiz.hora.compareToIgnoreCase(hora) > 0)
			raiz.hijoDer.agregar(hora, cliente);
		else if(raiz.hora.compareToIgnoreCase(hora) < 0)
			raiz.hijoIzq.agregar(hora, cliente);
	}

	@Override
	public void eliminar(String hora, String cliente) {
		if (raiz != null) {
			if(raiz.hora.equalsIgnoreCase(hora) && raiz.cliente.equalsIgnoreCase(cliente)
					&& raiz.hijoIzq.arbolVacio() && raiz.hijoDer.arbolVacio())
				raiz = null;
			else if (raiz.hora.equalsIgnoreCase(hora) && raiz.cliente.equalsIgnoreCase(cliente) && !raiz.hijoIzq.arbolVacio()) {
				raiz.hora = this.mayor(raiz.hijoIzq);
				raiz.hijoIzq.eliminar(raiz.hora, raiz.cliente);
			}
			else if (raiz.hora.equalsIgnoreCase(hora) && raiz.cliente.equalsIgnoreCase(cliente) && raiz.hijoIzq.arbolVacio()) {
				raiz.hora = this.menor(raiz.hijoDer);
				raiz.hijoDer.eliminar(raiz.hora, raiz.cliente);
			}
			else if(raiz.hora.compareToIgnoreCase(hora) > 0)
				raiz.hijoDer.eliminar(hora, cliente);
			else if(raiz.hora.compareToIgnoreCase(hora) < 0)
				raiz.hijoIzq.eliminar(hora, cliente);
		}
	}

	@Override
	public String hora() {
		return raiz.hora;
	}

	@Override
	public String cliente() {
		return raiz.cliente;
	}

	@Override
	public tdas.ArbolCitasTDA hijoDerecho() {
		return raiz.hijoDer;
	}

	@Override
	public tdas.ArbolCitasTDA hijoIzquierdo() {
		return raiz.hijoIzq;
	}

	@Override
	public boolean arbolVacio() {
		return (raiz == null);
	}
	
	private String mayor(tdas.ArbolCitasTDA a) {
		if(a.hijoDerecho().arbolVacio())
			return a.hora();
		else
			return mayor(a.hijoDerecho());
	}
	
	private String menor(tdas.ArbolCitasTDA a) {
		if(a.hijoIzquierdo().arbolVacio())
			return a.hora();
		else 
			return menor(a.hijoIzquierdo());
	}
}
