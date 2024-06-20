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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "profesor")
public class Profesor {

	@Id
	@SequenceGenerator(name = "seq_profesor", sequenceName = "seq_profesor", allocationSize = 1)
	@GeneratedValue(generator = "seq_profesor", strategy = GenerationType.SEQUENCE)

	@Column(name = "prof_id")
	private Integer id;

	@Column(name = "prof_cedula")
	private String cedula;

	@Column(name = "prof_nombre")
	private String nombre;

	@Column(name = "prof_apellido")
	private String apellido;

	@Column(name = "prof_fecha_nacimiento")
	private LocalDateTime fechaNacimiento;

	@Column(name = "prof_genero")
	private String genero;

	@Column(name = "prof_direccion")
	private String direccion;

	@Column(name = "prof_telefono")
	private String telefono;

	@Column(name = "prof_email")
	private String email;

	// relaciones
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "prof_id_personal")
	private Personal personal;

	@OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
	private List<Curso> cursos;
}
