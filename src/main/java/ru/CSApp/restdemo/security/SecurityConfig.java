package ru.CSApp.restdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// https://chataibot.ru/app/chat?chat_id=516961#:~:text=%D0%9A%D0%BB%D0%B0%D1%81%D1%81%20%60SecurityConfig%60%20%D0%BE%D1%82%D0%B2%D0%B5%D1%87%D0%B0%D0%B5%D1%82%20%D0%B7%D0%B0%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D1%83%20%D0%B1%D0%B5%D0%B7%D0%BE%D0%BF%D0%B0%D1%81%D0%BD%D0%BE%D1%81%D1%82%D0%B8%20%D0%B2%20%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B8%20Spring%2C%20%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D1%83%D1%8F%20Spring%20Security

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // Аннотация указывает Spring, что возвращаемый объект должен быть зарегистрирован как бин в контексте приложения.
    // Это позволяет другим компонентам получать объект `AuthenticationManager`, если это необходимо
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/authenticate/**").permitAll() // Разрешает доступ ко всем запросам, начинающимся с
                // `/auth/`, без аутентификации. Это обычно означает, что конечные точки для регистрации и входа в
                // систему не требуют аутентификации
                .anyRequest().authenticated() // Все остальные запросы требуют аутентификации. Это означает, что после
                // того, как пользователь вошел в систему, он должен предоставить токен для доступа к защищенным ресурсам
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Устанавливает политику
        // управления сессиями. Использование `SessionCreationPolicy.STATELESS` говорит о том, что приложение не будет
        // использовать сессии на стороне сервера. Вместо этого аутентификация будет основана на токенах, что идеально
        // подходит для RESTful API

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}