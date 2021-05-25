package com.ora.movieapi.domains;

import lombok.*;
import lombok.Builder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "movie_details")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "\"title\"")
    private String title;

    @Column(name = "\"year\"")
    private String year;

    @Column(name = "\"genere\"")
    private String genere;

    @Column(name = "\"director\"")
    private String director;

    @Column(name = "\"imdb_rating\"", scale = 10, precision = 2, columnDefinition = "DECIMAL")
    private BigDecimal imdbrating;

    @Column(name = "\"imdb_votes\"")
    private Double imdbVotes;

    @Column(name = "\"imdb_id\"")
    private String imdbID;
}
