package me.springprojects.petshopbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    private boolean locked;
    @Column(name = "credentials_expired")
    private boolean credentialsExpired;
    private boolean enabled;

    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id")
                                      , inverseJoinColumns = @JoinColumn(name = "auth_id"))
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities;
}
