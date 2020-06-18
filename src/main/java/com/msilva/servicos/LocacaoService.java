package com.msilva.servicos;

import static com.msilva.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import com.msilva.entities.Movie;
import com.msilva.entities.Location;
import com.msilva.entities.User;
import com.msilva.exceptions.MovieWithoutStockException;
import com.msilva.exceptions.LocadoraException;

public class LocacaoService {
	
	public Location alugarFilme(User user, List<Movie> movies) throws MovieWithoutStockException, LocadoraException {
		if(user == null) {
			throw new LocadoraException("Usuario vazio");
		}
		
		if(movies == null || movies.isEmpty()) {
			throw new LocadoraException("Filme vazio");
		}
		
		for(Movie movie : movies) {
			if(movie.getEstoque() == 0) {
				throw new MovieWithoutStockException();
			}
		}
		
		Location location = new Location();
		location.setFilmes(movies);
		location.setUsuario(user);
		location.setDataLocacao(new Date());
		Double valorTotal = 0d;
		for(int i = 0; i < movies.size(); i++) {
			Movie movie = movies.get(i);
			Double valorFilme = movie.getPrecoLocacao();
			switch (i) {
				case 2: valorFilme = valorFilme * 0.75; break;
				case 3: valorFilme = valorFilme * 0.5; break;
				case 4: valorFilme = valorFilme * 0.25; break;
				case 5: valorFilme = 0d; break;
			}
			valorTotal += valorFilme;
		}
		location.setValor(valorTotal);
		
		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		location.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return location;
	}
}