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
@Table(name = "contabilidad")
public class Contabilidad {

	@Id
	@SequenceGenerator(name = "seq_contabilidad", sequenceName = "seq_contabilidad", allocationSize = 1)
	@GeneratedValue(generator = "seq_contabilidad", strategy = GenerationType.SEQUENCE)

	@Column(name = "conta_id")
	private Integer id;

	@Column(name = "conta_nombre")
	private String nombre;

	@Column(name = "conta_direccion")
	private String direccion;

	@Column(name = "conta_telefono")
	private String telefono;

	// relaciones
	@OneToMany(mappedBy = "contabilidad", cascade = CascadeType.ALL)
	private List<Repre_Conta> repre_Contas;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "conta_id_departamento")
	private Departamento departamento;

	@OneToMany(mappedBy = "contabilidad", cascade = CascadeType.ALL)
	private List<Contador> contadores;
}
