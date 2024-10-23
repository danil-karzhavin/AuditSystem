package ru.CSApp.restdemo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Генерация безопасного секретного ключа для HS256
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())) // Устанавливает время создания токена
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Устанавливает время, когда токен истечет
                .signWith(SignatureAlgorithm.HS256, secretKey) // Указывает алгоритм подписи (в данном случае HMAC SHA-256) и секретный ключ, который будет использоваться для подписи токена
                .compact(); // Создает строковое представление токена
    }

    // метод извлекает имя пользователя из токена JWT
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token) // получает тело токена (claims) после его успешной проверки
                .getBody()
                .getSubject(); // Извлекает "subject", который в данном случае — это имя пользователя
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // извлекает дату истечения срока действия токена
    private Date extractExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration(); // извлекает дату истечения срока действия токена
    }
}