package com.movie.auth.config;

import com.movie.auth.service.UserDetailsImpl;
import com.movie.auth.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${app.jwtSecret}")
    private String jwtSecret;
    @Value("${app.jwtExpirationMs}")
    private int jwtExperationMs;

    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()+jwtExperationMs))
                .signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
    }
    public boolean validateJwtToken(String jwt){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(jwt);
            return true;
        }catch (MalformedJwtException | IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
        return false;
    }
    public String getUserNameFromJwtToken (String jwt){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(jwt).getBody().getSubject();
    }
}
