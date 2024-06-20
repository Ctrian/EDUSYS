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
@Table(name = "estu_enfe")
public class Estu_Enfe {

	@Id
	@SequenceGenerator(name = "seq_estu_enfe", sequenceName = "seq_estu_enfe", allocationSize = 1)
	@GeneratedValue(generator = "seq_estu_enfe", strategy = GenerationType.SEQUENCE)

	@Column(name = "esen_id")
	private Integer id;

	@Column(name = "esen_fecha_cita")
	private LocalDateTime fechaCita;

	// relaciones
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "esen_id_estudiante")
	private Estudiante estudiante;
	
	@ManyToOne
	@JoinColumn(name = "esen_id_enfermeria")
	private Enfermeria enfermeria;
}
