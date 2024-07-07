package com.uce.edusys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edusys.repository.modelo.Pago;
import com.uce.edusys.service.IPagoService;

@Controller
@RequestMapping("/pagos")
public class PagoController {
    
    @Autowired
    private IPagoService iPagoService;

    // http://localhost:8080/pagos/registrar
    @PostMapping("/registrar")
    public String registrarPago(@ModelAttribute Pago pago) {
        iPagoService.registrarPago(pago);
        return "redirect:/matriculas/detalle/" + pago.getMatricula().getId();
    }

    // http://localhost:8080/pagos/matricula
    @GetMapping("/matricula/{id}")
    public String obtenerPagosPorMatricula(@PathVariable Integer id, Model model) {
        List<Pago> pagos = iPagoService.obtenerPagosPorMatricula(id);
        model.addAttribute("pagos", pagos);
        return "detalleMatricula";
    }
}
