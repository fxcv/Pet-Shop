package me.springprojects.petshopbackend.security;

import lombok.AllArgsConstructor;
import me.springprojects.petshopbackend.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SecurityUserDetails implements UserDetails {

    private final User user;
    private final PasswordEncoder passwordEncoder;


    @Override
    public String getPassword() {
        return passwordEncoder.encode(user.getPassword());
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities().stream()
                                    .map(SecurityAuthority::new)
                                    .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        LocalDate localDate = LocalDate.now();
        return !localDate.isAfter(user.getExpirationDate());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !user.isCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
