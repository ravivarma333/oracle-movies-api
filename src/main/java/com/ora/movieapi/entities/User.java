package com.ora.movieapi.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private long id;

    private String name;
    private String password;
}
