package com.uce.edusys.repository.modelo;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

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

	@NotEmpty
	@Email
	@Column(name = "pers_email")
	private String email;

	@Column(name = "pers_direccion")
	private String direccion;

	@Column(name = "pers_fecha_nacimiento")
	private LocalDateTime fechaNacimiento;

	@Column(name = "pers_genero")
	private String genero;

	@Column(name = "pers_fecha_login")
	private LocalDateTime fechaLogin = LocalDateTime.now();

	@NotEmpty
	@Column(name = "pers_telefono")
	private String telefono;

	@Column(name = "pers_enabled")
    private boolean enabled = true;

	// cifrado RSA
	@NotEmpty
	@Column(name = "pers_password")
	private String password;

	// relaciones
	// @OneToOne(mappedBy = "personal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// private Doctor doctor;

	// @OneToOne(mappedBy = "personal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// private Contador contador;

	// @OneToOne(mappedBy = "personal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// private Profesor profesor;

	// @OneToOne(mappedBy = "personal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// private Bibliotecario bibliotecario;

	// @OneToOne(mappedBy = "personal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// private Autoridad autoridad;

	// @OneToOne(mappedBy = "personal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// private Asesor asesor;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "personal_roles", joinColumns = @JoinColumn(name = "personal_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"personal_id", "rol_id" }))
	private Set<Rol> roles = new HashSet<>();

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

	// public Doctor getDoctor() {
	// 	return doctor;
	// }

	// public void setDoctor(Doctor doctor) {
	// 	this.doctor = doctor;
	// }

	// public Contador getContador() {
	// 	return contador;
	// }

	// public void setContador(Contador contador) {
	// 	this.contador = contador;
	// }

	// public Profesor getProfesor() {
	// 	return profesor;
	// }

	// public void setProfesor(Profesor profesor) {
	// 	this.profesor = profesor;
	// }

	// public Bibliotecario getBibliotecario() {
	// 	return bibliotecario;
	// }

	// public void setBibliotecario(Bibliotecario bibliotecario) {
	// 	this.bibliotecario = bibliotecario;
	// }

	// public Autoridad getAutoridad() {
	// 	return autoridad;
	// }

	// public void setAutoridad(Autoridad autoridad) {
	// 	this.autoridad = autoridad;
	// }

	// public Asesor getAsesor() {
	// 	return asesor;
	// }

	// public void setAsesor(Asesor asesor) {
	// 	this.asesor = asesor;
	// }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getFechaLogin() {
		return fechaLogin;
	}

	public void setFechaLogin(LocalDateTime fechaLogin) {
		this.fechaLogin = fechaLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}