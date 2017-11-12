package com.project.LoginAndRegistration.repositories;

import com.project.LoginAndRegistration.models.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findByUsername(String username);
}