package com.uce.edusys.configuracion.seguridad;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.uce.edusys.repository.modelo.Personal;
import com.uce.edusys.repository.modelo.Representante;

public class CustomUserDetails implements UserDetails {

    private Representante representante;

    private Personal personal;

    public CustomUserDetails(Representante representante) {
        this.representante = representante;
    }

    public CustomUserDetails(Personal personal) {
        this.personal = personal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (representante != null) {
            return representante.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNombre()))
                    .collect(Collectors.toSet());
        } else if (personal != null) {
            return personal.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNombre()))
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    @Override
    public String getPassword() {
        if (representante != null) {
            return representante.getPassword();
        } else if (personal != null) {
            return personal.getPassword();
        }
        return null;
    }

    @Override
    public String getUsername() {
        if (representante != null) {
            return representante.getEmail();
        } else if (personal != null) {
            return personal.getEmail();
        }
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (representante != null) {
            return representante.isEnabled();
        } else if (personal != null) {
            return personal.isEnabled();
        }
        return false;
    }

}
