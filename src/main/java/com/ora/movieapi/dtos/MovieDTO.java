package com.ora.movieapi.dtos;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDTO {

    @SerializedName("name")
    @NotEmpty
    @NotNull(message = "Title cannot be null or blank")
    private String name;


    private String genre;
}
