package me.springprojects.petshopbackend.repositories;

import me.springprojects.petshopbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    public Optional<User> findUserByUsername(String username);
}
