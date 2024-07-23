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
@Table(name = "asesor")
public class Asesor {

	@Id
	@SequenceGenerator(name = "seq_asesor", sequenceName = "seq_asesor", allocationSize = 1)
	@GeneratedValue(generator = "seq_asesor", strategy = GenerationType.SEQUENCE)

	@Column(name = "ases_id")
	private Integer id;

	@Column(name = "ases_cedula")
	private String cedula;

	@Column(name = "ases_nombre")
	private String nombre;

	@Column(name = "ases_apellido")
	private String apellido;

	@Column(name = "ases_fecha_nacimiento")
	private LocalDateTime fechaNacimiento;

	@Column(name = "ases_genero")
	private String genero;

	@Column(name = "ases_direccion")
	private String direccion;

	@Column(name = "ases_telefono")
	private String telefono;

	@Column(name = "ases_email")
	private String email;

	// relaciones
	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "ases_id_personal")
	// Personal personal;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ases_id_rrhh")
	private RRHH rrhh;
}
