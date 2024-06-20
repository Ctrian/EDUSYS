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

}
