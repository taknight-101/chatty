package com.webTraining.chatty.repositories ;

import com.webTraining.chatty.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users, Integer> {
    public Optional<Users> findByUsername(String username);


}
