package com.uce.edusys.repository.modelo;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @SequenceGenerator(name = "seq_roles", sequenceName = "seq_roles", allocationSize = 1)
	@GeneratedValue(generator = "seq_roles", strategy = GenerationType.SEQUENCE)

    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", nullable = false, unique = true)
    private String nombre;

    // relaciones

    @ManyToMany(mappedBy = "roles")
    private Set<Representante> representantes;

    // get y set

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Representante> getRepresentantes() {
        return representantes;
    }

    public void setRepresentantes(Set<Representante> representantes) {
        this.representantes = representantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
