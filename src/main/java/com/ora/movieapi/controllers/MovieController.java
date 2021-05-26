package com.ora.movieapi.controllers;

import com.ora.movieapi.domains.MovieDetails;
import com.ora.movieapi.dtos.MovieDTO;
import com.ora.movieapi.services.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public MovieDetails getMovieByTitle(@Valid @RequestParam("title") String title){
        return movieService.getMovieByTitle(title);
    }

    @ApiOperation(value = "this method is used to get details of the movie requested")
    @PostMapping(value = "/movie")
    @ResponseBody
    public List<MovieDetails> addMovie(@Valid @RequestBody MovieDTO movieDTO) throws IOException {
        return movieService.addMovie(movieDTO);
    }


}
