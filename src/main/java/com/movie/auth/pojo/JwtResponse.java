package com.movie.auth.pojo;

import com.movie.auth.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

public class JwtResponse {
    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private String type ="Bearer";
    @Getter
    @Setter
    private UUID id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private List<String> roles;

    public JwtResponse(String token, UUID id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

}
