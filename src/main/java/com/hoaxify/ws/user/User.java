package com.hoaxify.ws.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created By Yasin Memic on Mar, 2020
 */
@Data
@Entity
public class User {
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 4, max = 255)
    @UniqueUsername
    private String username;
    @NotNull
    @Size(min = 4, max = 255)
    private String displayName;

    @Pattern(regexp = PASSWORD_PATTERN)
    @NotNull
    @Size(min = 6, max = 255)
    private String password;
}

