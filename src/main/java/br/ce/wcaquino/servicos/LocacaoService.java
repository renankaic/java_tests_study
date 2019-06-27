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
		
		for (Filme filme : filmes) {
			
			if (filme == null) {
				throw new LocadoraException("Filme vazio");
			}
			
			if(filme.getEstoque() == 0) {
				throw new FilmeSemEstoqueException();
			}
			
			locacao.setValor( locacao.getValor() + filme.getPrecoLocacao() );
			
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