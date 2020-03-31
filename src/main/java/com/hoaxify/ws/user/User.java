package com.hoaxify.ws.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created By Yasin Memic on Mar, 2020
 */
@Data
@Entity
public class User {
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,255})";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "{hoaxify.constraint.username.NotNull.message}")
    @Size(min = 4, max = 255)
    @UniqueUsername
    @Column
    private String username;
    @NotNull(message = "{hoaxify.constraint.displayName.NotNull.message}")
    @Size(min = 4, max = 255)
    private String displayName;

    @Pattern(regexp = PASSWORD_PATTERN, message = "{hoaxify.constraint.password.Pattern.message}")
    @NotNull(message = "{hoaxify.constraint.password.NotNull.message}")
    @Size(min = 6, max = 255)
    private String password;
}

