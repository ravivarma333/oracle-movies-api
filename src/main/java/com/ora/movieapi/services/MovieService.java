package com.ora.movieapi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.ora.movieapi.domains.MovieDetails;
import com.ora.movieapi.dtos.MovieMetaDataRequest;
import com.ora.movieapi.repositories.MovieDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieService {

    @Autowired
    MovieDetailsRepository movieDetailsRepository;

    @Autowired
    private RestTemplate restTemplate;


    public MovieDetails getMovieDetails(String title){
        MovieDetails movieDetails =  MovieDetails.builder().build();
        movieDetailsRepository.findByTitle(title);
        return new MovieDetails();
    }

    public MovieDetails saveMovieMetaData(String title){
        List<MovieMetaDataRequest> movieMetaDataRequestList = new ArrayList<>();
        List<MovieDetails> movieDetailsList = new ArrayList<>();
        try {
            URL url = new URL("http://www.omdbapi.com/?i=tt3896198&apikey=9d880d53");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            String response = null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((response = bufferedReader.readLine()) != null) {
                Gson gson = new Gson();
//                MovieMetaDataRequest json = gson.fromJson(response, MovieMetaDataRequest.class);
                movieMetaDataRequestList = Arrays.asList(gson.fromJson("[" +response + "]",
                        MovieMetaDataRequest[].class));
                movieDetailsList = Arrays.asList(gson.fromJson("[" +response + "]",
                        MovieDetails[].class));
            }
            bufferedReader.close();
        } catch (MalformedURLException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        movieMetaDataRequestList.stream().filter(ele -> ele.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
        MovieDetails movieDetails = MovieDetails.builder().build();
        movieDetailsRepository.saveAll(movieDetailsList);
        return movieDetails;
    }
}
