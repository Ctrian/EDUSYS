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
@Table(name = "repre_conta")
public class Repre_Conta {

	@Id
	@SequenceGenerator(name = "seq_repre_conta", sequenceName = "seq_repre_conta", allocationSize = 1)
	@GeneratedValue(generator = "seq_repre_conta", strategy = GenerationType.SEQUENCE)

	@Column(name = "reco_id")
	private Integer id;

	@Column(name = "reco_fecha")
	private LocalDateTime fecha;

	// relaciones
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reco_id_representante")
	private Representante representante;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reco_id_contabilidad")
	private Contabilidad contabilidad;

}
