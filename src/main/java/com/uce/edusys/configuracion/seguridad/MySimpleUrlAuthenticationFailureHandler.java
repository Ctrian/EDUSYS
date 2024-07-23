package com.uce.edusys.configuracion.seguridad;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MySimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "Ud no tiene acceso a esta página.";

        if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
            errorMessage = "Su cuenta está deshabilitada.";
        } else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
            errorMessage = "Su cuenta ha expirado.";
        } else if (exception.getMessage().equalsIgnoreCase("Bad credentials")) {
            errorMessage = "Credenciales incorrectas.";
        }

        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);

        // Detectar la URL de origen
        String referrer = request.getHeader("Referer");
        if (referrer != null) {
            if (referrer.contains("/admin")) {
                getRedirectStrategy().sendRedirect(request, response, "/admin/login?error=true");
            } else if (referrer.contains("/representantes")) {
                getRedirectStrategy().sendRedirect(request, response, "/representantes/login?error=true");
            } else if (referrer.contains("/estudiantes")) {
                getRedirectStrategy().sendRedirect(request, response, "/estudiantes/login?error=true");
            } else if (referrer.contains("/personal")) {
                getRedirectStrategy().sendRedirect(request, response, "/personal/login?error=true");
            } else {
                getRedirectStrategy().sendRedirect(request, response, "/login?error=true");
            }
        } else {
            getRedirectStrategy().sendRedirect(request, response, "/login?error=true");
        }
    }

}
