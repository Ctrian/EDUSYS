package com.uce.edusys.configuracion.seguridad;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.io.IOException;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class MySimpleUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
    
    protected String determineTargetUrl(Authentication authentication) {
        boolean isRepresentante = false;
        boolean isEstudiante = false;
        
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_REPRESENTANTE")) {
                isRepresentante = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ESTUDIANTE")) {
                isEstudiante = true;
                break;
            }
        }
        
        if (isRepresentante) {
            return "/representantes/cuentaR";
        } else if (isEstudiante) {
            return "/estudiantes/cuentaE";
        } else {
            throw new IllegalStateException();
        }
    }
}
