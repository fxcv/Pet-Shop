package me.springprojects.petshopbackend.security;

import lombok.AllArgsConstructor;
import me.springprojects.petshopbackend.entities.Authority;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

    private final Authority authority;

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
