package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoService {
	
	public Locacao alugarFilmes(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
				
		if (usuario == null) {
			throw new LocadoraException("Usuário vazio");
		}
				
		Locacao locacao = new Locacao();		
		locacao.setDataLocacao(new Date());
		locacao.setUsuario(usuario);
		
		if (filmes == null || filmes.isEmpty()) {
			throw new LocadoraException("Filme vazio");
		}
		
		int contagem = 0;
		for (Filme filme : filmes) {
			
			if (filme == null) {
				throw new LocadoraException("Filme vazio");
			}
			
			if(filme.getEstoque() == 0) {
				throw new FilmeSemEstoqueException();
			}
			
			Double valorFilme = filme.getPrecoLocacao();
			
			switch (contagem) {
				case 2:
					valorFilme = valorFilme * 0.75;
					break;
					
				case 3:
					valorFilme = valorFilme * 0.50;
					break;
					
				case 4:
					valorFilme = valorFilme * 0.25;
					break;
					
				case 5:
					valorFilme = 0d;
					break;
			}		
			
			locacao.setValor( locacao.getValor() + valorFilme );
			contagem++;
			
		}	
		locacao.setFilmes(filmes);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		
		if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)) {
			dataEntrega = adicionarDias(dataEntrega, 1);
		}
		
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		
		return locacao;
	}
	
}