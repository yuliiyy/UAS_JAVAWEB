package com.uas.humanresource.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.uas.humanresource.HumanResourceManagementSystemApplication;
import com.uas.humanresource.entity.Staff;
import com.uas.humanresource.service.StaffService;

@Controller
public class StaffController {

    private StaffService staffService;

    public StaffController(StaffService staffService) {
        super();
        this.staffService = staffService;
    }

    @GetMapping("/staffs")
    public String listStaffs(Model model) {
        Authentication authentication = HumanResourceManagementSystemApplication.authenticatedUser;

        if (authentication != null) {

            model.addAttribute("adminRole", authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));
            model.addAttribute("userRole", authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));

            model.addAttribute("staffs", staffService.getAllStaffs());
            return "staffs";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("/staffs/new")
    public String createStaffForm(Model model) {

        Authentication authentication = HumanResourceManagementSystemApplication.authenticatedUser;

        if (authentication != null) {
            model.addAttribute("adminRole", authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));

            if (model.getAttribute("adminRole") == null) {
                return "redirect:/home";
            }

            Staff staff = new Staff();
            model.addAttribute("staff", staff);
            return "create_staff";
        } else {
            return "redirect:/login";
        }

    }

    @PostMapping("/staffs")
    public String saveStaff(@ModelAttribute("staff") Staff staff) {
        staffService.saveStaff(staff);
        return "redirect:/staffs";
    }

    @GetMapping("/staffs/edit/{id}")
    public String editStaffForm(@PathVariable Long id, Model model) {
        Authentication authentication = HumanResourceManagementSystemApplication.authenticatedUser;
        if (authentication != null) {
            model.addAttribute("staff", staffService.getStaffById(id));
            return "edit_staff";
        } else {
            return "redirect:/login";
        }

    }

    @PostMapping("/staffs/{id}")
    public String updateStaff(@PathVariable Long id,
                               @ModelAttribute("staff") Staff staff,
                               Model model) {

        Staff existingStaff = staffService.getStaffById(id);
        existingStaff.setId(id);
        existingStaff.setFirstName(staff.getFirstName());
        existingStaff.setLastName(staff.getLastName());
        existingStaff.setEmail(staff.getEmail());

        staffService.updateStaff(existingStaff);
        return "redirect:/staffs";

    }

    @GetMapping("/staffs/{id}")
    public String deleteStaff(@PathVariable Long id) {
        Authentication authentication = HumanResourceManagementSystemApplication.authenticatedUser;
        if (authentication != null) {
            staffService.deleteStaffById(id);
            return "redirect:/staffs";
        } else {
            return "redirect:/login";
        }

    }
}
