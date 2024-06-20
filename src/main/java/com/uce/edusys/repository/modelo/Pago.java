package com.uce.edusys.repository.modelo;

import java.math.BigDecimal;

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
@Table(name = "pago")
public class Pago {

	@Id
	@SequenceGenerator(name = "seq_pago", sequenceName = "seq_pago", allocationSize = 1)
	@GeneratedValue(generator = "seq_pago", strategy = GenerationType.SEQUENCE)

	@Column(name = "pago_id")
	private Integer id;

	@Column(name = "pago_monto")
	private BigDecimal monto;

	// relaciones
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pago_id_transferencia")
	private Transferencia transferencia;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pago_id_factura")
	private Factura factura;
}
