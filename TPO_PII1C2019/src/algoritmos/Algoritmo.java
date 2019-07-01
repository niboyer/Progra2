package algoritmos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import implementaciones.ColaEstatica;
import implementaciones.ColaPrioridadEstatica;
import implementaciones.ConjuntoEstatico;
import tdas.AgendaCitasTDA;
import tdas.ColaPrioridadTDA;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class Algoritmo implements IAlgoritmo {

	@Override
	public boolean disponible(AgendaCitasTDA agenda, String abogado, String fecha, String hora) {
		ConjuntoTDA abogados = new ConjuntoEstatico();
		ConjuntoTDA fechas = new ConjuntoEstatico();
		AgendaCitasTDA auxAgenda;

		if (agenda == null)
			return false;

		abogados.inicializar();
		fechas.inicializar();
		auxAgenda = agenda;

		abogados = auxAgenda.abogados();
		if (abogados.pertenece(abogado)) {
			fechas = auxAgenda.fechas(abogado);
			if (fechas.pertenece(fecha)) {
				return !auxAgenda.existeCita(abogado, fecha, hora);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public ConjuntoTDA masCitas(AgendaCitasTDA agenda, String fechaDesde, String fechaHasta) {
		ConjuntoTDA abogados = new ConjuntoEstatico();
		ConjuntoTDA resultado = new ConjuntoEstatico();
		AgendaCitasTDA auxAgenda;
		String auxAbogadoValor;
		String auxFecha;
		String auxHoraCita;
		int cantCitas = 0;

		abogados.inicializar();
		resultado.inicializar();
		auxAgenda = agenda;

		if (agenda == null)
			return resultado;

		abogados = auxAgenda.abogados();

		while (!abogados.conjuntoVacio()) {
			auxAbogadoValor = abogados.elegir();
			int cantCitasAux = 0;
			auxFecha = fechaDesde;
			while (fechaHasta.compareToIgnoreCase(auxFecha) >= 0) {
				auxHoraCita = "00:00";
				for (int j = 0; j < 48; j++) {
					if (auxAgenda.existeCita(auxAbogadoValor, auxFecha, auxHoraCita)) {
						cantCitasAux++;
					}
					auxHoraCita = sumaHora(auxHoraCita);
				}
				auxFecha = sumaDiasFecha(auxFecha, 1);
			}
			if (cantCitasAux > cantCitas && cantCitasAux != 0) {
				resultado.inicializar();
				resultado.agregar(auxAbogadoValor);
				cantCitas = cantCitasAux;
			} else if (cantCitasAux == cantCitas && cantCitasAux != 0) {
				resultado.agregar(auxAbogadoValor);
			}
			abogados.sacar(auxAbogadoValor);
		}
		return resultado;
	}

	@Override
	public String abogadoUltimaVez(AgendaCitasTDA agenda, String cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] obtenerCitas(AgendaCitasTDA agenda, String abogado, String fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] conQuienSeReunio(AgendaCitasTDA agenda, String cliente) {
		String auxAbogadoValor;
		String auxFechaValor;
		String auxCliente;
		String[][] auxResultado = new String[0][3];
		ConjuntoTDA abogados = new ConjuntoEstatico();
		ConjuntoTDA fechas = new ConjuntoEstatico();
		ColaTDA citas = new ColaEstatica();
		AgendaCitasTDA auxAgenda;

		abogados.inicializar();
		auxAgenda = agenda;

		if (auxAgenda == null)
			return new String[][] { {} };

		abogados = auxAgenda.abogados();
		while (!abogados.conjuntoVacio()) {
			fechas.inicializar();
			auxAbogadoValor = abogados.elegir();
			fechas = auxAgenda.fechas(auxAbogadoValor);
			while (!fechas.conjuntoVacio()) {
				auxFechaValor = fechas.elegir();
				citas.inicilizar();
				citas = auxAgenda.turnos(auxAbogadoValor, auxFechaValor);
				while (!citas.colaVacia()) {
					auxCliente = auxAgenda.clienteEnCita(auxAbogadoValor, auxFechaValor, citas.primero());
					if (auxCliente.equalsIgnoreCase(cliente)) {
						String[] nuevaLinea = new String[] { auxAbogadoValor, auxFechaValor, citas.primero() };
						auxResultado = agregarLinea(auxResultado, nuevaLinea, auxResultado.length);
					}
					citas.desacolar();
				}
				
				fechas.sacar(auxFechaValor);
			}
			abogados.sacar(auxAbogadoValor);
		}
		
		ordenarPorFechaYHora(auxResultado);

		return auxResultado;
	}

	@Override
	public ColaPrioridadTDA libresTotal(AgendaCitasTDA agenda, String fecha) {
		ColaPrioridadTDA resultado = new ColaPrioridadEstatica();
		ConjuntoTDA abogados = new ConjuntoEstatico();
		ConjuntoTDA fechas = new ConjuntoEstatico();
		AgendaCitasTDA auxAgenda;
		String auxFecha;
		String auxAbogadoValor;
		String auxHoraCita;

		resultado.inicializar();
		abogados.inicializar();

		auxAgenda = agenda;

		if (auxAgenda == null)
			return resultado;

		abogados = auxAgenda.abogados();
		while (!abogados.conjuntoVacio()) {
			fechas.inicializar();
			auxAbogadoValor = abogados.elegir();
			fechas = auxAgenda.fechas(auxAbogadoValor);
			if (!fechas.conjuntoVacio()) {
				for (int i = 0; i < 5; i++) {
					auxFecha = sumaDiasFecha(fecha, i);
					if (fechas.pertenece(auxFecha)) {
						auxHoraCita = "00:00";
						for (int j = 0; j < 48; j++) {
							if (!auxAgenda.existeCita(auxAbogadoValor, auxFecha, auxHoraCita)) {
								resultado.acolar(auxAbogadoValor, auxHoraCita);
							}
							auxHoraCita = sumaHora(auxHoraCita);
						}
					}
				}
			}
			abogados.sacar(auxAbogadoValor);
		}
		return resultado;
	}

	private String sumaDiasFecha(String fecha, int dias) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar calendar = Calendar.getInstance();

			calendar.setTime(sdf.parse(fecha));
			calendar.add(Calendar.DAY_OF_YEAR, dias);

			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			return e.getMessage();
		}
	}

	private String sumaHora(String hora) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			Calendar calendar = Calendar.getInstance();

			calendar.setTime(sdf.parse(hora));
			calendar.add(Calendar.MINUTE, 30);

			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			return e.getMessage();
		}
	}
	
	private void ordenarPorFechaYHora(String[][] origen) {
		String[] aux;
		String fecha;
		String fechaSig;
		String hora;
		String horaSig;
		
		for(int linea = 1; linea <= origen.length; linea++) {
			for(int lineaSig = 0; lineaSig < origen.length - linea; lineaSig++) {
				fecha = origen[lineaSig][1];
				fechaSig = origen[lineaSig + 1][1];
				hora = origen[lineaSig][2];
				horaSig = origen[lineaSig + 1][2];
				if(esFechaMenor(fecha,fechaSig)) {
					aux = origen[lineaSig];
					origen[lineaSig] = origen[lineaSig + 1];
					origen[lineaSig + 1] = aux;
				}
				else if(fecha.equalsIgnoreCase(fechaSig) && esHoraMenor(hora, horaSig)){
					aux = origen[lineaSig];
					origen[lineaSig] = origen[lineaSig + 1];
					origen[lineaSig + 1] = aux;
				}
			}
		}
	}
	
	//Si fechaSig es fecha menor que fechaMenor devuelvo true si es mayor false;
	private boolean esFechaMenor(String fechaMenor, String fechaSig) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar calendarMenor = Calendar.getInstance();
			Calendar calendarSig = Calendar.getInstance();

			calendarMenor.setTime(sdf.parse(fechaMenor));
			calendarSig.setTime(sdf.parse(fechaSig));

			return calendarSig.before(calendarMenor);

		} catch (ParseException e) {
			return false;
		}
	}
	
	private boolean esHoraMenor(String horaMenor, String horaSig) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			Calendar calendarMenor = Calendar.getInstance();
			Calendar calendarSig = Calendar.getInstance();

			calendarMenor.setTime(sdf.parse(horaMenor));
			calendarSig.setTime(sdf.parse(horaSig));

			return calendarSig.before(calendarMenor);

		} catch (ParseException e) {
			return false;
		}
	}
	
	private String[][] agregarLinea(String origen[][],String[] linea, int tamAnterior){
		int i;
		String[][] result = new String[tamAnterior+1][3];
		for(i = 0; i < tamAnterior; i++) {
			result[i] = origen[i];
		}
		if(i == tamAnterior)
			result[i] = linea;
		return result;
	}
}
