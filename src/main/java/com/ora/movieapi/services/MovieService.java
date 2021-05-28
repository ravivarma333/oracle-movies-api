package com.ora.movieapi.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ora.movieapi.dtos.MovieDTO;
import com.ora.movieapi.entities.Movie;
import com.ora.movieapi.repositories.MovieDetailsRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Slf4j
public class MovieService {

    @Autowired
    MovieDetailsRepository movieDetailsRepository;

    @Value("${omdbapi.url}")
    @Setter
    private String omdbApi;

    private String movie = "movie";
    private String genre = "genre";

    public List<Movie> getMovieByTitle(MovieDTO movieDTO){
        addMovie(movieDTO,movie);
        Optional<List<Movie>> optionalMovieDetails = movieDetailsRepository.findByTitle(movieDTO.getName());
        if(optionalMovieDetails.isPresent())
            return optionalMovieDetails.get();
        else
            return new ArrayList<>();
    }

    public List<Movie> getMoviesByGenre(MovieDTO movieDTO){
        addMovie(movieDTO,genre);
        Optional<List<Movie>> optionalMovieDetailsList = movieDetailsRepository.findByGenreContainingIgnoreCase(movieDTO.getGenre());
        if(optionalMovieDetailsList.isPresent())
            return optionalMovieDetailsList.get();
        else
            return new ArrayList<>();
    }

    public List<Movie> addMovie(MovieDTO movieDTO, String findBy){
        List<Movie> movieList = new ArrayList<>();
        List<Movie> movieResultList = new ArrayList<>();
        String data = getJSON(omdbApi, 600);
        if (isJSONValid(data)) {
            Gson gson = new Gson();
            if (!data.startsWith("[")) {
                movieList.add(gson.fromJson(data, Movie.class));
            } else {
                Type userListType = new TypeToken<ArrayList<Movie>>(){}.getType();
                movieList = gson.fromJson(data, userListType);
            }
        }
        movieList.forEach(element->{
            if(findBy.equalsIgnoreCase(movie) && element.getTitle().equalsIgnoreCase(movieDTO.getName())){
                movieResultList.add(element);
            }

            if(findBy.equalsIgnoreCase(genre) && element.getGenre().contentEquals(movieDTO.getGenre())){
                movieResultList.add(element);
            }
        });
        movieDetailsRepository.saveAll(movieResultList);
        return movieResultList;
    }

    public String getJSON(String url, int timeout) {
        HttpURLConnection connection = null;
        try {
            URL u = new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-length", "0");
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.connect();
            int status = connection.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
