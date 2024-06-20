package com.uce.edusys.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Table(name = "factura")
public class Factura {

	@Id
	@SequenceGenerator(name = "seq_factura", sequenceName = "seq_factura", allocationSize = 1)
	@GeneratedValue(generator = "seq_factura", strategy = GenerationType.SEQUENCE)

	@Column(name = "fact_id")
	private Integer id;

	@Column(name = "fact_fecha_emision")
	private LocalDateTime fechaEmision;

	@Column(name = "fact_monto_total")
	private BigDecimal montoTotal;

	@Column(name = "fact_estado")
	private String estado;

	// relaciones
	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
	private List<Pago> pagos;

}
