package com.movie.auth.pojo;

import lombok.Getter;
import lombok.Setter;

public class SigninRequest {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
}
