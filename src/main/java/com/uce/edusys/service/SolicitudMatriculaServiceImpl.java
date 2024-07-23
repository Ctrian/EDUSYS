package com.uce.edusys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uce.edusys.repository.ISolicitudMatriculaRepository;
import com.uce.edusys.repository.modelo.SolicitudMatricula;

@Service
public class SolicitudMatriculaServiceImpl implements ISolicitudMatriculaService {

    @Autowired
    public ISolicitudMatriculaRepository iSolicitudMatriculaRepository;

    @Override
    @Transactional
    public void crearSolicitud(SolicitudMatricula solicitudMatricula) {
        this.iSolicitudMatriculaRepository.save(solicitudMatricula);
    }

    @Override
    @Transactional
    public void actualizarSolicitud(SolicitudMatricula solicitudMatricula) {
        this.iSolicitudMatriculaRepository.save(solicitudMatricula);
    }

    @Override
    @Transactional(readOnly = true)
    public SolicitudMatricula encontrarSolicitud(Integer id) {
        return this.iSolicitudMatriculaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void borrarR(Integer id) {
        this.iSolicitudMatriculaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SolicitudMatricula> encontrarPorEstado(String estado) {
        return this.iSolicitudMatriculaRepository.findByEstado(estado);
    }

}
