package com.ora.movieapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private String user;
    private String token;
    private String password;
}
