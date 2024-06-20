package com.uce.edusys.repository.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@SequenceGenerator(name = "seq_curso", sequenceName = "seq_curso", allocationSize = 1)
	@GeneratedValue(generator = "seq_curso", strategy = GenerationType.SEQUENCE)

	@Column(name = "curs_id")
	private Integer id;

	@Column(name = "curs_nombre")
	private String nombre;

	@Column(name = "curs_horario")
	private String horario;

	// relaciones
	@OneToOne()
	@JoinColumn(name = "curs_id_departamento")
	private Departamento departamento;

	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
	private List<Matricula> matriculas;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "curs_id_profesor")
	private Profesor profesor;

	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
	private List<Curs_Mate> curs_Mates;
}
