package com.codegym.musichouse.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.codegym.musichouse.message.request.ChangePasswordForm;
import com.codegym.musichouse.message.request.LoginForm;
import com.codegym.musichouse.message.request.SignUpForm;
import com.codegym.musichouse.message.request.UpdateForm;
import com.codegym.musichouse.message.respond.JwtResponse;
import com.codegym.musichouse.message.respond.ResponseMessage;
import com.codegym.musichouse.model.Role;
import com.codegym.musichouse.model.RoleName;
import com.codegym.musichouse.model.User;
import com.codegym.musichouse.repository.RoleRepository;
import com.codegym.musichouse.repository.UserRepository;
import com.codegym.musichouse.security.jwt.JwtAuthTokenFilter;
import com.codegym.musichouse.security.jwt.JwtProvider;

import com.codegym.musichouse.security.services.UserDetailsServiceImpl;
import com.codegym.musichouse.security.services.UserPrinciple;
import com.codegym.musichouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    JwtAuthTokenFilter jwtAuthTokenFilter;

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!",null),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!",null),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(),signUpRequest.getAvatarUrl(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!",null), HttpStatus.OK);
    }

    @PutMapping("/updateuser")
    public ResponseEntity<?> updatePostUser(HttpServletRequest request, @Valid @RequestBody UpdateForm updateRequest){
        String jwt = jwtAuthTokenFilter.getJwt(request);
        String username = jwtProvider.getUserNameFromJwtToken(jwt);
        User user;
        try {
            user = userService
                    .findByUsername(username)
                    .orElseThrow(
                            () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }

        if (updateRequest.getName() != null && updateRequest.getEmail() != null) {
            user.setName(updateRequest.getName());
            user.setEmail(updateRequest.getEmail());
        }

        User save = userService.save(user);
        UserPrinciple userDetails = (UserPrinciple)userDetailsService.loadUserByUsername(username);
        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername(),userDetails.getAuthorities());
        return ResponseEntity.ok(jwtResponse);
    }

    @GetMapping("/updateuser/{username}")
    public ResponseEntity<?> getUser(@PathVariable("username") String username) {
        Optional<User> user = userService.findByUsername(username);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> showUser(@PathVariable("username") String username) {
        Optional<User> user = userService.findByUsername(username);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(HttpServletRequest request, @Valid @RequestBody ChangePasswordForm changePasswordForm) {
        String jwt = jwtAuthTokenFilter.getJwt(request);
        String username = jwtProvider.getUserNameFromJwtToken(jwt);
        User user;
        try {
            user = userService
                    .findByUsername(username)
                    .orElseThrow(
                            () -> new UsernameNotFoundException("User Not Found with -> username:" + username));
        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }

        boolean matches = encoder.matches(changePasswordForm.getCurrentPassword(), user.getPassword());
        if ( changePasswordForm.getNewPassword() != null) {
            if (matches){
                user.setPassword(encoder.encode(changePasswordForm.getNewPassword()));
                userService.save(user);
            }
            else {
                new ResponseEntity(new ResponseMessage("Change Failed"), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(new ResponseMessage("Change Succeed"), HttpStatus.OK);
    }
}

