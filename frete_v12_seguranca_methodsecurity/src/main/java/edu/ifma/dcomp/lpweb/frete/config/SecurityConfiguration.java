package edu.ifma.dcomp.lpweb.frete.config;

import edu.ifma.dcomp.lpweb.frete.domain.service.UsuarioService;
import edu.ifma.dcomp.lpweb.frete.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//habilita a adcionar a segurança nos controllers
@EnableMethodSecurity (securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                /* .formLogin(configure -> configure.loginPage("/login.html")
                        .successForwardUrl("/home.html")
                        .failureForwardUrl("/login.html"))*/
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize ->  {
                    authorize.requestMatchers("/login").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll();

                 /*   authorize.requestMatchers("/clientes/**").hasRole("ADMIN");
                    authorize.requestMatchers("/entregas/**").hasAnyRole("USER", "ADMIN"); */

                    // O anyRequest deve ser a última linha
                    authorize.anyRequest().authenticated();
                })
                .build();

    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioService usuarioService ) {
       /*UserDetails user1 = User.builder()
                .username("usuario")
                .password(passwordEncoder().encode("123") )
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("321"))
                .roles("ADMIN")
                .build();

       return new InMemoryUserDetailsManager(user1, user2 );*/
       return new CustomUserDetailsService(usuarioService );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
