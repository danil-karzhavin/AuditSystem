package ru.CSApp.restdemo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String authenticatePath = "/users/authenticate/";
    private static final String createUserPath = "/users/createUser/";
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
                .antMatchers(createUserPath,authenticatePath).permitAll() // Разрешает доступ ко всем запросам, начинающимся с
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