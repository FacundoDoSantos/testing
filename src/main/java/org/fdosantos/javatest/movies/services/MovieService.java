package org.fdosantos.javatest.movies.services;

import org.fdosantos.javatest.movies.data.MovieRepository;
import org.fdosantos.javatest.movies.model.Genre;
import org.fdosantos.javatest.movies.model.Movie;

import java.util.Collection;
import java.util.stream.Collectors;

public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> findMoviesByGenres(Genre genre) {

            return movieRepository.findAll().stream()
                    .filter(movie -> movie.getGenre() == genre)
                    .collect(Collectors.toList());
    }

    public Collection<Movie> findMoviesByLenght(int lenght) {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getMinutes() <= lenght)
                .collect(Collectors.toList());
    }
}
