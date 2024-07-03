package com.uce.edusys.configuracion.seguridad;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.uce.edusys.repository.modelo.Representante;

public class CustomUserDetails implements UserDetails {

    private Representante representante;

    public CustomUserDetails(Representante representante) {
        this.representante = representante;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
        return null;
    }

    @Override
    public String getPassword() {
        return representante.getPassword();
    }

    @Override
    public String getUsername() {
        return representante.getEmail();
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
        return true;
    }

    public String getUserName() {
        return representante.getNombre();
    }

    // public String getUserEmail() {
    //     return representante.getEmail();
    // }
}
