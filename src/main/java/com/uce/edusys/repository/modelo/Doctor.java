package com.uce.edusys.repository.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor {

	@Id
	@SequenceGenerator(name = "seq_doctor", sequenceName = "seq_doctor", allocationSize = 1)
	@GeneratedValue(generator = "seq_doctor", strategy = GenerationType.SEQUENCE)

	@Column(name = "doct_id")
	private Integer id;

	@Column(name = "doct_cedula")
	private String cedula;

	@Column(name = "doct_nombre")
	private String nombre;

	@Column(name = "doct_apellido")
	private String apellido;

	@Column(name = "doct_fecha_nacimiento")
	private LocalDateTime fechaNacimiento;

	@Column(name = "doct_genero")
	private String genero;

	@Column(name = "doct_direccion")
	private String direccion;

	@Column(name = "doct_telefono")
	private String telefono;

	@Column(name = "doct_email")
	private String email;

	// relaciones
	@OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
	private Enfermeria enfermeria;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "doct_id_personal")
	Personal personal;
}
