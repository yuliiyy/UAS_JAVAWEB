package com.uas.humanresource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uas.humanresource.HumanResourceManagementSystemApplication;
import com.uas.humanresource.entity.Owner;
import com.uas.humanresource.service.OwnerService;

@Controller
public class OwnerController {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        super();
        this.ownerService = ownerService;
    }

    // handler method to handle list owners and return model and view
    @GetMapping("/owners")
    public String listOwners(Model model) {
        Authentication authentication = HumanResourceManagementSystemApplication.authenticatedUser;

        // Check if the user is authenticated

        if (authentication != null) {

            model.addAttribute("adminRole", authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));
            model.addAttribute("userRole", authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));

            model.addAttribute("owners", ownerService.getAllOwners());
            return "owners";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/owners/new")
    public String createOwnerForm(Model model) {

        Authentication authentication = HumanResourceManagementSystemApplication.authenticatedUser;

        // Check if the user is authenticated

        if (authentication != null) {
            model.addAttribute("adminRole", authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));
            // create owner object to hold owner form data
            if (model.getAttribute("adminRole") == null) {
                return "redirect:/home";
            }
            Owner owner = new Owner();
            model.addAttribute("owner", owner);
            return "create_owner";
        } else {
            return "redirect:/login";
        }

    }

    @PostMapping("/owners")
    public String saveOwner(@ModelAttribute("owner") Owner owner) {
        ownerService.saveOwner(owner);
        return "redirect:/owners";
    }

    @GetMapping("/owners/edit/{id}")
    public String editOwnerForm(@PathVariable Long id, Model model) {
        Authentication authentication = HumanResourceManagementSystemApplication.authenticatedUser;
        if (authentication != null) {
            // create owner object to hold owner form data
            model.addAttribute("owner", ownerService.getOwnerById(id));
            return "edit_owner";
        } else {
            return "redirect:/login";
        }

    }

    @PostMapping("/owners/{id}")
    public String updateOwner(@PathVariable Long id,
                               @ModelAttribute("owner") Owner owner,
                               Model model) {

        // get owner from database by id
        Owner existingOwner = ownerService.getOwnerById(id);
        existingOwner.setId(id);
        existingOwner.setName(owner.getName());
        existingOwner.setPhone(owner.getPhone());
        existingOwner.setEmail(owner.getEmail());

        // save updated owner object
        ownerService.updateOwner(existingOwner);
        return "redirect:/owners";
    }

    // handler method to handle delete owner request

    @GetMapping("/owners/{id}")
    public String deleteOwner(@PathVariable Long id) {
        Authentication authentication = HumanResourceManagementSystemApplication.authenticatedUser;
        if (authentication != null) {
            ownerService.deleteOwnerById(id);
            return "redirect:/owners";
        } else {
            return "redirect:/login";
        }
    }
}