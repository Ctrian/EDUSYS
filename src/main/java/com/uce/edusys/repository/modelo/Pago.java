package com.uce.edusys.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

	@Column(name = "pago_fecha")
	private LocalDate fecha;

	@Enumerated(EnumType.STRING)
	private EstadoPago estado;

	// relaciones

	@ManyToOne
	@JoinColumn(name = "matricula_id")
	private Matricula matricula;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pago_id_transferencia")
	private Transferencia transferencia;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pago_id_factura")
	private Factura factura;

	// get y set

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public EstadoPago getEstado() {
		return estado;
	}

	public void setEstado(EstadoPago estado) {
		this.estado = estado;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Transferencia getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

}

enum EstadoPago {
	PENDIENTE,
	COMPLETO,
	EN_PROCESO
}
