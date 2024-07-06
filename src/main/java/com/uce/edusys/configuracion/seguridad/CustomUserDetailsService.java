package com.uce.edusys.configuracion.seguridad;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uce.edusys.repository.modelo.Representante;
import com.uce.edusys.service.IRepresentanteService;

import org.slf4j.Logger;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final IRepresentanteService iRepresentanteService;

    @Autowired
    public CustomUserDetailsService(IRepresentanteService representanteService) {
        this.iRepresentanteService = representanteService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Intentando cargar usuario por email: {}", email);

        // Lógica para cargar el usuario desde la base de datos usando el email
        Representante representante = iRepresentanteService.encontrarPorEmail(email);
        if (representante == null) {
            logger.error("Usuario no encontrado: {}", email);
            throw new UsernameNotFoundException("Usuario X no encontrado" + email);
        }

        logger.info("Usuario XXXXXXX encontrado: {}", representante);

        // // Aquí deberías cargar las autoridades (roles) del usuario
        // Set<SimpleGrantedAuthority> authorities = representante.getRoles().stream()
        // .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNombre()))
        // .collect(Collectors.toSet());

        // System.out.println("Contraseña cifrada en la base de datos: " + representante.getPassword()
        //         + "//////////////////////////////////");

        return new CustomUserDetails(representante);
    }

}
