package com.project.LoginAndRegistration.controllers;


import com.project.LoginAndRegistration.models.User;
import com.project.LoginAndRegistration.services.UserService;
import com.project.LoginAndRegistration.validators.UserValidator;

import java.security.Principal;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Users {

    private UserService userService;
    private UserValidator userValidator;

    public Users(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @RequestMapping(value = { "/", "/dashboard" })
    public String home(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        // for (User user : userService.allUsers()) {
        //     // System.out.println(user.getRoles());
        // }
        // System.out.println(userService.allUsers().size());
        return "dashboard";
    }

    @RequestMapping("/loginAndReg")
    public String logingAndReg(@Valid @ModelAttribute("user") User user) {
        return "index";
    }

    
    // @RequestMapping("/registration")
    // public String registerForm(@Valid @ModelAttribute("user") User user) {
    //     return "registrationPage";
    // }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            // System.out.println("not successfull");
            return "index";
        }
        if (userService.allUsers().size() > 0) {
            // System.out.println("length is less then 0");
            userService.saveWithUserRole(user);
            return "redirect:/dashboard";
        } else {
            // System.out.println("length is greater then 0");
            userService.saveUserWithAdminRole(user);
            return "redirect:/dashboard";
        }
    }

    @RequestMapping("/admin")
    public String adminPage(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        model.addAttribute("users", userService.allUsers());
        return "adminPage";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute("user") User user, @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, Model model) {
        model.addAttribute("registered", "Successful Registration! Please Login...");
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Logout Successfull!");
        }
        return "index";
    }
    @RequestMapping("/admin/removeUser/{id}")
    public String removeUser(@PathVariable("id") Long id) {

        System.out.println("in remove");
        userService.destroyUser(id);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/addAdmin/{id}")
    public String addAdmin(@PathVariable("id") Long id) {
        User current = userService.findUserById(id);
        userService.saveUserWithAdminRole(current);
        return "redirect:/admin";
    }

}