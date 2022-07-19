package com.example.clinicaOdontologicaC47Sv7.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @GetMapping
    public String home(){
        return "<h1>Welcome</h1>";
    }
    @GetMapping("/user")
    public String user(){
        return "<h1>Welcome user</h1>";
    }
    @GetMapping("/admin")
    public String admin(){
        return "<h1>Welcome admin</h1>";
    }
}
