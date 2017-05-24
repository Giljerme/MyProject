package com.malynovsky.service.impl;

import com.malynovsky.dao.MovieDao;
import com.malynovsky.dto.MovieDto;
import com.malynovsky.entity.Movie;
import com.malynovsky.entity.Rate;
import com.malynovsky.service.MovieService;
import com.malynovsky.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ivan on 08.04.2017.
 */
@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDao movieDao;

    @Autowired
    private RateService rateService;

    @Override
    public void addMovie(MovieDto movie) {
        movieDao.save(movie.toMovie());
    }

    @Override
    public List<MovieDto> getAllMovie(long userId) {
        List<MovieDto> dtos = movieDao.findAll().stream().map(MovieDto::new).collect(Collectors.toList());
        List<Rate> rates = rateService.getAllUniqueRates();

        dtos.forEach(p -> p.setVoiceCount(rates.stream().filter(rate -> rate.getIdMovie() == p.getId()).count()));
        dtos.forEach(p -> p.setRate(rates.stream().filter(rate -> rate.getIdMovie() == p.getId())
                .mapToLong(Rate::getRateValue).average().orElse(0)));

        dtos.forEach(p -> p.setYourRate(rates.stream().filter(rate -> rate.getIdMovie() == p.getId())
                .filter(rate -> rate.getIdUser() == userId).map(Rate::getRateValue)
                .findFirst().orElse(0)));

        return dtos;
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieDao.findOne(id);
    }

    @Override
    public Set<MovieDto> searchInAllColumn(String param) {
        Set<MovieDto> movieDtos = new HashSet<>();

        movieDtos.addAll(getMovieWhichContainsInDescription(param));
        movieDtos.addAll(getMovieWhichContainsInName(param));
        movieDtos.addAll(getMovieForDirector(param));

        return movieDtos;
    }

    @Override
    public List<MovieDto> getMovieWhichContainsInName(String param) {
        return movieDao.findByMovieName(prepareParamForSearching(param))
                .stream().map(MovieDto::new).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getMovieWhichContainsInDescription(String param) {
        return movieDao.findByMovieDescription(prepareParamForSearching(param))
                .stream().map(MovieDto::new).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getMovieForDirector(String director) {
        return movieDao.findByDirector(prepareParamForSearching(director))
                .stream().map(MovieDto::new).collect(Collectors.toList());
    }

    @Override
    public void deleteMovie(Long id) {
        movieDao.delete(id);
    }

    @Override
    public void update(MovieDto movieDto) {
        movieDao.save(movieDto.toMovie());
    }

    @Override
    public List<MovieDto> getTopMovie(Long id) {
        return getAllMovie(id).stream()
                .sorted((MovieDto d1, MovieDto d2) -> Double.compare(d2.getRate(), d1.getRate()))
                .limit(10).collect(Collectors.toList());
    }

    @Override
    public long getAllMovieCount() {
        return movieDao.count();
    }

    @Override
    public List<MovieDto> getAllSortedMovie(long userId, String columnName, int direction) {
        List<MovieDto> dtos = getAllMovie(userId);
        Comparator<MovieDto> function;

        switch (columnName) {
            case "Year":
                function = (MovieDto d1, MovieDto d2) -> Integer.compare(d1.getYear(), d2.getYear()) * direction;
                break;
            case "Director":
                function = (MovieDto d1, MovieDto d2) -> d1.getDirector().compareTo(d2.getDirector()) * direction;
                break;
            case "Budget":
                function = (MovieDto d1, MovieDto d2) -> Long.compare(d2.getBudget(), d1.getBudget()) * direction;
                break;
            case "Box-office":
                function = (MovieDto d1, MovieDto d2) -> Long.compare(d2.getBoxOffice(), d1.getBoxOffice()) * direction;
                break;
            case "Rate":
                function = (MovieDto d1, MovieDto d2) -> Double.compare(d2.getRate(), d1.getRate()) * direction;
                break;
            case "Vote":
                function = (MovieDto d1, MovieDto d2) -> Long.compare(d2.getVoiceCount(), d1.getVoiceCount()) * direction;
                break;
            case "Your":
                function = (MovieDto d1, MovieDto d2) -> Integer.compare(d2.getYourRate(), d1.getYourRate()) * direction;
                break;
            default:
                function = (MovieDto d1, MovieDto d2) -> d1.getName().compareTo(d2.getName()) * direction;
                break;

        }

        return dtos.stream().sorted(function).collect(Collectors.toList());
    }

    private String prepareParamForSearching(String param) {
        return "%" + param + "%";
    }
}
