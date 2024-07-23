package com.uce.edusys.repository.modelo;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "oferta_academica")
public class OfertaAcademica {

    @Id
    @SequenceGenerator(name = "seq_oferta_academica", sequenceName = "seq_oferta_academica", allocationSize = 1)
    @GeneratedValue(generator = "seq_oferta_academica", strategy = GenerationType.SEQUENCE)

    @Column(name = "ofac_id")
    private Integer id;

    @Column(name = "ofac_nombre")
    private String nombre;

    @Column(name = "ofac_descripcion")
    private String descripcion;

    @Column(name = "ofac_precio")
    private BigDecimal precio;

    @Column(name = "ofac_hora")
    private String hora;

    // relaciones
    @OneToMany(mappedBy = "ofertaAcademica")
    private List<Matricula> matriculas;

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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

}
