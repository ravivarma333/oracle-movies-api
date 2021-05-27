package com.ora.movieapi.repositories;

import com.ora.movieapi.entities.MovieDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieDetailsRepository extends JpaRepository<MovieDetails, String> {

    public Optional<List<MovieDetails>> findByTitle(String title);

    public Optional<List<MovieDetails>> findByGenreContainingIgnoreCase(String genre);
}
