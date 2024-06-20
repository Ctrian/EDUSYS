package com.uce.edusys.repository.modelo;

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
@Table(name = "curs_mate")
public class Curs_Mate {

	@Id
	@SequenceGenerator(name = "seq_curs_mate", sequenceName = "seq_curs_mate", allocationSize = 1)
	@GeneratedValue(generator = "seq_curs_mate", strategy = GenerationType.SEQUENCE)

	@Column(name = "cuma_id")
	private Integer id;

	// relaciones
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cuma_id_curso")
	private Curso curso;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cuma_id_materia")
	private Materia materia;

}
