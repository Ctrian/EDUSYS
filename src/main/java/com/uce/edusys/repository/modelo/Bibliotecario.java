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
@Table(name = "bibliotecario")
public class Bibliotecario {

	@Id
	@SequenceGenerator(name = "seq_bibliotecario", sequenceName = "seq_bibliotecario", allocationSize = 1)
	@GeneratedValue(generator = "seq_bibliotecario", strategy = GenerationType.SEQUENCE)

	@Column(name = "bibl_id")
	private Integer id;

	@Column(name = "bibl_cedula")
	private String cedula;

	@Column(name = "bibl_nombre")
	private String nombre;

	@Column(name = "bibl_apellido")
	private String apellido;

	@Column(name = "bibl_fecha_nacimiento")
	private LocalDateTime fechaNacimiento;

	@Column(name = "bibl_genero")
	private String genero;

	@Column(name = "bibl_direccion")
	private String direccion;

	@Column(name = "bibl_telefono")
	private String telefono;

	@Column(name = "bibl_email")
	private String email;

	// relaciones
	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "bibl_id_personal")
	// private Personal personal;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "conta_id_contabilidad")
	private Biblioteca biblioteca;
}
