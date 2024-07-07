package com.uce.edusys.repository.modelo;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

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

	@Column(name = "estu_email")
	private String email;

	// relaciones

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "estu_id_representante")
	private Representante representante;

	@OneToMany(mappedBy = "estudiante")
	private List<Matricula> matriculas;

	@OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
	private List<Estu_Enfe> estu_Enfes;

	@OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
	private List<Estu_Bibl> estu_Bibls;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "estudiante_roles",
		joinColumns = @JoinColumn(name = "estudiante_id"), 
		inverseJoinColumns = @JoinColumn(name = "role_id"), 
		uniqueConstraints = @UniqueConstraint(columnNames = {"estudiante_id", "rol_id" }))
	private Set<Rol> roles = new HashSet<>();

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public List<Estu_Enfe> getEstu_Enfes() {
		return estu_Enfes;
	}

	public void setEstu_Enfes(List<Estu_Enfe> estu_Enfes) {
		this.estu_Enfes = estu_Enfes;
	}

	public List<Estu_Bibl> getEstu_Bibls() {
		return estu_Bibls;
	}

	public void setEstu_Bibls(List<Estu_Bibl> estu_Bibls) {
		this.estu_Bibls = estu_Bibls;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	// toString

}
