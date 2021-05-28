package com.ora.movieapi.repositories;

import com.ora.movieapi.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieDetailsRepository extends JpaRepository<Movie, String> {

    public Optional<List<Movie>> findByTitle(String title);

    public Optional<List<Movie>> findByGenreContainingIgnoreCase(String genre);
}
