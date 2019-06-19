package implementaciones;

import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class AgendaCitas implements tdas.AgendaCitasTDA {

	NodoAgenda agenda;
	NodoDia fec;
	
	@Override
	public void inicializar() {
		agenda = null;
		fec = null;
	}

	@Override
	public void agregarNuevoDia(String abogado, String dia, String fecha) {
		ConjuntoTDA abogados = new ConjuntoDinamico();
		ConjuntoTDA fechas = new ConjuntoDinamico();
		
		abogados.inicializar();
		fechas.inicializar();
		
		abogados = abogados();
		fechas = fechas(abogado);
		
		//Si no existe el abogado creo el nuevo nodo Agenda con los parametros.
		if(!abogados.pertenece(abogado)){
			NodoAgenda auxAgenda = new NodoAgenda();
			auxAgenda.abogado = abogado;
			fec.dia = dia;
			fec.fecha = fecha;
			fec.siguienteFecha = null;
			fec.turnos = null;
			auxAgenda.primeraFecha = fec;
			auxAgenda.sigAbogado = agenda;
			agenda = auxAgenda;
		}
		//si existe el abogado
		else{
			//si no existe la fecha para ese abogado
			if(!fechas.pertenece(fecha)) {
				NodoDia auxFecha = new NodoDia();
				auxFecha.dia = dia;
				auxFecha.fecha = fecha;
				auxFecha.siguienteFecha = agenda.primeraFecha;
				auxFecha.turnos = null;
				agenda.primeraFecha = auxFecha;
			}
		}
	}

	@Override
	public void agregarNuevaCita(String abogado, String fecha, String hora, String cliente) {
		NodoAgenda auxAgenda = agenda;
		NodoDia auxFecha = agenda.primeraFecha;
		while(auxAgenda != null) {
			if(auxAgenda.abogado.equalsIgnoreCase(abogado)) {
				while(auxFecha != null) {
					if(auxFecha.fecha.equalsIgnoreCase(fecha)) {
						auxFecha.turnos.agregar(hora, cliente);
					}
					auxFecha = auxFecha.siguienteFecha;
				}
			}
			auxAgenda = auxAgenda.sigAbogado;
		}
	}

	@Override
	public void eliminarAbogado(String abogado) {
		ConjuntoTDA abogados = new ConjuntoDinamico();
		
		abogados.inicializar();
		abogados = abogados();
		if(abogados.pertenece(abogado)) {
			
			if(agenda.abogado.equalsIgnoreCase(abogado))
				agenda = agenda.sigAbogado;
			
			NodoAgenda auxAgenda = agenda;
			while(auxAgenda != null) {
				if(auxAgenda.sigAbogado != null && auxAgenda.sigAbogado.abogado.equalsIgnoreCase(abogado)) {
					auxAgenda.sigAbogado = auxAgenda.sigAbogado.sigAbogado;
				}
				auxAgenda = auxAgenda.sigAbogado;
			}
		}
	}

	@Override
	public void eliminarFecha(String abogado, String fecha) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarCita(String abogado, String fecha, String hora, String cliente) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existeCita(String abogado, String fecha, String hora) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String clienteEnCita(String abogado, String fecha, String hora) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConjuntoTDA abogados() {
		ConjuntoTDA aux = new ConjuntoDinamico();
		NodoAgenda auxAgenda = agenda;
		aux.inicializar();
		while(auxAgenda != null) {
			aux.agregar(auxAgenda.abogado);
			auxAgenda = auxAgenda.sigAbogado;
		}
		return aux;
	}
	
	@Override
	public ConjuntoTDA fechas(String abogado) {
		ConjuntoTDA aux = new ConjuntoDinamico();
		NodoAgenda auxAgenda = agenda;
		aux.inicializar();
		while(auxAgenda != null) {
			if(auxAgenda.abogado.equalsIgnoreCase(abogado) && auxAgenda.primeraFecha != null) {
				aux.agregar(auxAgenda.primeraFecha.fecha);
				auxAgenda.primeraFecha = auxAgenda.primeraFecha.siguienteFecha;
				while(auxAgenda.primeraFecha != null) {
					aux.agregar(auxAgenda.primeraFecha.fecha);
					auxAgenda.primeraFecha = auxAgenda.primeraFecha.siguienteFecha;
				}
			}
			auxAgenda = auxAgenda.sigAbogado;
		}
		return aux;
	}

	@Override
	public ColaTDA turnos(String abogado, String fecha) {
		// TODO Auto-generated method stub
		return null;
	}

}
