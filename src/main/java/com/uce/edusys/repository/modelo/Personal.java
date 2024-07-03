package com.uce.edusys.repository.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "personal")
public class Personal {

	@Id
	@SequenceGenerator(name = "seq_personal", sequenceName = "seq_personal", allocationSize = 1)
	@GeneratedValue(generator = "seq_personal", strategy = GenerationType.SEQUENCE)

	@Column(name = "pers_id")
	private Integer id;

	@Column(name = "pers_cedula")
	private String cedula;

	@Column(name = "pers_nombre")
	private String nombre;

	@Column(name = "pers_apellido")
	private String apellido;

	@Column(name = "pers_fecha_nacimiento")
	private LocalDateTime fechaNacimiento;

	@Column(name = "pers_genero")
	private String genero;

	@Column(name = "pers_direccion")
	private String direccion;

	// relaciones
	@OneToOne(mappedBy = "personal", cascade = CascadeType.ALL)
	private Doctor doctor;

	@OneToOne(mappedBy = "personal", cascade = CascadeType.ALL)
	private Contador contador;

	@OneToOne(mappedBy = "personal", cascade = CascadeType.ALL)
	private Profesor profesor;

	@OneToOne(mappedBy = "personal", cascade = CascadeType.ALL)
	private Bibliotecario bibliotecario;

	@OneToOne(mappedBy = "personal", cascade = CascadeType.ALL)
	private Autoridad autoridad;

	@OneToOne(mappedBy = "personal", cascade = CascadeType.ALL)
	private Asesor asesor;

	// get y set

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Contador getContador() {
		return contador;
	}

	public void setContador(Contador contador) {
		this.contador = contador;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Bibliotecario getBibliotecario() {
		return bibliotecario;
	}

	public void setBibliotecario(Bibliotecario bibliotecario) {
		this.bibliotecario = bibliotecario;
	}

	public Autoridad getAutoridad() {
		return autoridad;
	}

	public void setAutoridad(Autoridad autoridad) {
		this.autoridad = autoridad;
	}

	public Asesor getAsesor() {
		return asesor;
	}

	public void setAsesor(Asesor asesor) {
		this.asesor = asesor;
	}

}
