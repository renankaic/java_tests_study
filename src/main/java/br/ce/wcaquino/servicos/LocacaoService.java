package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

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
			if( contagem == 2) {
				valorFilme = valorFilme * 0.75;
			} else if ( contagem == 3) {
				valorFilme = valorFilme * 0.50;
			} else if ( contagem == 4) {
				valorFilme = valorFilme * 0.25;
			} else if ( contagem == 5) {
				valorFilme = 0.0;
			}
			
			locacao.setValor( locacao.getValor() + valorFilme );
			contagem++;
			
		}	
		locacao.setFilmes(filmes);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}
	
}