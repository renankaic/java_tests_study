package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import builders.FilmeBuilder;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTeste {

	@Parameter(value=0)
	public List<Filme> filmes;
	
	@Parameter(value=1)
	public Double valorLocacao;
	
	@Parameter(value=2)
	public String descricaoString;
	
	private LocacaoService service;
	
	//Executa antes de qualquer teste
	@Before
	public void setup() {
		service = new LocacaoService();
	}
	
	private static Filme filme1 = FilmeBuilder.umFilme().agora();
	private static Filme filme2 = FilmeBuilder.umFilme().agora();
	private static Filme filme3 = FilmeBuilder.umFilme().agora();
	private static Filme filme4 = FilmeBuilder.umFilme().agora();
	private static Filme filme5 = FilmeBuilder.umFilme().agora();
	private static Filme filme6 = FilmeBuilder.umFilme().agora();
	private static Filme filme7 = FilmeBuilder.umFilme().agora();
	
	//Método que trará os parâmetros que serão utilizados no teste
	@Parameters(name="{2}")
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object[][] {
			// { @parametro(value=0), @parametro(value=1) }
			{Arrays.asList(filme1, filme2), 8.0, "2 Filmes: Sem Desconto."},
			{Arrays.asList(filme1, filme2, filme3), 11.0, "3 Filmes: 25%"},
			{Arrays.asList(filme1, filme2, filme3, filme4), 13.0, "4 Filmes: 50%"},
			{Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "5 Filmes: 75%"},
			{Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "6 Filmes: 100%"},
			{Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6, filme7), 18.0, "7 Filmes: Sem Desconto"}
		});
	}
	
	@Test
	public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {
		
		//cenário
		Usuario usuario = new Usuario("Usuario 1");		
		
		//ação
		Locacao resultadoLocacao = service.alugarFilmes(usuario, filmes);
		
		//verificação
		Assert.assertThat(resultadoLocacao.getValor(), is(valorLocacao));		
				
	}
	
	@Test
	public void print() {
		
		System.out.println(valorLocacao);
		
	}
	
}
