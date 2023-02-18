package me.springprojects.petshopbackend.security;

import lombok.AllArgsConstructor;
import me.springprojects.petshopbackend.entities.User;
import me.springprojects.petshopbackend.repositories.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);

        return user.map(u -> new SecurityUserDetails(u, passwordEncoder))
                   .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
    }
}
