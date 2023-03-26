package com.freebee.hive.service;

import com.freebee.hive.enums.EToken;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Service
public class JWTService {

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(EToken.ACCESS_SECRET.getValue()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException |
                 IllegalArgumentException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        }

        return false;
    }

    public String getSubjectDataFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(EToken.ACCESS_SECRET.getValue()).parseClaimsJws(token).getBody().getSubject();
    }

    public String generateToken(String subject, EToken eToken) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(createTokenExpiredTime(Integer.parseInt(eToken.getValue())))
                .signWith(SignatureAlgorithm.HS512, EToken.ACCESS_SECRET.getValue())
                .compact();
    }

    public String generateToken(String subject, ZonedDateTime expireAt) {
        Duration between = Duration.between(ZonedDateTime.now(), expireAt);
        int hour = (int) (between.getSeconds() / 3600);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(createTokenExpiredTime(hour))
                .signWith(SignatureAlgorithm.HS512, EToken.ACCESS_SECRET.getValue())
                .compact();
    }

    public Date createTokenExpiredTime(int hour) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.HOUR, hour);
        return instance.getTime();
    }

    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
