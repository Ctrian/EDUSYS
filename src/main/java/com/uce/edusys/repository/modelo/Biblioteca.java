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
@Table(name = "biblioteca")
public class Biblioteca {

	@Id
	@SequenceGenerator(name = "seq_biblioteca", sequenceName = "seq_biblioteca", allocationSize = 1)
	@GeneratedValue(generator = "seq_biblioteca", strategy = GenerationType.SEQUENCE)

	@Column(name = "bibl_id")
	private Integer id;

	@Column(name = "bibl_nombre")
	private String nombre;

	@Column(name = "bibl_direccion")
	private String direccion;

	@Column(name = "bibl_telefono")
	private String telefono;

	// relaciones
	@OneToMany(mappedBy = "biblioteca", cascade = CascadeType.ALL)
	private List<Estu_Bibl> estu_Bibls;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bibl_id_departamento")
	private Departamento departamento;

	@OneToMany(mappedBy = "biblioteca", cascade = CascadeType.ALL)
	private List<Bibliotecario> bibliotecarios;

}
