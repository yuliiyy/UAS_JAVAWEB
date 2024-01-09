package com.uas.humanresource.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uas.humanresource.HumanResourceManagementSystemApplication;

import java.util.Collections;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }


        // Your login logic goes her
        //
        @PostMapping("/login")
        public String login(@RequestParam String username, @RequestParam String password) {
            // Your basic authentication logic without dependencies
            // Your basic authentication logic without dependencies
            if ("staff".equals(username) && "123".equals(password)) {
                // Create a simple authentication token with roles for demonstration purposes
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

                // Set the authentication object in the SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authentication);
                HumanResourceManagementSystemApplication.authenticatedUser = authentication;

                // Redirect to a success page
                return "redirect:/home";
            } else if ("admin".equals(username) && "123".equals(password)) {
                // Create a simple authentication token with admin role for demonstration purposes
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));

                // Set the authentication object in the SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authentication);

                HumanResourceManagementSystemApplication.authenticatedUser = authentication;

                // Redirect to a success page for admin
                return "redirect:/home";
            } else {
                // Redirect to a failure page
                return "/login";
            }
        }

//            // Your basic authentication logic without dependencies
//            if ("user".equals(username) && "password".equals(password)) {
//                // Create a simple authentication token for demonstration purposes
//                Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
//
//                // Set the authentication object in the SecurityContextHolder
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//
//                // Redirect to a success page
//                return "/index";
//            } else {
//                // Redirect to a failure page
//                return "redirect:/login";
//            }

//        return "redirect:/index"; // or whatever your success redirect is

}
