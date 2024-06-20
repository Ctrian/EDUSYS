package com.uce.edusys.repository.modelo;

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
@Table(name = "enfermeria")
public class Enfermeria {

	@Id
	@SequenceGenerator(name = "seq_enfermeria", sequenceName = "seq_enfermeria", allocationSize = 1)
	@GeneratedValue(generator = "seq_enfermeria", strategy = GenerationType.SEQUENCE)

	@Column(name = "enfe_id")
	private Integer id;

	@Column(name = "enfe_direccion")
	private String direccion;

	@Column(name = "enfe_telefono")
	private String telefono;

	// relaciones
	@OneToMany(mappedBy = "enfermeria")
	private List<Estu_Enfe> estu_Enfes;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "enfe_id_departamento")
	private Departamento departamento;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "enfe_id_doctor")
	private Doctor doctor;
}
