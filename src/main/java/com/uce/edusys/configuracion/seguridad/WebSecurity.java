package com.uce.edusys.configuracion.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurity {

        private final UserDetailsService customUserDetailsService;
        private final PasswordEncoder passwordEncoder;

        @Autowired
        public WebSecurity(UserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
                this.customUserDetailsService = customUserDetailsService;
                this.passwordEncoder = passwordEncoder;
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
        }

        @SuppressWarnings({ "deprecation" })
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeRequests(authorizeRequests -> authorizeRequests
                                                .requestMatchers("/",
                                                                "/menu/botones", "/menu/contactar",
                                                                "menu/formulario", "menu/insertar",

                                                                "/representantes/space",
                                                                "/representantes/login",
                                                                "/representantes/signUp", "/representantes/insertar",
                                                                "/representantes/tyc",
                                                                "/representantes/reset-password",
                                                                "/representantes/reset-request",
                                                                "/representantes/reset-password/confirm",
                                                                "/representantes/reset-password/reset",

                                                                "/personal/space",
                                                                "/personal/login",
                                                                "/personal/signUp", "/personal/registrarP",

                                                                "/estudiantes/space", "/estudiantes/login",

                                                                "/error",
                                                                "/resources/**",
                                                                "/images/**",
                                                                "/static/css/**")
                                                .permitAll()
                                                .requestMatchers("/representantes/cuentaR",
                                                                "/representantes/registrarE",
                                                                "/representantes/estadoRegistro",
                                                                "/representantes/matricular", "/representantes/enviado",
                                                                "/representantes/pagos", "/representantes/pago",
                                                                "/representantes/descargarFactura",
                                                                "/representantes/vistaFactura")
                                                .hasRole("REPRESENTANTE")
                                                .requestMatchers("/personal/cuentaPer")
                                                .hasAnyRole("PERSONAL")
                                                .requestMatchers("/estudiantes/cuentaE").hasRole("ESTUDIANTE")
                                                .requestMatchers("/profesores/cuentaP").hasRole("PROFESOR")
                                                .anyRequest().authenticated())

                                .formLogin(formLogin -> formLogin
                                                .loginPage("/login")
                                                .usernameParameter("email")
                                                .passwordParameter("password")
                                                .loginProcessingUrl("/perform_login")
                                                .defaultSuccessUrl("/default", true)
                                                .failureUrl("/login?error=true") // Redirigir a /login con un parámetro
                                                                                 // de error
                                                .successHandler(myAuthenticationSuccessHandler())
                                                .failureHandler(myAuthenticationFailureHandler())
                                                .permitAll())

                                .logout(logout -> logout
                                                .logoutUrl("/perform_logout")
                                                .logoutSuccessUrl("/menu/botones") // URL de redirección después de
                                                                                   // logout
                                                .deleteCookies("JSESSIONID")
                                                .permitAll())

                                .exceptionHandling(exceptionHandling -> exceptionHandling
                                                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()))

                                .csrf(csrf -> csrf
                                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));

                return http.build();
        }

        @Bean
        public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
                return new MySimpleUrlAuthenticationSuccessHandler();
        }

        @Bean
        public AuthenticationFailureHandler myAuthenticationFailureHandler() {
                return new MySimpleUrlAuthenticationFailureHandler();
        }

}
