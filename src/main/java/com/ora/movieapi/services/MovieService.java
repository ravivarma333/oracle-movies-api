package com.ora.movieapi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.ora.movieapi.domains.MovieDetails;
import com.ora.movieapi.dtos.MovieMetaDataRequest;
import com.ora.movieapi.repositories.MovieDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

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

    public MovieDetails saveMovieMetaData(String name) throws JsonProcessingException {
//        String url = "http://www.omdbapi.com/?i=tt3896198&apikey=9d880d53";
//        ResponseEntity<List<MovieMetaDataRequest>> movieMetaDataRequest = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<MovieMetaDataRequest>>(){});
        try {
            URL url = new URL("http://www.omdbapi.com/?i=tt3896198&apikey=9d880d53");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            String response = null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((response = bufferedReader.readLine()) != null) {
                Gson gson = new Gson();
                JSONObject jsnobject = new JSONObject(response);

                JSONResponse json = gson.fromJson(response, JSONResponse.class);
                List<MovieMetaDataRequest> data = json.data;
                data.forEach(action -> {
                    System.out.println(action.getActors());
                });
            }
            bufferedReader.close();
        }
        catch(Exception e){

        }

        MovieDetails movieDetails = new MovieDetails();
        return movieDetails;
    }

    class JSONResponse{
        List<MovieMetaDataRequest> data;
    }

}


