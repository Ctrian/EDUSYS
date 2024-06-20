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
@Table(name = "rrhh")
public class RRHH {

	@Id
	@SequenceGenerator(name = "seq_rrhh", sequenceName = "seq_rrhh", allocationSize = 1)
	@GeneratedValue(generator = "seq_rrhh", strategy = GenerationType.SEQUENCE)

	@Column(name = "rrhh_id")
	private Integer id;

	@Column(name = "rrhh_nombre")
	private String nombre;

	@Column(name = "rrhh_direccion")
	private String direccion;

	@Column(name = "rrhh_telefono")
	private String telefono;

	// relaciones
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rrhh_id_departamento")
	private Departamento departamento;

	@OneToMany(mappedBy = "rrhh", cascade = CascadeType.ALL)
	private List<Asesor> asesores;

}
