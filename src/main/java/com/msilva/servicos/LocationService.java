package com.msilva.servicos;

import com.msilva.entities.Location;
import com.msilva.entities.Movie;
import com.msilva.entities.User;
import com.msilva.exceptions.LocadoraException;
import com.msilva.exceptions.MovieWithoutStockException;

import java.util.*;
import java.util.function.Function;

import static com.msilva.utils.DataUtils.adicionarDias;

public class LocationService {

    protected static Map<Integer, Function<Double, Double>> calculatePercentage = new HashMap<>();

    public LocationService() {
        calculatePercentage.put(2, (x) -> calculate2(x));
        calculatePercentage.put(3, (x) -> calculate2(x));
        calculatePercentage.put(4, (x) -> calculate2(x));
        calculatePercentage.put(5, (x) -> calculate2(x));
    }

    public Location locationMovie(User user, List<Movie> movies) throws MovieWithoutStockException, LocadoraException {
        verifyInitial(user, movies);

        Location location = new Location();
        location.setFilmes(movies);
        location.setUsuario(user);
        location.setDataLocacao(new Date());
        Double valorTotal = 0d;
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getEstoque() == 0) {
                throw new MovieWithoutStockException();
            }
            Movie movie = movies.get(i);
            Double movieValue = movie.getPrecoLocacao();
            movieValue = calculatePercentage.getOrDefault(i, null).apply(movieValue);
            valorTotal += movieValue;
        }
        location.setValor(valorTotal);

        Date deliveryDate = new Date();
        deliveryDate = adicionarDias(deliveryDate, 1);
        location.setDataRetorno(deliveryDate);

        //Save location...
        //TODO add method to save

        return location;
    }

    private void verifyInitial(User user, List<Movie> movies) throws LocadoraException {
        if (user == null) {
            throw new LocadoraException("Empty user");
        }

        if (movies == null || movies.isEmpty()) {
            throw new LocadoraException("Movie empty");
        }
    }

    private Double calculate2(double movieValue) {
        return movieValue * 0.75;
    }

    private Double calculate3(double movieValue) {
        return movieValue * 0.5;
    }

    private Double calculate4(double movieValue) {
        return movieValue * 0.25;
    }

    private Double calculate5(double movieValue) {
        return 0d;
    }

}