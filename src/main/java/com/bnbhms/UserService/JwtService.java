package com.bnbhms.UserService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JwtService {

    @Value("${Jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration.time}")
    private long expirationTime;

    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct() throws UnsupportedEncodingException
    {
       algorithm=Algorithm.HMAC256(algorithmKey);
    }


    public String generateToken(String username) {
        return JWT.create()
                .withClaim("name",username)
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis()+expirationTime))
                .sign(algorithm);
    }

    public String getUsername(String token) {
        DecodedJWT decodedJWT=JWT.require(algorithm)
                .withIssuer(issuer)
                .build().verify(token);
        return decodedJWT.getClaim("name").asString();
    }
}
