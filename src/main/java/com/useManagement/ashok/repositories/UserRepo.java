package com.useManagement.ashok.repositories;

import com.useManagement.ashok.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {

    Users findByEmailAndPassword(String email, String password);

    Users findByEmail(String email);
}
