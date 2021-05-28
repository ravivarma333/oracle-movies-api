package com.ora.movieapi.controllers;

import com.ora.movieapi.dtos.MovieDTO;
import com.ora.movieapi.entities.Movie;
import com.ora.movieapi.services.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movie-management/")
@Api(value = "user" , produces = "applciation/json")
@Validated
public class MovieController {

    @Autowired
    MovieService movieService;

    @ApiOperation(value = "this method is used to get details of the movie by title")
    @GetMapping(value = "/movie")
    @ResponseBody
    public List<Movie> getMovieByTitle(@NotNull @NotEmpty @RequestParam("title") String title) {
        MovieDTO movieDTO = MovieDTO.builder().name(title).build();
        return movieService.getMovieByTitle(movieDTO);
    }


    @ApiOperation(value = "this method is used to get details of the by genre")
    @GetMapping(value = "/movies")
    @ResponseBody
    public List<Movie> getMoviesByGenre(@NotNull @NotEmpty @RequestParam("genre") String genre) {
        MovieDTO movieDTO = MovieDTO.builder().genre(genre).build();
        return movieService.getMoviesByGenre(movieDTO);
    }

    @ApiOperation(value = "this method is used to get details of the movie requested")
    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    @ResponseBody
    public List<Movie> addMovie(@Valid @RequestBody MovieDTO movieDTO) throws IOException {

        return movieService.addMovie(movieDTO , "movie");
    }


}
