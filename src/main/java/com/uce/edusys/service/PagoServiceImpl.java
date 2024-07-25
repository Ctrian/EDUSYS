package com.uce.edusys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uce.edusys.repository.IPagoRepository;
import com.uce.edusys.repository.modelo.Pago;

@Service
public class PagoServiceImpl implements IPagoService {

    @Autowired
    private IPagoRepository pagoRepository;

    @Override
    @Transactional
    public void registrarPago(Pago pago) {
        pagoRepository.save(pago);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> obtenerPagosPorMatricula(Integer id) {
        return pagoRepository.findByMatriculaId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> obtenerPagosPorFactura(Integer facturaId) {
        return pagoRepository.findByFacturaId(facturaId);
    }

	@Override
	public List<Pago> obtenerPagosPorRepresentante(Integer representanteId) {
		return null;
	}

}
