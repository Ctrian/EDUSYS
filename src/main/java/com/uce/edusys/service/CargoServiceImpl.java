package com.uce.edusys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uce.edusys.repository.ICargoRepository;
import com.uce.edusys.repository.modelo.Cargo;

@Service
public class CargoServiceImpl implements ICargoService {

    @Autowired
    public ICargoRepository iCargoRepository;

    @Override
    @Transactional
    public void registrarC(Cargo cargo) {
        this.iCargoRepository.save(cargo);
    }

    @Override
    @Transactional
    public void actualizarC(Cargo cargo) {
        this.iCargoRepository.save(cargo);
    }

    @Override
    @Transactional(readOnly = true)
    public Cargo encontrarC(Integer id) {
        return this.iCargoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void borrarC(Integer id) {
        this.iCargoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cargo> encontrarTodos() {
        return this.iCargoRepository.findAll();
    }

}
