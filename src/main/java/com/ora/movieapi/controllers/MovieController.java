package com.ora.movieapi.controllers;

import com.ora.movieapi.dtos.MovieDTO;
import com.ora.movieapi.entities.MovieDetails;
import com.ora.movieapi.services.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "this method is used to get details of the movie requested")
    @GetMapping(value = "/movie")
    @ResponseBody
    public List<MovieDetails> getMovieByTitle(@Validated @NotNull @NotEmpty @RequestParam("title") String title){
        MovieDTO movieDTO = MovieDTO.builder().name(title).build();
        return movieService.getMovieByTitle(movieDTO);
    }


    @ApiOperation(value = "this method is used to get details of the movie requested")
    @GetMapping(value = "/movies")
    @ResponseBody
    public List<MovieDetails> getMoviesByGenre(@Validated @NotNull @NotEmpty @RequestParam("genre") String genre){
        MovieDTO movieDTO = MovieDTO.builder().genre(genre).build();
        return movieService.getMoviesByGenre(movieDTO);
    }

    @ApiOperation(value = "this method is used to get details of the movie requested")
    @PostMapping(value = "/movie")
    @ResponseBody
    public List<MovieDetails> addMovie(@Validated @RequestBody MovieDTO movieDTO) throws IOException {
        return movieService.addMovie(movieDTO , "movie");
    }


}
