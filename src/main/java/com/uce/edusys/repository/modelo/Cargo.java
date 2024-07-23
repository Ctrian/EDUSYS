package com.uce.edusys.repository.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "cargo")
public class Cargo {

    @Id
	@SequenceGenerator(name = "seq_cargo", sequenceName = "seq_cargo", allocationSize = 1)
	@GeneratedValue(generator = "seq_cargo", strategy = GenerationType.SEQUENCE)

	@Column(name = "carg_id")
	private Integer id;

    @Column(name = "carg_nombre")
	private String nombre;

    @Column(name = "carg_descripcion")
	private String descripcion;
    
    // get y set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
