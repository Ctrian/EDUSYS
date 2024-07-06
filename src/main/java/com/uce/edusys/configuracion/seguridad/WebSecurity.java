package com.uce.edusys.configuracion.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.uce.edusys.service.IRepresentanteService;
import com.uce.edusys.service.RepresentanteServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    private final UserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurity(UserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }
         
    @SuppressWarnings({ "deprecation", "removal" })
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/",
                                "/menu/botones",
                                "/matriculas/formulario",
                                "/resources/**",
                                "/images/**",
                                "/static/css/**",
                                "/contactos/contactar", "/representantes/space", "/representantes/login",
                                "/representantes/signUp", "/representantes/insertar",
                                "/representantes/tyc", "/representantes/fPassword", "/error")
                        .permitAll()
                        .requestMatchers("/representantes/cuentaR", "/representantes/pagos").hasRole("REPRESENTANTE")
                        .anyRequest().authenticated())

                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/representantes/cuentaR", true)
                        .failureUrl("/representantes/login?error=true")
                        .permitAll())
                .logout()
                    .logoutUrl("/logout")
                // .logoutSuccessUrl("/representantes/login?logout=true")
                .permitAll()
                
            .and()
                .csrf()
                .ignoringRequestMatchers("/send-email");

        return http.build();
    }

}
