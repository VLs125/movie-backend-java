package com.movie.auth.utils;

import com.movie.auth.config.JwtUtils;
import com.movie.auth.pojo.JwtResponse;
import com.movie.auth.pojo.SigninRequest;
import com.movie.auth.service.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SignInUtils {

    public JwtResponse signIn(AuthenticationManager authenticationManager, SigninRequest signinRequest, JwtUtils jwtUtils){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        signinRequest.getUsername()
                        ,signinRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new JwtResponse(jwt,userDetails.getId(),userDetails.getUsername(),userDetails.getPassword(),roles);
    }
}
