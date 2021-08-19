package com.example.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts; 
import io.jsonwebtoken.SignatureAlgorithm; 
import org.springframework.stereotype.Service; 
import java.util.Date; 
import java.util.HashMap; 
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;


@Service
public class JWTManager {
 
    private final String AccessKey = "AccessKey_SECRET";
    private final Long expiredTime = 1000 * 60L * 60L * 24L * 365L; // 1년

    ///////////////create////////////////////////////

    public String CreateToken(String username) {
        Date now = new Date();

        return Jwts.builder()
            .setSubject(username)
            .setHeader(createHeader())
            .setClaims(createClaims(username))
            .setExpiration(new Date(now.getTime() + expiredTime))
            .signWith(SignatureAlgorithm.HS256, AccessKey)
            .compact();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());
        return header;
    }

    private Map<String, Object> createClaims(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        return claims;
    }

    /////////////////decoded////////////////////////

    private Claims getClaims(String token) {
        //return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(AccessKey.getBytes())).parseClaimsJws(token).getBody();
        return Jwts.parser().setSigningKey(AccessKey).parseClaimsJws(token).getBody();
    }

    public String VerifyToken(String token) {
        return (String) getClaims(token).get("username");
    }
}