package implementaciones;

import tdas.ArbolCitasTDA;
import tdas.ColaPrioridadTDA;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class AgendaCitas implements tdas.AgendaCitasTDA {

	NodoAgenda agenda;

	@Override
	public void inicializar() {
		agenda = null;
	}

	@Override
	public void agregarNuevoDia(String abogado, String dia, String fecha) {
		if (agenda == null) {
			AgregarAbogado(abogado, dia, fecha);
		} else {
			NodoAgenda auxAgenda = BuscarNodoAbogado(abogado);
			if (auxAgenda == null) {
				AgregarAbogado(abogado, dia, fecha);
			} else {
				NodoDia auxFecha = BuscarNodoFecha(auxAgenda.primeraFecha, fecha);
				if (auxFecha == null) {
					AgregarFecha(auxAgenda.primeraFecha, abogado, dia, fecha);
				}
			}
		}
	}

	@Override
	public void agregarNuevaCita(String abogado, String fecha, String hora, String cliente) {
		NodoAgenda auxAgenda = BuscarNodoAbogado(abogado);
		NodoDia auxFecha = BuscarNodoFecha(auxAgenda.primeraFecha, fecha);
		if (auxFecha.turnos == null) {
			ArbolCitasTDA nuevoArbol = new ArbolCitas();
			nuevoArbol.inicializar();
			nuevoArbol.agregar(hora, cliente);
			auxFecha.turnos = nuevoArbol;
		} else {
			auxFecha.turnos.agregar(hora, cliente);
		}
	}

	@Override
	public void eliminarAbogado(String abogado) {
		if (agenda.abogado.equalsIgnoreCase(abogado))
			agenda = agenda.sigAbogado;

		NodoAgenda auxAgenda = agenda;
		while (auxAgenda != null) {
			if (auxAgenda.sigAbogado != null && auxAgenda.sigAbogado.abogado.equalsIgnoreCase(abogado))
				auxAgenda.sigAbogado = auxAgenda.sigAbogado.sigAbogado;
			auxAgenda = auxAgenda.sigAbogado;
		}
	}

	@Override
	public void eliminarFecha(String abogado, String fecha) {
		NodoAgenda auxAgenda = BuscarNodoAbogado(abogado);
		if (auxAgenda.primeraFecha.fecha.equalsIgnoreCase(fecha))
			auxAgenda.primeraFecha = auxAgenda.primeraFecha.siguienteFecha;

		NodoDia auxFecha = auxAgenda.primeraFecha;
		while (auxFecha != null) {
			if (auxFecha.siguienteFecha != null && auxFecha.siguienteFecha.fecha.equalsIgnoreCase(fecha))
				auxFecha.siguienteFecha = auxFecha.siguienteFecha.siguienteFecha;
			auxFecha = auxFecha.siguienteFecha;
		}
		
		if(auxAgenda.primeraFecha == null)
			eliminarAbogado(abogado);
	}

	@Override
	public void eliminarCita(String abogado, String fecha, String hora, String cliente) {
		NodoAgenda auxAgenda = BuscarNodoAbogado(abogado);
		if (auxAgenda != null) {
			NodoDia auxFecha = BuscarNodoFecha(auxAgenda.primeraFecha, fecha);
			if (auxFecha != null && auxFecha.turnos != null) {
				auxFecha.turnos.eliminar(hora, cliente);
			}
		}
	}

	@Override
	public boolean existeCita(String abogado, String fecha, String hora) {
		NodoAgenda auxAgenda = BuscarNodoAbogado(abogado);
		if (auxAgenda != null) {
			NodoDia auxFecha = BuscarNodoFecha(auxAgenda.primeraFecha, fecha);
			if (auxFecha != null && auxFecha.turnos != null && BuscarCita(auxFecha.turnos, hora))
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public String clienteEnCita(String abogado, String fecha, String hora) {
		NodoAgenda auxAgenda = BuscarNodoAbogado(abogado);
		if (auxAgenda != null) {
			NodoDia auxFecha = BuscarNodoFecha(auxAgenda.primeraFecha, fecha);
			if (auxFecha != null && auxFecha.turnos != null && BuscarCita(auxFecha.turnos, hora))
				return BuscarCliente(auxFecha.turnos, hora);
			else
				return null;
		} else
			return null;
	}

	@Override
	public ConjuntoTDA abogados() {
		ConjuntoTDA aux = new ConjuntoEstatico();
		aux.inicializar();

		if (agenda == null)
			return aux;

		NodoAgenda auxAgenda = agenda;

		while (auxAgenda != null) {
			aux.agregar(auxAgenda.abogado);
			auxAgenda = auxAgenda.sigAbogado;
		}
		return aux;
	}

	@Override
	public ConjuntoTDA fechas(String abogado) {
		ConjuntoTDA aux = new ConjuntoEstatico();
		aux.inicializar();

		if (agenda == null)
			return aux;

		NodoAgenda auxAgenda = agenda;

		while (auxAgenda != null) {
			if (auxAgenda.abogado.equalsIgnoreCase(abogado)) {
				aux.agregar(auxAgenda.primeraFecha.fecha);
				NodoDia auxFecha = auxAgenda.primeraFecha;
				while (auxFecha != null) {
					aux.agregar(auxFecha.fecha);
					auxFecha = auxFecha.siguienteFecha;
				}
			}
			auxAgenda = auxAgenda.sigAbogado;
		}
		return aux;
	}

	@Override
	public ColaTDA turnos(String abogado, String fecha) {
		NodoAgenda auxAgenda = BuscarNodoAbogado(abogado);
		ColaTDA resultado = new ColaEstatica();
		resultado.inicilizar();
		if(auxAgenda != null) {
			NodoDia auxFecha = BuscarNodoFecha(auxAgenda.primeraFecha, fecha);
			if(auxFecha != null && auxFecha.turnos != null) {
				resultado = ObtenerTurnos(auxFecha.turnos);
			}
		}
		return resultado;
	}

	private NodoAgenda BuscarNodoAbogado(String abogado) {
		NodoAgenda aux = agenda;
		while (aux != null && !aux.abogado.equalsIgnoreCase(abogado)) {
			aux = aux.sigAbogado;
		}
		return aux;
	}

	private NodoDia BuscarNodoFecha(NodoDia auxFecha, String fecha) {
		NodoDia aux = auxFecha;
		while (aux != null && !aux.fecha.equalsIgnoreCase(fecha)) {
			aux = aux.siguienteFecha;
		}
		return aux;
	}

	private void AgregarAbogado(String abogado, String dia, String fecha) {
		if (agenda == null) {
			agenda = new NodoAgenda();
			agenda.abogado = abogado;
			agenda.primeraFecha = new NodoDia();
			agenda.primeraFecha.dia = dia;
			agenda.primeraFecha.fecha = fecha;
			agenda.primeraFecha.siguienteFecha = null;
			agenda.primeraFecha.turnos = null;
			agenda.sigAbogado = null;
		} else {
			NodoAgenda auxAgenda = agenda;
			while (auxAgenda.sigAbogado != null)
				auxAgenda = auxAgenda.sigAbogado;
			NodoAgenda nuevaAgenda = new NodoAgenda();
			nuevaAgenda.abogado = abogado;
			nuevaAgenda.primeraFecha = new NodoDia();
			nuevaAgenda.primeraFecha.dia = dia;
			nuevaAgenda.primeraFecha.fecha = fecha;
			nuevaAgenda.primeraFecha.siguienteFecha = null;
			nuevaAgenda.primeraFecha.turnos = null;
			nuevaAgenda.sigAbogado = null;
			auxAgenda.sigAbogado = nuevaAgenda;
		}
	}

	private void AgregarFecha(NodoDia auxFecha, String abogado, String dia, String fecha) {
		if (auxFecha == null) {
			auxFecha = new NodoDia();
			auxFecha.dia = dia;
			auxFecha.fecha = fecha;
			auxFecha.siguienteFecha = null;
			auxFecha.turnos = null;
		} else {
			NodoDia auxFec = auxFecha;
			while (auxFec.siguienteFecha != null)
				auxFec = auxFec.siguienteFecha;
			NodoDia nuevaFecha = new NodoDia();
			nuevaFecha.dia = dia;
			nuevaFecha.fecha = fecha;
			nuevaFecha.siguienteFecha = null;
			nuevaFecha.turnos = null;
			auxFec.siguienteFecha = nuevaFecha;
		}
	}

	private boolean BuscarCita(ArbolCitasTDA turnos, String hora) {
		if (!turnos.arbolVacio()) {
			if (hora.compareToIgnoreCase(turnos.hora()) < 0)
				return BuscarCita(turnos.hijoIzquierdo(), hora);
			else if (hora.compareToIgnoreCase(turnos.hora()) > 0)
				return BuscarCita(turnos.hijoDerecho(), hora);
			else
				return true;
		} else
			return false;
	}
	
	private String BuscarCliente(ArbolCitasTDA turnos, String hora) {
		if (!turnos.arbolVacio()) {
			if (hora.compareToIgnoreCase(turnos.hora()) < 0)
				return BuscarCliente(turnos.hijoIzquierdo(), hora);
			else if (hora.compareToIgnoreCase(turnos.hora()) > 0)
				return BuscarCliente(turnos.hijoDerecho(), hora);
			else
				return turnos.cliente();
		} else
			return null;
	}
	
	private ColaTDA ObtenerTurnos(ArbolCitasTDA turnos) {
		ColaTDA resultado = new ColaEstatica();
		ColaTDA auxI, auxD;
		resultado.inicilizar();
		if(!turnos.arbolVacio()) {
			auxI = ObtenerTurnos(turnos.hijoIzquierdo());
			resultado.acolar(turnos.hora());
			auxD = ObtenerTurnos(turnos.hijoDerecho());
			resultado = ConstituirTurnos(resultado,auxI,auxD);
		}
		return resultado;
	}
	
	private ColaTDA ConstituirTurnos(ColaTDA r, ColaTDA i, ColaTDA d) {
		while (!i.colaVacia()) {
			r.acolar(i.primero());
			i.desacolar();
		}
		while (!d.colaVacia()) {
			r.acolar(d.primero());
			d.desacolar();
		}
		r = OrdenarColaMenorMayor(r);
		return r;
	}
	
	private ColaTDA OrdenarColaMenorMayor(ColaTDA orig) {
		ColaPrioridadTDA aux = new ColaPrioridadEstatica();
		aux.inicializar();
		while (!orig.colaVacia()) {
			aux.acolar(orig.primero(), orig.primero());
			orig.desacolar();
		}
		while (!aux.colaVacia()) {
			orig.acolar(aux.primero());
			aux.desacolar();
		}
		return orig;
	}
}
