package com.uce.edusys.repository.modelo;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "solicitud_matricula")
public class SolicitudMatricula {

    @Id
    @SequenceGenerator(name = "seq_solicitud_matricula", sequenceName = "seq_solicitud_matricula", allocationSize = 1)
    @GeneratedValue(generator = "seq_solicitud_matricula", strategy = GenerationType.SEQUENCE)

    @Column(name = "soma_id")
    private Integer id;

    @Column(name = "soma_fecha")
    private LocalDate fecha;

    @Column(name = "soma_estado")
    private String estado; // PENDIENTE, ACEPTADA, RECHAZADA

    // relaciones
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "soma_id_representante")
    private Representante representante;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "soma_id_estudiante")
    private Estudiante estudiante;

    // get y set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Representante getRepresentante() {
        return representante;
    }

    public void setRepresentante(Representante representante) {
        this.representante = representante;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    // Método para obtener la fecha formateada
    public String getFechaFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }
    
}
