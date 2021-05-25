package com.ora.movieapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ora.movieapi.domains.*;
import com.ora.movieapi.services.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
@Api(value = "user" , produces = "applciation/json")
public class MovieController {

    @Autowired
    MovieService movieService;



    @ApiOperation(value = "this method is used to get details of the movie requested")
    @GetMapping(value = "/movie/{title}")
    public MovieDetails getMovieDetails(@Valid @PathVariable("title") String title){
        return movieService.getMovieDetails(title);
    }

    @ApiOperation(value = "this method is used to get details of the movie requested")
    @PostMapping(value = "/movie")
    @ResponseBody
    public MovieDetails addMovieMetaData(@Valid @RequestBody String title) throws JsonProcessingException {
        return movieService.saveMovieMetaData(title);
    }


}
