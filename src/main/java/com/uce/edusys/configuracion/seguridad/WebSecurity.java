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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
                                                                "/matriculas/formulario",
                                                                "/matriculas/insertar",
                                                                 "/representantes/space",
                                                                "/representantes/login",
                                                                "/representantes/signUp", "/representantes/insertar",
                                                                "/representantes/tyc", "/representantes/fPassword",
                                                                "/error",
                                                                "/resources/**",
                                                                "/images/**",
                                                                "/static/css/**")
                                                .permitAll()
                                                .requestMatchers("/representantes/cuentaR", "/representantes/pagos")
                                                .hasRole("REPRESENTANTE")
                                                .requestMatchers("/estudiantes/cuentaE").hasRole("ESTUDIANTE")
                                                .requestMatchers("/profesores/cuentaP").hasRole("PROFESOR")
                                                .anyRequest().authenticated())

                                .formLogin(formLogin -> formLogin
                                                .loginPage("/login")
                                                .usernameParameter("email")
                                                .passwordParameter("password")
                                                .defaultSuccessUrl("/representantes/cuentaR", true)
                                                .failureUrl("/representantes/login?error=true")
                                                .permitAll())

                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/representantes/login?logout=true")
                                                .permitAll())

                                .csrf(csrf -> csrf.ignoringRequestMatchers("/send-email"));

                return http.build();
        }

        @Bean
        public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
                return new MySimpleUrlAuthenticationSuccessHandler();
        }

}
