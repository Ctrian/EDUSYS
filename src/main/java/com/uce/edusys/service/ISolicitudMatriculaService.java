package com.uce.edusys.service;

import java.util.List;

import com.uce.edusys.repository.modelo.SolicitudMatricula;

public interface ISolicitudMatriculaService {
    
    public void crearSolicitud(SolicitudMatricula solicitudMatricula);

	public void actualizarSolicitud(SolicitudMatricula solicitudMatricula);

    public SolicitudMatricula encontrarSolicitud(Integer id);

	public void borrarR(Integer id);

    public List<SolicitudMatricula> encontrarPorEstado(String estado);
}
