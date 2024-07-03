package com.uce.edusys.repository.modelo;

import java.time.LocalDateTime;

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

@Entity
@Table(name = "matricula")
public class Matricula {

	@Id
	@SequenceGenerator(name = "seq_matricula", sequenceName = "seq_matricula", allocationSize = 1)
	@GeneratedValue(generator = "seq_matricula", strategy = GenerationType.SEQUENCE)

	@Column(name = "matr_id")
	private Integer id;

	@Column(name = "matr_fecha")
	private LocalDateTime fecha = LocalDateTime.now();

	@Column(name = "matr_nombre_repr")
	private String nombreRepresentante;

	@Column(name = "matr_cedula_repr")
	private String cedulaRepresentante;

	@Column(name = "matr_email_repr")
	private String emailRepresentante;

	@Column(name = "matr_telefono_repr")
	private String telefonoRepresentante;

	@Column(name = "matr_actividad_repr")
	private String actividadRepresentante;

	@Column(name = "matr_lugar_repr")
	private String lugarRepresentante;

	// <---------- ESTUDIANTE ----------->

	@Column(name = "matr_nombre_estu")
	private String nombreEstudiante;

	@Column(name = "matr_bd_estu")
	private LocalDateTime bdEstudiante;

	@Column(name = "matr_cedula_estu")
	private String cedulaEstudiante;

	@Column(name = "matr_grade_estu")
	private String gradeEstudiante;

	// relaciones
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "matr_id_representante")
	private Representante representante;

	@ManyToOne
	@JoinColumn(name = "matr_id_estudiante")
	private Estudiante estudiante;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "matr_id_curso")
	private Curso curso;

	// get y set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

}
