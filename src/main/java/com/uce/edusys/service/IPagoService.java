package com.uce.edusys.service;

import java.util.List;

import com.uce.edusys.repository.modelo.Pago;

public interface IPagoService {

    public void registrarPago(Pago pago);

    public List<Pago> obtenerPagosPorMatricula(Integer id);

    public List<Pago> obtenerPagosPorFactura(Integer facturaId);
    
    List<Pago> obtenerPagosPorRepresentante(Integer representanteId);
    
}
