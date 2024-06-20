package com.uce.edusys.repository.modelo;

import java.time.LocalDateTime;
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
@Table(name = "representante")
public class Representante {

	@Id
	@SequenceGenerator(name = "seq_representante", sequenceName = "seq_representante", allocationSize = 1)
	@GeneratedValue(generator = "seq_representante", strategy = GenerationType.SEQUENCE)
	@Column(name = "repr_id")
	private Integer id;

	@Column(name = "repr_cedula")
	private String cedula;

	@Column(name = "repr_nombre")
	private String nombre;

	@Column(name = "repr_apellido")
	private String apellido;

	@Column(name = "repr_correo_electronico")
	private String correoElectronico;

	@Column(name = "repr_fecha_nacimiento")
	private LocalDateTime fechaNacimiento;

	@Column(name = "repr_direccion")
	private String direccion;

	@Column(name = "repr_telefono")
	private String telefono;

	// cifrado RSA

	@Column(name = "repr_normal_password")
	private String normalPassword;

	@Column(name = "repr_encrypted_password")
	private String encryptedPassword;

	// relaciones
	@OneToMany(mappedBy = "representante", cascade = CascadeType.ALL)
	private List<Estudiante> estudiantes;

	@OneToMany(mappedBy = "representante", cascade = CascadeType.ALL)
	private List<Transferencia> transferencias;

	@OneToMany(mappedBy = "representante", cascade = CascadeType.ALL)
	private List<Repre_Conta> repre_Contas;

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

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	public String getNormalPassword() {
		return normalPassword;
	}

	public void setNormalPassword(String normalPassword) {
		this.normalPassword = normalPassword;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	
	// toString
	
}
