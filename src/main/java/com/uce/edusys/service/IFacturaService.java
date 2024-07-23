package com.uce.edusys.service;

import com.uce.edusys.repository.modelo.Factura;

public interface IFacturaService {
    
    Factura obtenerFacturaPorId(Integer id);
    
    Factura encontrarPorId(Integer id);
    
    Factura obtenerFacturaPorRepresentante (Integer id);
}
