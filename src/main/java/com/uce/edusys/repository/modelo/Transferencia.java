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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "transferencia")
public class Transferencia {

	@Id
	@SequenceGenerator(name = "seq_transferencia", sequenceName = "seq_transferencia", allocationSize = 1)
	@GeneratedValue(generator = "seq_transferencia", strategy = GenerationType.SEQUENCE)

	@Column(name = "tran_id")
	private Integer id;

	@Column(name = "tran_numero_referencia")
	private String numeroReferencia;

	@Column(name = "tran_fecha")
	private LocalDateTime fecha;

	@Column(name = "tran_monto")
	private BigDecimal monto;

	@Column(name = "tran_banco")
	private String banco;

	// relaciones
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tran_id_representante")
	private Representante representante;

	@OneToMany(mappedBy = "transferencia", cascade = CascadeType.ALL)
	private List<Pago> pagos;

}
