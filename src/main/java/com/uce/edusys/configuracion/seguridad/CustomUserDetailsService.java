package com.uce.edusys.configuracion.seguridad;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.uce.edusys.repository.IRepresentanteRepository;
import com.uce.edusys.repository.RepresentanteRepositoryImpl;
import com.uce.edusys.repository.modelo.Representante;

import java.util.ArrayList;

import org.slf4j.Logger;

public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private IRepresentanteRepository iRepresentanteRepository;

    @Autowired
    private RepresentanteRepositoryImpl repositoryImpl;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Intentando cargar usuario por email: {}", email);
        // Lógica para cargar el usuario desde la base de datos usando el email
        Representante representante = repositoryImpl.encontrarPorEmail(email);
        if (representante == null) {
            logger.error("Usuario no encontrado: {}", email);
            throw new UsernameNotFoundException("Usuario X no encontrado" + email);
        }
        logger.info("Usuario encontrado: {}", representante);

        return new CustomUserDetails(representante);
    }

}
