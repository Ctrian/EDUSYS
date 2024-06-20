package com.uce.edusys.repository.modelo;

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
@Table(name = "materia")
public class Materia {

	@Id
	@SequenceGenerator(name = "seq_materia", sequenceName = "seq_materia", allocationSize = 1)
	@GeneratedValue(generator = "seq_materia", strategy = GenerationType.SEQUENCE)

	@Column(name = "mate_id")
	private Integer id;

	@Column(name = "mate_nombre")
	private String nombre;

	@Column(name = "mate_descripcion")
	private String descripcion;

	// relaciones
	@OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
	private List<Curs_Mate> curs_Mates;

}
