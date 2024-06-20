package com.uce.edusys.repository.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento {

	@Id
	@SequenceGenerator(name = "seq_departamento", sequenceName = "seq_departamento", allocationSize = 1)
	@GeneratedValue(generator = "seq_departamento", strategy = GenerationType.SEQUENCE)

	@Column(name = "depa_id")
	private Integer id;

	@Column(name = "depa_nombre")
	private String nombre;

	@Column(name = "depa_direccion")
	private String direccion;

	@Column(name = "depa_telefono")
	private String telefono;

	// relaciones
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "depa_id_autoridad")
	private Autoridad autoridad;

	@OneToOne(mappedBy = "departamento", cascade = CascadeType.ALL)
	private Enfermeria enfermeria;

	@OneToOne(mappedBy = "departamento", cascade = CascadeType.ALL)
	private Contabilidad contabilidad;

	@OneToOne(mappedBy = "departamento", cascade = CascadeType.ALL)
	private Curso curso;

	@OneToOne(mappedBy = "departamento", cascade = CascadeType.ALL)
	private RRHH rrhh;

}
