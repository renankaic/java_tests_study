package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


public class CalculadoraMockTest {

	@Test
	public void Teste() {
		
		Calculadora calculadora = Mockito.mock(Calculadora.class);
		
		ArgumentCaptor<Integer> argCapt = ArgumentCaptor.forClass(Integer.class);
		Mockito.when(calculadora.somar(argCapt.capture(), argCapt.capture())).thenReturn(5);
		
		Assert.assertEquals(5, calculadora.somar(1,  100000));
		
		//Só se pode chamar depois do assert
		System.out.println(argCapt.getValue());
		
		//Para pegar todos os valores passados no mockito.When
		System.out.println(argCapt.getAllValues());
		
	}
	
}
