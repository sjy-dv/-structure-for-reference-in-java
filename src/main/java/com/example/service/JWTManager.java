package com.example.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts; 
import io.jsonwebtoken.SignatureAlgorithm; 
import org.springframework.stereotype.Component; 
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



public class JWTManager {
 
    public static String decodeJWTToken(String token){
    try {
        String secretKey = "YHDJKHJK@*$&*&@UJKD";
        Base64.Decoder decoder = Base64.getDecoder();

        String[] chunks = token.split("\\.");

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
        String signature = chunks[2];
        SignatureAlgorithm sa = HS256;
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), sa.getJcaName());

        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);
        
      
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(payload);
        JSONObject jsonObj = (JSONObject)obj;
        String oauthid = (String) jsonObj.get("oauthid");
        
        return oauthid; 
       } catch (Exception e) {
            return "error";   
       // e.printStackTrace();

       }
    }
}