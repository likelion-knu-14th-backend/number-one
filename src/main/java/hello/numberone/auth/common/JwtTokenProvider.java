package hello.numberone.auth.common;

import hello.numberone.data.entity.User;
import hello.numberone.data.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final SecretKey secretKey;
    private final UserRepository userRepository;
    private final long expirationTime = 1000 * 60 * 60;

    public JwtTokenProvider(
            @Valid("${jwt.secret-key}") String secretKey,
            UserRepository userRepository
    ) {
        this.secretKey = Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );
        this.userRepository = userRepository;
    }

    public String generateToken(User user) {
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim()
    }
}
