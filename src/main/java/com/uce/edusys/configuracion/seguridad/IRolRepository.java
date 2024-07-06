package com.uce.edusys.configuracion.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uce.edusys.repository.modelo.Rol;

public interface IRolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
}
