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
        return "dashboard";
    }

    @RequestMapping("/registration")
    public String registerForm(@Valid @ModelAttribute("user") User user) {
        return "registrationPage";
    }

    @PostMapping("registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            System.out.println("not successfull");
            return "registrationPage";
        }
        userService.saveWithUserRole(user);
        return "redirect:/dashboard";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Logout Successfull!");
        }
        return "loginPage";
    }


}