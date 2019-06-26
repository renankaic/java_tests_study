package br.ce.wcaquino.servicos;


import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	@Test
	public void teste() {
		
		//cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Renan");
		Filme filme = new Filme("FIlme 1", 2, 5.0);
		
		//ação
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		//verificação
		//Assert.assertEquals(5.0, locacao.getValor(),  0.01);
		assertThat(locacao.getValor(), is(equalTo(5.0)));
		//assertThat(locacao.getValor(), is(not(6.0)));
		assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		assertThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
		
	}
	
}
