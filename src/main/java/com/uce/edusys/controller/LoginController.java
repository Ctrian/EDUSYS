package com.uce.edusys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/estudiantes/login")
    public String loginEstudiantes() {
        return "vistaIniciarSesionEstudiante";
    }

    @GetMapping("/representantes/login")
    public String loginRepresentantes() {
        return "vistaIniciarSesionRepresentante";
    }

    @GetMapping("/personal/login")
    public String loginPersonal() {
        return "vistaIniciarSesionPersonal";
    }
}
