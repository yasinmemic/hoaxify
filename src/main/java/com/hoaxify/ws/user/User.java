package com.hoaxify.ws.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Created By Yasin Memic on Mar, 2020
 */
@Data
@Entity
@Table(name = "USERS")
public class User implements UserDetails {
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,255})";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "{hoaxify.constraint.username.NotNull.message}")
    @Size(min = 4, max = 255)
    @UniqueUsername
    private String username;

    @NotNull(message = "{hoaxify.constraint.displayName.NotNull.message}")
    @Size(min = 4, max = 255)
    private String displayName;

    @Pattern(regexp = PASSWORD_PATTERN, message = "{hoaxify.constraint.password.Pattern.message}")
    @NotNull(message = "{hoaxify.constraint.password.NotNull.message}")
    @Size(min = 6, max = 255)
    private String password;

    @Lob
    private String image;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

