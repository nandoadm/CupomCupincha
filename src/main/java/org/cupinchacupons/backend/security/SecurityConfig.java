package org.cupinchacupons.backend.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private static final String[] PUBLIC_URLS = {
            "/login/**",
            "/css/**",
            "/images/user-regular.svg",
            "/auth/**",
            "/home/",
            "/js/**"
    };

    private static final String[] SWAGGER_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**"
    };
    /*  Ainda falta
        - Configuração de CORS
        - Proteção contra XSS
        - Cache-Control headers
        - Frame options
    */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, SecurityUserFilter securityUserFilter) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( auth ->{
                    auth.requestMatchers(PUBLIC_URLS).permitAll()
                            .requestMatchers(SWAGGER_LIST).permitAll()
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .anyRequest().authenticated();
                }).formLogin(auth -> auth.loginPage("/login/"));
                httpSecurity.addFilterBefore(securityUserFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
