package com.uce.edusys.repository.modelo;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@SequenceGenerator(name = "seq_curso", sequenceName = "seq_curso", allocationSize = 1)
	@GeneratedValue(generator = "seq_curso", strategy = GenerationType.SEQUENCE)

	@Column(name = "curs_id")
	private Integer id;

	@Column(name = "curs_nombre")
	private String nombre;

	@Column(name = "curs_descripcion")
	private String descripcion;

	@Column(name = "curs_precio")
	private BigDecimal precio;

	@Column(name = "curs_horario")
	private String horario;

	// relaciones
	@OneToOne()
	@JoinColumn(name = "curs_id_departamento")
	private Departamento departamento;

	@ManyToMany(mappedBy = "cursos")
	private List<Estudiante> estudiantes;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "curs_id_profesor")
	private Profesor profesor;

	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
	private List<Curs_Mate> curs_Mates;

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

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public List<Curs_Mate> getCurs_Mates() {
		return curs_Mates;
	}

	public void setCurs_Mates(List<Curs_Mate> curs_Mates) {
		this.curs_Mates = curs_Mates;
	}
	
}
