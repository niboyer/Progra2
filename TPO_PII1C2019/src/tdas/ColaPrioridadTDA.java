package tdas;

/**
 * 
 * Similar a la Cola con Prioridad de enteros pero ahora con valores y prioridades cadena de caracteres.
 * 
 * */
public interface ColaPrioridadTDA {

	/**
	 * Inicializada
	 * */
	public void inicializar();
	
	/**
	 * Inicializada
	 * */
	public void acolar(String valor, String prioridad);
	
	/**
	 * Inicializada y no vac�a
	 * */
	public void desacolar();
	
	/**
	 * Inicializada y no vac�a
	 * */
	public String primero();
	
	/**
	 * Inicializada y no vac�a
	 * */
	public String prioridad();
	
	/**
	 * Inicializada
	 * */
	public boolean colaVacia();
}
