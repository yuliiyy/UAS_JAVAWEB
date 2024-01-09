package com.uas.humanresource.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.uas.humanresource.HumanResourceManagementSystemApplication;

import org.springframework.ui.Model;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/home")
    public String home(Model model) {
//        return "/index";
        Authentication authentication = HumanResourceManagementSystemApplication.authenticatedUser; //SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is authenticated

        if (authentication!= null && authentication.isAuthenticated()) {
//            model.addAttribute("authenticated", true);
//
//            // Check if the user has the ROLE_USER role
//            if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
//                model.addAttribute("userRole", true);
//            }
//
//            // Check if the user has the ROLE_ADMIN role
//            if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
//                model.addAttribute("adminRole", true);
//            }

            model.addAttribute("authenticated", true);
            model.addAttribute("username", authentication.getPrincipal());
            model.addAttribute("authority", authentication.getAuthorities().stream().toList().get(0));

            model.addAttribute("userRole", authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));

            model.addAttribute("adminRole", authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));

            // Log authentication details
//            logger.info("Authenticated user: {}", authentication.getName());
//            logger.info("User roles: {}", authentication.getAuthorities());

            return "home";
        }else {
            return "/login";
        }


    }

    @GetMapping("/home/logout")
    public String logout(Model model) {
//        return "/index";
        HumanResourceManagementSystemApplication.authenticatedUser = null;
        return "/login";



    }
}