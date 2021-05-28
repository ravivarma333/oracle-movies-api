package com.ora.movieapi.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"username\"")
    private String name;

    @Column(name ="\"password\"")
    private String password;
}
