package com.ora.movieapi.entities;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "movie")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetails implements Serializable {

    @Id
    @SerializedName("imdbID")
    @Column(name = "\"imdb_id\"")
    private String imdbID;

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
}
