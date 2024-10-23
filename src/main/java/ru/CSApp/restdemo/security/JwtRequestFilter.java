package ru.CSApp.restdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.CSApp.restdemo.service.user.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private UserService userService;
    private JwtUtil jwtUtil;

    public JwtRequestFilter(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    // содержит логику фильтрации
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Получает заголовок `Authorization` из входящего http запроса. Этот заголовок обычно содержит JWT,
        // использующийся для аутентификации пользователя
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Извлекается строка токена, убирая префикс `"Bearer "` (длина 7 символов)
            username = jwtUtil.extractUsername(jwt); // берет токен и извлекает из него имя пользователя
        }

        // Эта проверка удостоверяется, что имя пользователя было извлечено и что текущий контекст безопасности (`SecurityContextHolder.getContext()`) не имеет аутентифицированного пользователя
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userService.getUserByName(username);

            // проверка токена
            if (jwtUtil.validateToken(jwt, userDetails)) {

                // Установка аутентификации в контекст
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        // UsernamePasswordAuthenticationToken: Создает новый объект аутентификации, который будет хранить информацию о пользователе, в том числе его права доступа
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // заполняет объект аутентификации дополнительными данными из запроса (например, IP-адресом)
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Затем устанавливается аутентификация в контекст безопасности с помощью `SecurityContextHolder.getContext().setAuthentication(...)`. Это позволяет Spring Security знать, что запрос аутентифицирован.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        // вызов передает управление следующему фильтру в цепочке. Если это последний фильтр, то он завершает выполнение запроса и формирует ответ
        filterChain.doFilter(request, response);
    }
}