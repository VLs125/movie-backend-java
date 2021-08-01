package com.movie.auth.controller;

import com.movie.auth.config.JwtUtils;
import com.movie.auth.model.ERoles;
import com.movie.auth.model.Role;
import com.movie.auth.model.User;
import com.movie.auth.pojo.JwtResponse;
import com.movie.auth.pojo.MessageResponse;
import com.movie.auth.pojo.SigninRequest;
import com.movie.auth.pojo.SignupRequest;
import com.movie.auth.repository.RoleRepository;
import com.movie.auth.repository.UserRepository;
import com.movie.auth.utils.SignInUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*",maxAge = 3600)
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    SignInUtils signinUtils;

    @PostMapping(value = "/sign")
    public ResponseEntity<?> authUser(@RequestBody SigninRequest signinRequest) {
       JwtResponse response = signinUtils.signIn(authenticationManager,signinRequest,jwtUtils);
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/signup")
    public ResponseEntity<?> registrationUser(@RequestBody SignupRequest signupRequest){
        if(userRepository.existsByUsername(signupRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exists"));
        }
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is exists"));
        }

        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));
        Set<String> reqRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if(reqRoles==null){
            Role userRole = roleRepository
                    .findByName(ERoles.ROLE_USER)
                    .orElseThrow(()->new RuntimeException("Role USER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(role->{
                switch (role){
                    case "admin":
                        Role adminRole = roleRepository
                                .findByName(ERoles.ROLE_ADMIN)
                                .orElseThrow(()-> new RuntimeException("Role ADMIN is not found"));
                        roles.add(adminRole);
                        break;

                    case "mod":
                        Role modRole = roleRepository
                                .findByName(ERoles.ROLE_MODERATOR)
                                .orElseThrow(()-> new RuntimeException("Role MODERATOR is not found"));
                        roles.add(modRole);
                        break;

                    default:
                        Role userRole = roleRepository
                                .findByName(ERoles.ROLE_USER)
                                .orElseThrow(()-> new RuntimeException("Role USER is not found"));
                        roles.add(userRole);
                        break;
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}
