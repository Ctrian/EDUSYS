package com.uce.edusys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contactos")
public class ContactoController {
    
    // http://localhost:8080/contactos/contactar
	@GetMapping("/contactar")
	public String vistaRepresentantes() {
		return "vistaContacto";
	}
}
