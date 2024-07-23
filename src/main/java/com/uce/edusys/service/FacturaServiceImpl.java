package com.uce.edusys.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edusys.repository.IFacturaRepository;
import com.uce.edusys.repository.modelo.Factura;

@Service
public class FacturaServiceImpl implements IFacturaService {

    @Autowired
    private IFacturaRepository iFacturaRepository;

    @Override
    public Factura encontrarPorId(Integer id) {
        Optional<Factura> factura = iFacturaRepository.findById(id);
        return factura.orElse(null);
    }

    @Override
    public Factura obtenerFacturaPorId(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Factura obtenerFacturaPorRepresentante(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }
}
