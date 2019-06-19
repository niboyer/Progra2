package tdas;

public interface ArbolCitasTDA {

	/**
	 * Inicializa la estructura
	 * 
	 * Sin precondiciones.
	 * 
	 * Importante. Para el desarrollo del TPO vamos a considerar que TODAS las citas se dar�n en hora 
	 * o medias horas. Por Ejemplo 10:00; 12:30; etc.
	 *  
	 * */
	public void inicializar();
	
	/**
	 * Agrega una nueva cita en el arbol de citas de un abogado seleccionado
	 * 
	 * Precondici�n: 
	 * 		incializado y no existe una cita para ese horario 
	 * Par�metros:
	 * 		@param hora cadena de caracteres (con formato "HH:MM")
	 * 		@param cliente cadena de caracteres
	 */
	public void agregar(String hora, String cliente);
	
	/**
	 * Elimina una cita del arbol de citas de un abogado seleccionado
	 * 
	 * Precondici�n:
	 * 		incializado. Si la cita no existe no hace nada. Si la cita existe debe coincidir el cliente
	 * Par�metros:	 
	 * 		@param hora
	 * 		@param cliente
	 */
	public void eliminar(String hora, String cliente);
	
	/**
	 * Devuelve la hora de la cita correspondiente al nodo corriente.
	 * 
	 * Precondici�n:
	 * 		incializado y no es un arbol vac�o
	 * Par�metros:	 
	 * 		@return la hora de la cita del nodo corriente
	 */
	public String hora();
	
	/**
	 * Devuelve el nombre del cliente correspondiente al nodo corriente.
	 * 
	 * Precondici�n:
	 * 		incializado y no es un arbol vac�o
	 * Par�metros:	 
	 * 		@return el nombre del cliente del nodo corriente
	 */
	public String cliente();
	
	/**
	 * Devuelve el subarbol derecho de citas.
	 * 
	 * Precondici�n:
	 * 		incializado y no un arbol vac�o
	 * Par�metros:	 
	 * 		@return el subarbol derecho del nodo corriente
	 */
	public ArbolCitasTDA hijoDerecho();

	/**
	 * Devuelve el subarbol derecho de citas.
	 * 
	 * Precondici�n:
	 * 		incializado y no un arbol vac�o
	 * Par�metros:	 
	 * 		@return el subarbol izquierdo del nodo corriente
	 */
	public ArbolCitasTDA hijoIzquierdo();
	
	/**
	 * Me dice si el arbol es un arbol de citas vac�o.
	 * 
	 * Precondici�n:
	 * 		incializado
	 * Par�metros:	 
	 * 		@return verdadero si el arbol esta vac�o
	 */
	public boolean arbolVacio();
	
}
