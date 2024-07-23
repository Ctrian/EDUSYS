package com.uce.edusys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uce.edusys.repository.IEstudianteRepository;
import com.uce.edusys.repository.IMatriculaRepository;
import com.uce.edusys.repository.IOfertaAcademicaRepository;
import com.uce.edusys.repository.IRepresentanteRepository;
import com.uce.edusys.repository.modelo.Estudiante;
import com.uce.edusys.repository.modelo.Matricula;
import com.uce.edusys.repository.modelo.OfertaAcademica;
import com.uce.edusys.repository.modelo.Representante;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

    private final IMatriculaRepository iMatriculaRepository;
    private final IOfertaAcademicaRepository iOfertaAcademicaRepository;
    private final IEstudianteRepository iEstudianteRepository;
    private final IRepresentanteRepository iRepresentanteRepository;

    @Autowired
    public MatriculaServiceImpl(IMatriculaRepository iMatriculaRepository,
            IOfertaAcademicaRepository iOfertaAcademicaRepository, IEstudianteRepository iEstudianteRepository,
            IRepresentanteRepository iRepresentanteRepository) {
        this.iMatriculaRepository = iMatriculaRepository;
        this.iOfertaAcademicaRepository = iOfertaAcademicaRepository;
        this.iEstudianteRepository = iEstudianteRepository;
        this.iRepresentanteRepository = iRepresentanteRepository;
    }

    @Override
    @Transactional
    public void registrarM(Integer ofertaAcademicaId, Integer estudianteId, Integer representanteId) {
        
        Estudiante estudiante = this.iEstudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        OfertaAcademica ofertaAcademica = this.iOfertaAcademicaRepository.findById(ofertaAcademicaId)
                .orElseThrow(() -> new RuntimeException("Oferta académica no encontrada"));
        Representante representante = this.iRepresentanteRepository.findById(representanteId)
                .orElseThrow(() -> new RuntimeException("Representante no encontrado"));

        Matricula matricula = new Matricula();
        matricula.setEstudiante(estudiante);
        matricula.setOfertaAcademica(ofertaAcademica);
        matricula.setRepresentante(representante);

        this.iMatriculaRepository.save(matricula);
    }

    @Override
    @Transactional
    public void actualizarM(Matricula matricula) {
        this.iMatriculaRepository.save(matricula);
    }

    @Override
    @Transactional(readOnly = true)
    public Matricula encontrarM(Integer id) {
        return this.iMatriculaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void borrarM(Integer id) {
        this.iMatriculaRepository.deleteById(id);
    }

}
