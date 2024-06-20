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
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "contador")
public class Contador {

	@Id
	@SequenceGenerator(name = "seq_contador", sequenceName = "seq_contador", allocationSize = 1)
	@GeneratedValue(generator = "seq_contador", strategy = GenerationType.SEQUENCE)

	@Column(name = "conta_id")
	private Integer id;

	@Column(name = "conta_cedula")
	private String cedula;

	@Column(name = "conta_nombre")
	private String nombre;

	@Column(name = "conta_apellido")
	private String apellido;

	@Column(name = "conta_fecha_nacimiento")
	private LocalDateTime fechaNacimiento;

	@Column(name = "conta_genero")
	private String genero;

	@Column(name = "conta_direccion")
	private String direccion;

	@Column(name = "conta_telefono")
	private String telefono;

	@Column(name = "conta_email")
	private String email;

	// relaciones
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "doct_id_personal")
	Personal personal;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "conta_id_contabilidad")
	private Contabilidad contabilidad;

}
