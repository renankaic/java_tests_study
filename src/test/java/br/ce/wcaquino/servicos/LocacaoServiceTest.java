package br.ce.wcaquino.servicos;


import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testeLocacao() throws Exception {
		
		//cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Renan");
		Filme filme = new Filme("Filme 1", 2, 5.0);
		
		//ação
		Locacao locacao;
		locacao = service.alugarFilme(usuario, filme);
			
		//verificação
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
			
	}
	
	//Forma Elegante - Espera que realmente dê uma exceção
	//Se não der exceção, o teste falha
	@Test(expected = Exception.class)
	public void testeLocacao_filmeSemEstoque1() throws Exception{
		
		//cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Renan");
		Filme filme = new Filme("Filme 1", 0, 5.0);
		
		//ação
		service.alugarFilme(usuario, filme);		
	
	}
	
	//Forma robusta - fornece um controle maior sobre o teste
	//Espera que lance uma exceção para o teste funcionar
	@Test
	public void testeLocacao_filmeSemEstoque2(){
		
		//cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Renan");
		Filme filme = new Filme("Filme 1", 0, 5.0);
		
		//ação
		try {
			service.alugarFilme(usuario, filme);
			Assert.fail("Deveria ter lançado uma exceção.");
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(), is("Filme sem estoque"));
		}		
	
	}
	
	//Forma nova 
	@Test
	public void testeLocacao_filmeSemEstoque3() throws Exception{
		
		//cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Renan");
		Filme filme = new Filme("Filme 1", 0, 5.0);
		
		//espera pela exceção
		exception.expect(Exception.class);
		exception.expectMessage("Filme sem estoque");
		
		//ação
		service.alugarFilme(usuario, filme);			
	
	}
	
}
