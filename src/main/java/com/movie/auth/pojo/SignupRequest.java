package com.movie.auth.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class SignupRequest {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private Set<String> roles;
    @Getter
    @Setter
    private String password;
}
