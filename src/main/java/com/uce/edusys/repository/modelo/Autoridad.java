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
@Table(name = "autoridad")
public class Autoridad {

	@Id
	@SequenceGenerator(name = "seq_autoridad", sequenceName = "seq_autoridad", allocationSize = 1)
	@GeneratedValue(generator = "seq_autoridad", strategy = GenerationType.SEQUENCE)

	@Column(name = "auto_id")
	private Integer id;

	@Column(name = "auto_cedula")
	private String cedula;

	@Column(name = "auto_nombre")
	private String nombre;

	@Column(name = "auto_apellido")
	private String apellido;

	@Column(name = "auto_fecha_nacimiento")
	private LocalDateTime fechaNacimiento;

	@Column(name = "auto_genero")
	private String genero;

	@Column(name = "auto_direccion")
	private String direccion;

	@Column(name = "auto_telefono")
	private String telefono;

	@Column(name = "auto_email")
	private String email;

	// relaciones
	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "auto_id_personal")
	// private Personal personal;

	@OneToMany(mappedBy = "autoridad", cascade = CascadeType.ALL)
	private List<Departamento> departamentos;

}
