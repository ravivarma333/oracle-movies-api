package com.ora.movieapi.dtos;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    @SerializedName("name")
    @NotBlank(message = "Title cannot be null or blank")
    @NotEmpty
    @NotNull(message = "Title cannot be null or blank")
    private String name;
}
