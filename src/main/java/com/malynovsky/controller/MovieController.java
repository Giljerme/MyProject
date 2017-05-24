package com.malynovsky.controller;

import com.malynovsky.dto.MovieDto;
import com.malynovsky.service.MovieService;
import com.malynovsky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Ivan on 08.04.2017.
 */
@Controller
@RequestMapping(value = "movie/")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "movie-all", method = GET)
    public String listOfAllMovie() {
        return "movie-all";
    }

    @RequestMapping(value = "movie-count", method = GET)
    public @ResponseBody Long getMovieCount() {
        return movieService.getAllMovieCount();
    }

    @RequestMapping(value = "all", method = GET, headers="Accept=*/*", produces = "application/json")
    public @ResponseBody List<MovieDto> getAllMovie(@RequestParam String name, @RequestParam Long page,
                                                    @RequestParam String columnForSorting,
                                                    @RequestParam Integer direction) {

        return movieService.getAllSortedMovie(userService.findByUsername(name).getId(), columnForSorting,
                direction != 0 ? direction : -1)
                .stream().skip(page * 5).limit(5).collect(Collectors.toList());
    }

    @RequestMapping(value = "top-movie", method = GET, headers="Accept=*/*", produces = "application/json")
    public @ResponseBody List<MovieDto> getTopMovie(@RequestParam String name) {

        return movieService.getTopMovie(userService.findByUsername(name).getId());
    }

    @RequestMapping(value = "add", method = POST, headers="Accept=*/*")
    void addMovie(@RequestParam Long id, @RequestParam String name,
                  @RequestParam Long budget, @RequestParam Long box,
                  @RequestParam String desc, @RequestParam Integer year,
                  @RequestParam String director) {

        MovieDto movieDto = new MovieDto(id, name, year, director, budget, box, desc);
        movieService.addMovie(movieDto);
    }

    @RequestMapping(value = "delete", method = DELETE, headers="Accept=*/*")
    void deleteMovie(@RequestParam Long id) {

        movieService.deleteMovie(id);
    }

    @RequestMapping(value = "searchAll", method = GET)
    public @ResponseBody Set<MovieDto> getSearchResult(@RequestParam String param) {

        Set<MovieDto> dtos = movieService.searchInAllColumn(param);
        dtos.forEach(System.out::println);

        return dtos;
    }
}
