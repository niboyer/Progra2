package tdas;

public interface AgendaCitasTDA {
	
	/**
	 * 
	 * Sin precondiciones ni par�metros.
	 * 
	 * */
	public void inicializar();
	
	/**
	 *  Agrega un nuevo d�a para agendar citas a sus clientes.
	 *  	Si no existe el abogado lo crea y asigna la nueva fecha. 
	 *  	Si existe el abogado y no la fecha, crea la nueva fecha
	 *  	Si existe el abogado y la fecha, no hace nada.
	 * 
	 *  Precondici�n:
	 *  	inicializada.
	 *  Par�metros: 
	 * 		@param abogado  
	 * 		@param dia cadena de caracteres con los nombre de los d�as (lunes, martes, etc.)
	 * 		@param fecha cadena de caracteres con formato AAAA/MM/DD (a�o, mes, d�a) 
	 * 
	 * */
	public void agregarNuevoDia(String abogado, String dia, String fecha);
	
	/**
	 *  Agrega una nueva cita a un abogado para un cliente determinado, en una fecha y hora determinada. 
	 * 
	 * Precondici�n
	 * 		inicializada, existe el abogado y la fecha pero no existe una cita en esa fecha y horario.
	 * 
	 * Par�metros: 
	 * 		String abogado 
	 * 		String fecha
	 * 		String hora
	 * 		String cliente
	 * */
	public void agregarNuevaCita(String abogado, String fecha, String hora, String cliente);
	
	/**
	 *  Elimina un abogado y a toda su agenda de citas. Si el abogado no existe no hace nada.
	 * 
	 * Precondici�n: 
	 * 		inicializada
	 * 
	 * Par�metros: 
	 * 		String abogado 
	 * */	
	public void eliminarAbogado(String abogado);
	
	/**
	 *  Elimina un abogado y a toda su agenda de citas para una fecha determinada. 
	 *  Si el abogado no existe no hace nada.
	 * 
	 * Precondici�n: 
	 * 		inicializada
	 * 
	 * Par�metros: 
	 * 		String abogado
	 * 		String fecha 
	 * */	
	public void eliminarFecha(String abogado, String fecha);
	
	/**
	 * Elimina una cita determinada. Si no concuerda la fecha, la hora y el cliente no se borrar� la cita.
	 * 
	 * Precondici�n:
	 * 		inicializada y existe el turno para ese paciente.
	 * 
	 * Par�metros: 
	 * 		String abogado 
	 * 		String fecha
	 * 		String hora
	 * 		String cliente
	 * */	
	public void eliminarCita(String abogado, String fecha, String hora, String cliente);
	
	/**
	 * Devuelve el verdadero si existe una cita para ese abogado, en esa fecha y en ese horario. 
	 * Si no existe devuelve falso.
	 * 
	 * Precondic�n:
	 * 		inicializada
	 * 
	 * Par�metros: 
	 * 		@param abogado 
	 * 		@param fecha
	 * 		@param hora
	 * */	
	public boolean existeCita(String abogado, String fecha, String hora);
	
	/**
	 * Devuelve el nombre del cliente que posee la cita.
	 * 
	 * Precondici�n:
	 * 		inicializada y existe la cita
	 * 
	 * Par�metros: 
	 * 		@param abogado 
	 * 		@param fecha
	 * 		@param hora
	 * 		@return nombre del cliente de la cita
	 * */
	public String clienteEnCita(String abogado, String fecha, String hora);
	
	/**
	 * Devuelve una Conjunto con todos los abogados existentes en la agenda. Si no existen abogados devuelve un conjunto vac�o.
	 * 
	 * Precondici�n:
	 * 		inicializada
	 * 
	 * Par�metros:
	 * 		@return conjunto de los nombres de todos los abogados existentes en la estructura.
	 * 
	 * */	
	public ConjuntoTDA abogados();
	
	/**
	 * Devuelve una Conjunto con todos las fechas existentes en la agenda del abogado. Si no existen fechas devuelve un conjunto vac�o.
	 * 
	 * Precondici�n:
	 * 		inicializada
	 * 
	 * Par�metros:
	 * 		@return conjunto de todas las fechas de ese abogado existentes en la estructura.
	 * 
	 * */
	public ConjuntoTDA fechas(String abogado);
	
	/**
	 * Devuelve una cola con todos las citas que posee ese abogado en esa fecha ordenado por horario de las citas. 
	 * Si no existe el abogado o la fecha devuelve una cola vac�a.
	 * 
	 * Precondici�n:
	 * 		inicializada
	 * 
	 * Par�metros: 
	 * 		@param abogado 
	 * 		@param fecha
	 * */
	public ColaTDA turnos (String abogado, String fecha);
}
