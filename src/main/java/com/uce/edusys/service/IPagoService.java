package com.uce.edusys.service;

import java.util.List;

import com.uce.edusys.repository.modelo.Pago;

public interface IPagoService {

    public void registrarPago(Pago pago);

    public List<Pago> obtenerPagosPorMatricula(Integer id);
}
