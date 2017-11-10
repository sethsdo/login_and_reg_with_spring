package com.project.LoginAndRegistration.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.LoginAndRegistration.models.User;
import com.project.LoginAndRegistration.repositories.RoleRepository;
import com.project.LoginAndRegistration.repositories.UserRepository;


@Service
public class UserService {
    private UserRepository userRepo;
    private RoleRepository roleRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepo, RoleRepository roleRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepo.findByName("ROLE_USER"));
        userRepo.save(user);
    }

    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepo.findByName("ROLE_ADMIN"));
        userRepo.save(user);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}