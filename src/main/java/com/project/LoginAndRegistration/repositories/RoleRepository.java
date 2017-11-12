package com.project.LoginAndRegistration.repositories;


import com.project.LoginAndRegistration.models.Role;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findAll();

    List<Role> findByName(String name);
}