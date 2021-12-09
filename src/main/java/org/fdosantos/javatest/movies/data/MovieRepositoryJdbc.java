package org.fdosantos.javatest.movies.data;

import org.fdosantos.javatest.movies.model.Genre;
import org.fdosantos.javatest.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collection;

public class MovieRepositoryJdbc implements MovieRepository {

    private JdbcTemplate jdbcTemplate;

    public MovieRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Movie findById(long id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject("SELECT * FROM MOVIES WHERE id= ?", args, rowMapper);
    }
    public Collection<Movie> findAll() {

        return jdbcTemplate.query("SELECT * FROM MOVIES", rowMapper);
    }
    public void saveOrUpdate(Movie movie) {
        jdbcTemplate.update("INSERT INTO MOVIES(name, minutes, genre) VALUES(?, ? , ?)", movie.getName(), movie.getMinutes(), movie.getGenre().toString());
    }
    private RowMapper<Movie> rowMapper = (resultSet, i) -> new Movie(resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getInt("minutes"),
            Genre.valueOf(resultSet.getString("genre")));
}
