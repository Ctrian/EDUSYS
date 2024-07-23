package com.uce.edusys.configuracion.seguridad;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uce.edusys.repository.modelo.Personal;
import com.uce.edusys.repository.modelo.Representante;
import com.uce.edusys.service.IPersonalService;
import com.uce.edusys.service.IRepresentanteService;

import org.slf4j.Logger;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final IRepresentanteService iRepresentanteService;
    private final IPersonalService iPersonalService;

    @Autowired
    public CustomUserDetailsService(IRepresentanteService representanteService, IPersonalService personalService) {
        this.iRepresentanteService = representanteService;
        this.iPersonalService = personalService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Intentando cargar usuario por email: {}", email);

        // Lógica para cargar el usuario desde la base de datos usando el email
        Representante representante = iRepresentanteService.encontrarPorEmail(email);
        if (representante != null) {
            logger.info("Usuario Representante encontrado: {}", representante);
            return new CustomUserDetails(representante);
        }

        Personal personal = iPersonalService.encontrarPorEmail(email);
        if (personal != null) {
            logger.info("Usuario Personal encontrado: {}", personal);
            return new CustomUserDetails(personal);
        }

        logger.error("Usuario no encontrado: {}", email);
            throw new UsernameNotFoundException("Usuario X no encontrado con email: " + email);
    }
}
