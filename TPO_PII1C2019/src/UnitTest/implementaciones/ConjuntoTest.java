package UnitTest.implementaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import implementaciones.ConjuntoEstatico;

public class ConjuntoTest {

	ConjuntoEstatico conjunto;
	
	@Before
	public void setUp() throws Exception {
		conjunto = new ConjuntoEstatico();
		conjunto.inicializar();
	}

	@Test
	public void testConjuntoVacio() {
		// Operaci�n
		boolean elConjuntoEstaVacio = conjunto.conjuntoVacio();
		
		// Validaci�n
		Assert.assertTrue(elConjuntoEstaVacio);
	}
	
	@Test
	public void testConjuntoNoVacio() {
		// Operaci�n
		conjunto.agregar("un valor");
		
		// Validaci�n
		Assert.assertFalse(conjunto.conjuntoVacio());
	}

	@Test
	public void testAgregar() {
		// Operaci�n
		conjunto.agregar("un valor");
		
		// Validaci�n
		Assert.assertEquals("un valor", conjunto.elegir());
	}

	@Test
	public void testElegirYSacar() {
		// Inicializaci�n
		conjunto.agregar("un valor");
		conjunto.agregar("otro valor");
		conjunto.agregar("un ultimo valor");
		List<String> valoresEsperados = Arrays.asList("un valor", "otro valor", "un ultimo valor");
		List<String> valoresDelConjunto = new ArrayList<String>();
		
		// Operaci�n
		while(!conjunto.conjuntoVacio()) {
			String valor = conjunto.elegir();
			conjunto.sacar(valor);
			valoresDelConjunto.add(valor);
		}
		
		// Validaci�n
		Assert.assertTrue(valoresEsperados.size() == valoresDelConjunto.size() && 
				valoresEsperados.containsAll(valoresDelConjunto) && valoresDelConjunto.containsAll(valoresEsperados));
		
	}

	@Test
	public void testPertenece() {
		// Inicializaci�n
		conjunto.agregar("un valor");
		conjunto.agregar("otro valor");
		conjunto.agregar("un ultimo valor");
		
		// Operaci�n
		boolean unValorPertenece = conjunto.pertenece("un valor");
		boolean otroValorPertenece = conjunto.pertenece("otro valor");
		boolean unUltimoValorPertenece = conjunto.pertenece("un ultimo valor");
		
		// Validaci�n
		Assert.assertTrue(unValorPertenece);
		Assert.assertTrue(otroValorPertenece);
		Assert.assertTrue(unUltimoValorPertenece);
	}
	
	@Test
	public void testNoPerteneceEnConjuntoVacio() {		
		// Operaci�n
		boolean unValorEnUnConjuntoVacioPertenece = conjunto.pertenece("un valor en un conjunto vacio");
		
		// Validaci�n
		Assert.assertFalse(unValorEnUnConjuntoVacioPertenece);
	}
	
	@Test
	public void testNoPerteneceEnConjuntoNoVacio() {
		// Inicializaci�n
		conjunto.agregar("un valor");
		conjunto.agregar("otro valor");
		conjunto.agregar("un ultimo valor");
		
		// Operaci�n
		boolean unValorQueNoExistePertenece = conjunto.pertenece("un valor que no existe");
		
		// Validaci�n
		Assert.assertFalse(unValorQueNoExistePertenece);
	}

}
