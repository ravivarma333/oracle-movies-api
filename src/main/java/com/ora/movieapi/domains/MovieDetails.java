package com.ora.movieapi.domains;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.Builder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "movie")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @SerializedName("Title")
    @Column(name = "\"title\"")
    private String title;

    @SerializedName("Year")
    @Column(name = "\"year\"")
    private String year;

    @SerializedName("Genre")
    @Column(name = "\"genre\"")
    private String genre;

    @SerializedName("Director")
    @Column(name = "\"director\"")
    private String director;

    @SerializedName("imdbRating")
    @Column(name = "\"imdb_rating\"", scale = 10, precision = 2, columnDefinition = "DECIMAL")
    private BigDecimal imdbrating;

    @SerializedName("imdbVotes")
    @Column(name = "\"imdb_votes\"")
    private String imdbVotes;

    @SerializedName("imdbID")
    @Column(name = "\"imdb_id\"")
    private String imdbID;
}
