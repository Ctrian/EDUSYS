package com.uce.edusys.repository.modelo;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiante")
public class Estudiante {

	@Id
	@SequenceGenerator(name = "seq_estudiante", sequenceName = "seq_estudiante", allocationSize = 1)
	@GeneratedValue(generator = "seq_estudiante", strategy = GenerationType.SEQUENCE)

	@Column(name = "estu_id")
	private Integer id;

	@Column(name = "estu_cedula")
	private String cedula;

	@Column(name = "estu_nombre")
	private String nombre;

	@Column(name = "estu_apellido")
	private String apellido;

	@Column(name = "estu_fecha_nacimiento")
	private LocalDateTime fechaNacimiento;

	@Column(name = "estu_genero")
	private String genero;

	@Column(name = "estu_direccion")
	private String direccion;

	@Column(name = "estu_telefono")
	private String telefono;

	// relaciones

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "estu_id_representante")
	private Representante representante;

	@OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
	private List<Estu_Enfe> estu_Enfes;

	@OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
	private List<Estu_Bibl> estu_Bibls;

	@OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
	private List<Matricula> matriculas;
//
//	///////////////////////////////////////////////////////////////
//
//	@OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL)
//	private Comida comida;
//
//	@OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL)
//	private Taller taller;

	// get y set

	public String getCedula() {
		return cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	// toString

}
