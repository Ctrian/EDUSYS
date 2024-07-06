package com.uce.edusys.configuracion.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.uce.edusys.repository.modelo.Rol;

import jakarta.annotation.PostConstruct;

@Component

public class DataInitializer{

    @Autowired
    private IRolRepository iRolRepository;

    @PostConstruct
    public void init() {
        // Check if the role REPRESENTANTE exists, if not create it
        if (iRolRepository.findByNombre("REPRESENTANTE") == null) {
            Rol representanteRole = new Rol();
            representanteRole.setNombre("REPRESENTANTE");
            iRolRepository.save(representanteRole);
        }
    }
    
}
