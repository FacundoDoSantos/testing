package org.fdosantos.javatest.movies.data;

import org.fdosantos.javatest.movies.model.Genre;
import org.fdosantos.javatest.movies.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MovieRepositoryJdbcTest {

    private MovieRepositoryJdbc movieRepositoryJdbc;
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSource =
                new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        movieRepositoryJdbc = new MovieRepositoryJdbc(jdbcTemplate);
    }

    @Test
    public void load_movie_for_id() throws SQLException {
        Movie movie = movieRepositoryJdbc.findById(2);
        assertThat(movie,  is( new Movie(2,"Memento",113, Genre.THRILLER)));
    }
    @Test
    public void load_all_movies() throws SQLException {

        Collection<Movie> allMovies = movieRepositoryJdbc.findAll();
        assertThat(allMovies, is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2,"Memento",113,Genre.THRILLER),
                new Movie(3,"Matrix", 136, Genre.ACTION)
        )));
    }

    @Test
    public void insert_a_movie() {
        Movie movie = new Movie("Super 8", 112, Genre.THRILLER);
        movieRepositoryJdbc.saveOrUpdate(movie);
        Movie movieReturned = movieRepositoryJdbc.findById(4);
        assertThat(movieReturned, is(new Movie(4,"Super 8", 112, Genre.THRILLER)));
    }

    @After
    public void tearDown() throws Exception {

        final Statement s = dataSource.getConnection().createStatement();
        s.execute("drop all objects delete files");
    }
}