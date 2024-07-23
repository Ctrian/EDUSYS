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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "representante")
public class Representante {

	@Id
	@SequenceGenerator(name = "seq_representante", sequenceName = "seq_representante", allocationSize = 1)
	@GeneratedValue(generator = "seq_representante", strategy = GenerationType.SEQUENCE)

	@Column(name = "repr_id")
	private Integer id;

	@NotEmpty
	@Column(name = "repr_cedula")
	private String cedula;

	@NotEmpty
	@Column(name = "repr_nombre")
	private String nombre;

	@NotEmpty
	@Column(name = "repr_apellido")
	private String apellido;

	@NotEmpty
	@Email
	@Column(name = "repr_email")
	private String email;

	// back
	@Column(name = "repr_fecha_login")
	private LocalDateTime fechaLogin = LocalDateTime.now();

	@NotEmpty
	@Column(name = "repr_direccion")
	private String direccion;

	@NotEmpty
	@Column(name = "repr_telefono")
	private String telefono;

	@Column(name = "repr_enabled")
	private boolean enabled = true;

	// cifrado RSA
	@NotEmpty
	@Column(name = "repr_password")
	private String password;

	// relaciones
	@OneToMany(mappedBy = "representante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Estudiante> estudiantes;

	@OneToMany(mappedBy = "representante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SolicitudMatricula> solicitudMatriculas;

	@OneToMany(mappedBy = "representante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Matricula> matriculas;

	@OneToMany(mappedBy = "representante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Transferencia> transferencias;

	@OneToMany(mappedBy = "representante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Repre_Conta> repre_Contas;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "representante_roles", joinColumns = @JoinColumn(name = "representante_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"representante_id", "rol_id" }))
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

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public List<Transferencia> getTransferencias() {
		return transferencias;
	}

	public void setTransferencias(List<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}

	public List<Repre_Conta> getRepre_Contas() {
		return repre_Contas;
	}

	public void setRepre_Contas(List<Repre_Conta> repre_Contas) {
		this.repre_Contas = repre_Contas;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getFechaLogin() {
		return fechaLogin;
	}

	public void setFechaLogin(LocalDateTime fechaLogin) {
		this.fechaLogin = fechaLogin;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public List<SolicitudMatricula> getSolicitudMatriculas() {
		return solicitudMatriculas;
	}

	public void setSolicitudMatriculas(List<SolicitudMatricula> solicitudMatriculas) {
		this.solicitudMatriculas = solicitudMatriculas;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	// toString

}
