package com.msilva.servicos;



import com.msilva.entities.Location;
import com.msilva.entities.Movie;
import com.msilva.entities.User;
import com.msilva.exceptions.LocadoraException;
import com.msilva.exceptions.MovieWithoutStockException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.msilva.utils.DataUtils.isMesmaData;
import static com.msilva.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LocationServiceTest {

	private LocationService service;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup(){
		service = new LocationService();
	}
	
	@Test
	public void develocationMovie() throws Exception {
		//cenario
		User user = new User("Usuario 1");
		List<Movie> movies = Arrays.asList(new Movie("Filme 1", 1, 5.0));
		
		//acao
		Location location = service.locationMovie(user, movies);
			
		//verificacao
		error.checkThat(location.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(location.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(location.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
	}
	
	@Test(expected = MovieWithoutStockException.class)
	public void naoDevelocationMovieSemEstoque() throws Exception{
		//cenario
		User user = new User("Usuario 1");
		List<Movie> movies = Arrays.asList(new Movie("Filme 1", 0, 4.0));
		
		//acao
		service.locationMovie(user, movies);
	}
	
	@Test
	public void naoDevelocationMovieSemUsuario() throws MovieWithoutStockException {
		//cenario
		List<Movie> movies = Arrays.asList(new Movie("Filme 1", 1, 5.0));
		
		//acao
		try {
			service.locationMovie(null, movies);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Empty user"));
		}
	}

	@Test
	public void naoDevelocationMovieSemFilme() throws MovieWithoutStockException, LocadoraException{
		//cenario
		User user = new User("Usuario 1");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Movie empty");
		
		//acao
		service.locationMovie(user, null);
	}
	
	@Test
	public void devePagar75PctNoFilme3() throws MovieWithoutStockException, LocadoraException{
		//cenario
		User user = new User("Usuario 1");
		List<Movie> movies = Arrays.asList(new Movie("Filme 1", 2, 4.0), new Movie("Filme 2", 2, 4.0), new Movie("Filme 3", 2, 4.0));
		
		//acao
		Location resultado = service.locationMovie(user, movies);
		
		//verificacao
		assertThat(resultado.getValor(), is(11.0));
	}
	

	@Test
	public void devePagar50PctNoFilme4() throws MovieWithoutStockException, LocadoraException{
		//cenario
		User user = new User("Usuario 1");
		List<Movie> movies = Arrays.asList(
				new Movie("Filme 1", 2, 4.0), new Movie("Filme 2", 2, 4.0), new Movie("Filme 3", 2, 4.0), new Movie("Filme 4", 2, 4.0));
		
		//acao
		Location resultado = service.locationMovie(user, movies);
		
		//verificacao
		assertThat(resultado.getValor(), is(13.0));
	}
	

	@Test
	public void devePagar25PctNoFilme5() throws MovieWithoutStockException, LocadoraException{
		//cenario
		User user = new User("Usuario 1");
		List<Movie> movies = Arrays.asList(
				new Movie("Filme 1", 2, 4.0), new Movie("Filme 2", 2, 4.0),
				new Movie("Filme 3", 2, 4.0), new Movie("Filme 4", 2, 4.0),
				new Movie("Filme 5", 2, 4.0));
		
		//acao
		Location resultado = service.locationMovie(user, movies);
		
		//verificacao
		assertThat(resultado.getValor(), is(14.0));
	}
	

	@Test
	public void devePagar0PctNoFilme6() throws MovieWithoutStockException, LocadoraException{
		//cenario
		User user = new User("Usuario 1");
		List<Movie> movies = Arrays.asList(
				new Movie("Filme 1", 2, 4.0), new Movie("Filme 2", 2, 4.0),
				new Movie("Filme 3", 2, 4.0), new Movie("Filme 4", 2, 4.0),
				new Movie("Filme 5", 2, 4.0), new Movie("Filme 6", 2, 4.0));
		
		//acao
		Location resultado = service.locationMovie(user, movies);
		
		//verificacao
		assertThat(resultado.getValor(), is(14.0));
	}
}
