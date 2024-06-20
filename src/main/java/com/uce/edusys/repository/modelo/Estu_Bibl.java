package com.uce.edusys.repository.modelo;

import java.time.LocalDateTime;

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
@Table(name = "estu_bibl")
public class Estu_Bibl {

	@Id
	@SequenceGenerator(name = "seq_estu_bibl", sequenceName = "seq_estu_bibl", allocationSize = 1)
	@GeneratedValue(generator = "seq_estu_bibl", strategy = GenerationType.SEQUENCE)

	@Column(name = "esbi_id")
	private Integer id;

	@Column(name = "esen_fecha_visita")
	private LocalDateTime fechaVisita;

	// relaciones
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "esbi_id_estudiante")
	private Estudiante estudiante;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "esbi_id_biblioteca")
	private Biblioteca biblioteca;
}
