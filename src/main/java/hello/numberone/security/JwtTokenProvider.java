package hello.numberone.security;

import hello.numberone.entity.Student;
import hello.numberone.repository.StudentRepository;
import hello.numberone.exception.StudentNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    private final SecretKey secretKey;
    private final StudentRepository studentRepository;
    private final long expirationTime = 1000 * 60 * 60;

    public JwtTokenProvider(
            @Value("${jwt.secret-key}") String secretKey,
            StudentRepository studentRepository
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.studentRepository = studentRepository;
    }

    public String generateToken(Student student) {
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .setSubject(student.getEmail())
                .claim("studentId", student.getId())
                .claim("role", student.getRole().name())
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        String email = getEmail(token);
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(StudentNotFoundException::new);
        List<SimpleGrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + student.getRole().name())
        );
        return new UsernamePasswordAuthenticationToken(student.getEmail(), null, authorities);
    }
}