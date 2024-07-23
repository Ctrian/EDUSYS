package com.uce.edusys.configuracion.seguridad;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        String uri = request.getRequestURI();
        if (uri.startsWith("/admin")) {
            response.sendRedirect("/admin/login");
        } else if (uri.startsWith("/representantes")) {
            response.sendRedirect("/representantes/login");
        } else if (uri.startsWith("/estudiantes")) {
            response.sendRedirect("/estudiantes/login");
        } else if (uri.startsWith("/personal")) {
            response.sendRedirect("/personal/login");
        } else {
            response.sendRedirect("/login");
        }
    }

}
