package br.ce.wcaquino.servicos;


import static br.ce.wcaquino.matchers.MatchersProprios.caiNumaSegunda;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	private LocacaoService service;
		
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	//Executa antes de qualquer teste
	@Before
	public void setup() {
		service = new LocacaoService();
	}
	
	//Executa depois de qualquer teste
	@After
	public void tearDown() {
		System.out.println("After");
	}
	
	//Executado antes da instanciação da classe
	@BeforeClass
	public static void setupClass() {
		System.out.println("BeforeClass");
	}
	
	//Executado depois de todos os testes serem finalizados
	@AfterClass
	public static void tearDownClass() {
		System.out.println("AfterClass");
	}
	
	@Test
	public void deveAlugarFilme() throws Exception {
		
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		//cenário
		Usuario usuario = new Usuario("Renan");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));
		
		//ação
		Locacao locacao;
		locacao = service.alugarFilmes(usuario, filmes);
			
		//verificação
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
			
	}
	
	//Forma Elegante - Espera que realmente dê uma exceção
	//Se não der exceção, o teste falha
	@Test(expected = FilmeSemEstoqueException.class)
	public void naoDeveAlugarFilmeSemEstoque() throws FilmeSemEstoqueException, LocadoraException{
		
		//cenário
		Usuario usuario = new Usuario("Renan");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));
				
		//ação
		service.alugarFilmes(usuario, filmes);		
	
	}
	
	//Forma robusta
	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
		
		//cenário
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		
		//ação
		try {
			service.alugarFilmes(null, filmes);
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuário vazio"));
		}	
		
	}	
	
	//Forma Nova
	@Test
	public void naoDeveAlugarFilmeSemFilme( ) throws FilmeSemEstoqueException, LocadoraException {
		
		//cenário
		Usuario usuario = new Usuario("Renan");
		
		//Prepara as exceções esperadas
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//ação
		service.alugarFilmes(usuario, null);
		
	}
	
	@Test
	public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException {
		
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		//cenário
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		
		//ação
		Locacao retornoLocacao = service.alugarFilmes(usuario, filmes);
		
//		//verificação
//		boolean ehSegunda = DataUtils.verificarDiaSemana(retornoLocacao.getDataRetorno(), Calendar.MONDAY);
//		Assert.assertTrue(ehSegunda);		

//		Assert.assertThat(retornoLocacao.getDataRetorno(), new DiaSemanaMatcher(Calendar.MONDAY));
//		Assert.assertThat(retornoLocacao.getDataRetorno(), caiEm(Calendar.MONDAY));
		Assert.assertThat(retornoLocacao.getDataRetorno(), caiNumaSegunda());
	}
	
	/*
	//Forma robusta - fornece um controle maior sobre o teste
	//Espera que lance uma exceção para o teste funcionar
	@Test
	public void testeLocacao_filmeSemEstoque2() throws LocadoraException{
		
		//cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Renan");
		Filme filme = new Filme("Filme 1", 0, 5.0);
		
		//ação
		try {
			service.alugarFilme(usuario, filme);
			Assert.fail("Deveria ter lançado uma exceção.");
		} catch (FilmeSemEstoqueException e) {
			
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
	
	}*/
	
}
