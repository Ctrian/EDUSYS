package com.uce.edusys.configuracion.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uce.edusys.repository.modelo.Rol;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private IRolRepository iRolRepository;

    @PostConstruct
    public void init() {
        // Define the roles
        String[] roles = {"REPRESENTANTE", "ESTUDIANTE", "PROFESOR", "ADMIN", "USER"};

        // Check and create roles if they do not exist
        for (String roleName : roles) {
            if (iRolRepository.findByNombre(roleName) == null) {
                Rol role = new Rol();
                role.setNombre(roleName);
                iRolRepository.save(role);
            }
        }
        
    }

}
