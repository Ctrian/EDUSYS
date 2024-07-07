package com.uce.edusys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uce.edusys.repository.IMatriculaRepository;
import com.uce.edusys.repository.IPagoRepository;
import com.uce.edusys.repository.modelo.Matricula;
import com.uce.edusys.repository.modelo.Pago;

@Service
public class PagoServiceImpl implements IPagoService {

    @Autowired
    private IPagoRepository iPagoRepository;

    @Autowired
    private IMatriculaRepository iMatriculaRepository; 

    @Override
    @Transactional
    public void registrarPago(Pago pago) {
        iPagoRepository.save(pago);
        Matricula matricula = pago.getMatricula();
        matricula.actualizarEstado();
        iMatriculaRepository.save(matricula);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> obtenerPagosPorMatricula(Integer id) {
        return iPagoRepository.findByMatriculaId(id);
    }
    
}
