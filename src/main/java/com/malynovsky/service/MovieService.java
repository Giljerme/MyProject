package com.malynovsky.service;

import com.malynovsky.dto.MovieDto;
import com.malynovsky.entity.Movie;

import java.util.List;
import java.util.Set;

/**
 * Created by Ivan on 08.04.2017.
 */
public interface MovieService {
    void addMovie(MovieDto movie);

    List<MovieDto> getAllMovie(long userId);

    Movie getMovieById(Long id);

    Set<MovieDto> searchInAllColumn(String param);

    List<MovieDto> getMovieWhichContainsInName(String param);

    List<MovieDto> getMovieWhichContainsInDescription(String param);

    List<MovieDto> getMovieForDirector(String director);

    void deleteMovie(Long id);

    void update(MovieDto movieDto);

    List<MovieDto> getTopMovie(Long id);

    long getAllMovieCount();

    List<MovieDto> getAllSortedMovie(long userId, String columnName, int direction);
}
