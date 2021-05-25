package com.ora.movieapi.repositories;

import com.ora.movieapi.domains.MovieDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieDetailsRepository extends JpaRepository<MovieDetails, Long> {

    public Optional<MovieDetails> findByTitle(String title);
}
