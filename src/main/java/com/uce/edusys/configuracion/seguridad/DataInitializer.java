package com.uce.edusys.configuracion.seguridad;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.uce.edusys.repository.IOfertaAcademicaRepository;
import com.uce.edusys.repository.modelo.OfertaAcademica;
import com.uce.edusys.repository.modelo.Rol;

@Component
public class DataInitializer implements CommandLineRunner {

    private final IRolRepository iRolRepository;
    private final IOfertaAcademicaRepository iOfertaAcademicaRepository;

    @Autowired
    public DataInitializer(IRolRepository iRolRepository, IOfertaAcademicaRepository iOfertaAcademicaRepository) {
        this.iRolRepository = iRolRepository;
        this.iOfertaAcademicaRepository = iOfertaAcademicaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Inicialización de roles
        String[] roles = { "REPRESENTANTE", "PERSONAL", "ESTUDIANTE", "PROFESOR", "ADMIN", "USER" };
        for (String roleName : roles) {
            if (iRolRepository.findByNombre(roleName) == null) {
                Rol role = new Rol();
                role.setNombre(roleName);
                iRolRepository.save(role);
            }
        }
        // Inicialización de ofertas académicas

        final String horaD = "07:00 - 13:00";
        final String horaT = "13:00 - 18:00";

        if (this.iOfertaAcademicaRepository.count() == 0) {

            // Ofertas de la tarde

            OfertaAcademica oferta1 = new OfertaAcademica();
            oferta1.setNombre("N8");
            oferta1.setDescripcion("8vo de Bachillerato");
            oferta1.setPrecio(BigDecimal.valueOf(200));
            oferta1.setHora(horaT);
            this.iOfertaAcademicaRepository.save(oferta1);

            OfertaAcademica oferta2 = new OfertaAcademica();
            oferta2.setNombre("N9");
            oferta2.setDescripcion("9no de Bachillerato");
            oferta2.setPrecio(BigDecimal.valueOf(250));
            oferta2.setHora(horaT);
            this.iOfertaAcademicaRepository.save(oferta2);

            OfertaAcademica oferta3 = new OfertaAcademica();
            oferta3.setNombre("N10");
            oferta3.setDescripcion("10mo de Bachillerato");
            oferta3.setPrecio(BigDecimal.valueOf(300));
            oferta3.setHora(horaT);
            this.iOfertaAcademicaRepository.save(oferta3);

            // Ofertas del dia

            OfertaAcademica oferta4 = new OfertaAcademica();
            oferta4.setNombre("D1");
            oferta4.setDescripcion("1ro de Bachillerato");
            oferta4.setPrecio(BigDecimal.valueOf(400));
            oferta4.setHora(horaD);
            this.iOfertaAcademicaRepository.save(oferta4);

            OfertaAcademica oferta5 = new OfertaAcademica();
            oferta5.setNombre("D2");
            oferta5.setDescripcion("2do de Bachillerato");
            oferta5.setPrecio(BigDecimal.valueOf(450));
            oferta5.setHora(horaD);
            this.iOfertaAcademicaRepository.save(oferta5);

            OfertaAcademica oferta6 = new OfertaAcademica();
            oferta6.setNombre("D3");
            oferta6.setDescripcion("3ro de Bachillerato");
            oferta6.setPrecio(BigDecimal.valueOf(500));
            oferta6.setHora(horaD);
            this.iOfertaAcademicaRepository.save(oferta6);
        }
    }

}
