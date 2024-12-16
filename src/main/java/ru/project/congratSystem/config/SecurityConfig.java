package ru.project.congratSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/simplePages/main", "/authReg/reg","/authReg/auth",
                                 "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                );
        http
                .formLogin(fl->fl.loginPage("/authReg/auth").
                usernameParameter("username").permitAll().
                defaultSuccessUrl("/simplePages/main", true)

                );
        http.
                logout(lt -> lt.logoutRequestMatcher(new
                        AntPathRequestMatcher("/logout")).
                        deleteCookies("JSESSIONID").
                        invalidateHttpSession(true).
                        logoutSuccessUrl("/login").
                        permitAll());

        http.
                sessionManagement(sm ->sm.sessionFixation().none());
        return http.build();
    }


    public void configure(WebSecurity web) throws Exception {
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowSemicolon(true);
        return firewall;
    }
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
