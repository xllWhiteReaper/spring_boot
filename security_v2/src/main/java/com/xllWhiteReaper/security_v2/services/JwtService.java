package com.xllWhiteReaper.security_v2.services;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xllWhiteReaper.security_v2.models.User;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${security.jwt.signing-key}")
    private String SIGNING_KEY;
    @Value("${security.jwt.token-duration-in-minutes}")
    private long TOKEN_DURATION_IN_MINUTES;

    public String getToken(User user, Map<String, Object> extraClaims) {
        long currentMilliseconds = System.currentTimeMillis();
        Date currentDate = new Date(currentMilliseconds);
        Date expirationDate = new Date(currentMilliseconds + TOKEN_DURATION_IN_MINUTES * 60 * 1000);
        return Jwts.builder().setClaims(extraClaims) // We start with extra claims because we might accidentally
                                                     // override
                // other claims

                .setSubject(user.getUsername())
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getKey() {
        final byte[] SIGNING_KEY_BYTES = Decoders.BASE64.decode(SIGNING_KEY);
        return Keys.hmacShaKeyFor(SIGNING_KEY_BYTES);
    }

}
