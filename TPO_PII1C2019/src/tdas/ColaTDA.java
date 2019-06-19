package tdas;

/**
 * 
 * Similar a la Cola de enteros pero ahora con elementos cadena de caracteres.
 * 
 * */
public interface ColaTDA {

	/**
	 * Si precondiciones
	 * */
	public void inicilizar();
	/**
	 * Inicializada
	 * */
	public void acolar(String valor);
	/**
	 * inicializada y no vac�a
	 * */
	public void desacolar();
	/**
	 * inicializada y no vac�a
	 * */
	public String primero();
	/**
	 * inicializada
	 * */
	public boolean colaVacia();
}
