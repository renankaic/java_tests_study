package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//Essa anotação ordena as ordens do teste 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemTest {
	
	//Variaveis estaticas mantem seu valor nos testes
	public static int contador = 0;

	@Test
	public void inicia() {
		contador = 1;
	}
	
	@Test
	public void verifica() {
		Assert.assertEquals(1, contador);
	}
	
	//1 - Forma para ordenar os testes
	//Ruim!!!
	/*@Test
	public void testGeral() {
		inicia();
		verifica();
	}*/
	
}
