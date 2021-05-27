package com.ora.movieapi.services;

import com.google.gson.Gson;
import com.ora.movieapi.dtos.MovieDTO;
import com.ora.movieapi.entities.MovieDetails;
import com.ora.movieapi.repositories.MovieDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovieService {

    @Autowired
    MovieDetailsRepository movieDetailsRepository;

    public List<MovieDetails> getMovieByTitle(MovieDTO movieDTO){
        //to update list present in DB
        addMovie(movieDTO,"movie");

        Optional<List<MovieDetails>> optionalMovieDetails = movieDetailsRepository.findByTitle(movieDTO.getName());
        List<MovieDetails> movieDetails = new ArrayList<>();
        if(optionalMovieDetails.isPresent())
            movieDetails = optionalMovieDetails.get();
        return movieDetails;
    }

    public List<MovieDetails> getMoviesByGenre(MovieDTO movieDTO){
        //to update list present in DB
        addMovie(movieDTO,"genre");

        Optional<List<MovieDetails>> optionalMovieDetailsList = movieDetailsRepository.findByGenreContainingIgnoreCase(movieDTO.getGenre());
        List<MovieDetails> movieDetailsList = new ArrayList<>();
        if(optionalMovieDetailsList.isPresent())
            movieDetailsList = optionalMovieDetailsList.get();
        return movieDetailsList;
    }


    public List<MovieDetails> addMovie(MovieDTO movieDTO, String findBy){
        MovieDetails movieDetailsResponse;
        List<MovieDetails> movieDetailsList = new ArrayList<>();
        try {
            URL url = new URL("http://www.omdbapi.com/?i=tt3896198&apikey=9d880d53");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            String response = null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((response = bufferedReader.readLine()) != null) {
                Gson gson = new Gson();
                movieDetailsResponse = gson.fromJson(response, MovieDetails.class);
                if(findBy.equalsIgnoreCase("movie") && movieDetailsResponse.getTitle().toLowerCase().equalsIgnoreCase(movieDTO.getName().toLowerCase())){
                    movieDetailsList.add(movieDetailsResponse);
                }
                if(findBy.equalsIgnoreCase("genre") && movieDetailsResponse.getGenre().toLowerCase().contains(movieDTO.getGenre().toLowerCase())){
                    movieDetailsList.add(movieDetailsResponse);
                }
            }
            bufferedReader.close();
        } catch (MalformedURLException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        movieDetailsRepository.saveAll(movieDetailsList);
        return movieDetailsList;
    }
}
