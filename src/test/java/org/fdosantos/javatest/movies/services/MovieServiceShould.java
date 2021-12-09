package org.fdosantos.javatest.movies.services;

import org.fdosantos.javatest.movies.data.MovieRepository;
import org.fdosantos.javatest.movies.model.Genre;
import org.fdosantos.javatest.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MovieServiceShould {

    private MovieService movieService;
    @Before
    public void setUp() throws Exception {
        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        Mockito.when(movieRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Movie(1, "Dark Knight", 152, Genre.ACTION),
                        new Movie(2, "Memento", 113, Genre.THRILLER),
                        new Movie(3, "There's Something About Mary", 119, Genre.COMEDY),
                        new Movie(4,"Super 8", 112,Genre.THRILLER),
                        new Movie(5,"Scream", 111, Genre.HORROR),
                        new Movie(6, "Home Alone", 103, Genre.COMEDY),
                        new Movie(7, "Matrix", 136, Genre.ACTION)
                )
        );
        movieService = new MovieService(movieRepository);
    }

    @Test
    public void return_movies_by_gender() {
        Collection<Movie> movies = movieService.findMoviesByGenres(Genre.COMEDY);
        List<Integer> moviesIds = getIntegers(movies);
        Assert.assertThat(moviesIds, CoreMatchers.is(Arrays.asList(3,6)));
    }
    @Test
    public void return_movies_by_duration() {
        Collection<Movie> movies = movieService.findMoviesByLenght(120);
        List<Integer> moviesIds = getIntegers(movies);
        Assert.assertThat(moviesIds, CoreMatchers.is(Arrays.asList(2,3,4,5,6)));
    }

    private List<Integer> getIntegers(Collection<Movie> movies) {
        List<Integer> moviesIds =  movies.stream().map(movie -> movie.getId()).collect(Collectors.toList());
        return moviesIds;
    }
}