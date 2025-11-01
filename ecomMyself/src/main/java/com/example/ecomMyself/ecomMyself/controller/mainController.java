package com.example.ecomMyself.ecomMyself.controller;

import com.example.ecomMyself.ecomMyself.repository.User_Repo;
import com.example.ecomMyself.ecomMyself.service.MyUserDetailService;
import com.example.ecomMyself.ecomMyself.service.UserPrincipal;
import com.example.ecomMyself.ecomMyself.model.Users;
import com.example.ecomMyself.ecomMyself.service.JwtService;
import com.example.ecomMyself.ecomMyself.service.User_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class mainController {
    @Autowired
    private User_service user_service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private User_Repo user_repo;
    @Autowired
    AuthenticationManager authenticationManager;
    @GetMapping("/")
    public String home()
    {
        return "Hello World";
    }
    @PostMapping("create_account")
    public ResponseEntity<?> creating_account(@RequestBody Users user) {
        try {
            Users savedUser = user_service.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e)
        {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping("login")
    public String login(@RequestBody Users user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()) {
            Users user2=user_service.finfByUserName(user.getUsername());
            return jwtService.generateToken(user.getUsername(), user2.getVersion());
        }
        else
            return "Login Failed";
    }

    @PostMapping("custom_logout")
    public String logout(@AuthenticationPrincipal UserPrincipal principal)
    {
        if(principal!=null) {
            System.out.println("logout "+principal.toString());
            user_service.changeVersion(principal.getUsername());
            return "logging out";
        }
        return "logged out";
    }
}
