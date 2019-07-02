package algoritmos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import implementaciones.ColaPrioridadEstatica;
import implementaciones.ConjuntoEstatico;
import implementaciones.NodoDia;
import tdas.AgendaCitasTDA;
import tdas.ColaPrioridadTDA;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class Algoritmo implements IAlgoritmo {
	
	private final String semana[] = new String[] {"lunes", "martes", "miercoles", "jueves", "viernes"};

	@Override
	public boolean disponible(AgendaCitasTDA agenda, String abogado, String fecha, String hora) {
		AgendaCitasTDA auxAgenda = agenda;
		if (auxAgenda.existeCita(abogado, fecha, hora))
			return false;
		else
			return true;
	}

	@Override
	public ConjuntoTDA masCitas(AgendaCitasTDA agenda, String fechaDesde, String fechaHasta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String abogadoUltimaVez(AgendaCitasTDA agenda, String cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] obtenerCitas(AgendaCitasTDA agenda, String abogado, String fecha) {
		String resultado[][] = new String[][] {};
		String cita;
		int i;
		ColaTDA turnos;
		if (agenda != null) {
			for (i = 0; i < 5; i++) {
				turnos = agenda.turnos(abogado, fecha);
				while (!turnos.colaVacia()) {
					cita = turnos.primero();
					resultado = agregarLinea(
							resultado, 
							new String[] {semana[i], cita, agenda.clienteEnCita(abogado, fecha, cita)}, 
							resultado.length
							);
					turnos.desacolar();
				}
				fecha = sumaDiasFecha(fecha, 1);
			}
		}
		return resultado;
	}

	@Override
	public String[][] conQuienSeReunio(AgendaCitasTDA agenda, String cliente) {
		// TODO Auto-generated method stub
		return null;
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
		
		if(agenda == null)
			return resultado;
		
		abogados = auxAgenda.abogados();
		while(!abogados.conjuntoVacio()) {
			fechas.inicializar();
			auxAbogadoValor = abogados.elegir();
			fechas = auxAgenda.fechas(auxAbogadoValor);
			if(!fechas.conjuntoVacio()) {
				for(int i = 0; i < 5; i++) {
					auxFecha = sumaDiasFecha(fecha,i);
					if(fechas.pertenece(auxFecha)) {
						auxHoraCita = "00:00";
						for(int j = 0; j < 48; j++) {
							if(!auxAgenda.existeCita(auxAbogadoValor, auxFecha, auxHoraCita)) {
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
