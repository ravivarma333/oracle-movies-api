package com.ora.movieapi.services;

import com.ora.movieapi.dtos.MovieDTO;
import com.ora.movieapi.entities.Movie;
import com.ora.movieapi.repositories.MovieDetailsRepository;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    private MovieService movieServiceUnderTest;

    @Before
    public void setUp() {
        movieServiceUnderTest = new MovieService();
        movieServiceUnderTest.movieDetailsRepository = mock(MovieDetailsRepository.class);
    }


    @Test
    public void testGetMovieByTitle() {
        // Setup
        MovieDTO movieDTO = MovieDTO.builder().name("title").build();
        List<Movie> movieList =new ArrayList<>();
        movieList.add(Movie.builder().genre("action").title("abc").director("JB").imdbID("tt212").imdbrating(BigDecimal.ONE).imdbVotes("2133").build());

        List<Movie> movies = Arrays.asList(new Movie("imdbID", "title", "year", "genre", "director", new BigDecimal("0.00"), "imdbVotes"));
        when(movieServiceUnderTest.movieDetailsRepository.saveAll(movieList)).thenReturn(movies);

        when(movieServiceUnderTest.movieDetailsRepository.findByTitle("title")).thenReturn(Optional.of(movieList));

        // Run the test
        movieServiceUnderTest.getMovieByTitle(movieDTO);

        // Verify the results
        verify(movieServiceUnderTest.movieDetailsRepository,times(1)).findByTitle("title");
    }

    @Test
    public void testGetMoviesByGenre() {
        // Setup
        List<Movie> movieList =new ArrayList<>();
        movieList.add(Movie.builder().genre("action").title("abc").director("JB").imdbID("tt212").imdbrating(BigDecimal.ONE).imdbVotes("2133").build());
        final MovieDTO movieDTO = MovieDTO.builder().genre("action").build();

        final List<Movie> movies = Arrays.asList(new Movie("imdbID", "title", "year", "genre", "director", new BigDecimal("0.00"), "imdbVotes"));
        when(movieServiceUnderTest.movieDetailsRepository.saveAll(movies)).thenReturn(movies);

        when(movieServiceUnderTest.movieDetailsRepository.findByGenreContainingIgnoreCase("action")).thenReturn(Optional.of(movies));

        // Run the test
        movieServiceUnderTest.getMoviesByGenre(movieDTO);

        // Verify the results
        verify(movieServiceUnderTest.movieDetailsRepository,times(1)).findByGenreContainingIgnoreCase("action");

    }

    @Test
    public void testAddMovie() {
        // Setup
        final MovieDTO movieDTO = MovieDTO.builder().name("title").genre("action").build();

        final List<Movie> movieList = Arrays.asList(new Movie("imdbID", "title", "year", "genre", "director", new BigDecimal("0.00"), "imdbVotes"));
        when(movieServiceUnderTest.movieDetailsRepository.saveAll(movieList)).thenReturn(movieList);

        // Run the test
        movieServiceUnderTest.addMovie(movieDTO, "movie");

        // Verify the results
        verify(movieServiceUnderTest.movieDetailsRepository,times(1)).saveAll(anyList());
    }

    @Test
    public void testGetJSON() {
        // Setup

        // Run the test
        final String result = movieServiceUnderTest.getJSON("http://www.omdbapi.com/?i=tt3896198&apikey=9d880d53", 1200);

        // Verify the results
        assertThat(result).isNotBlank();
    }

    @Test
    public void testIsJSONValid() {
        // Setup
        String json = "{abc : test}";

        // Run the test
        final boolean result = movieServiceUnderTest.isJSONValid(json);

        // Verify the results
        assertThat(result).isTrue();

        final boolean result1 = movieServiceUnderTest.isJSONValid("json");

        assertThat(result1).isFalse();

    }
}
