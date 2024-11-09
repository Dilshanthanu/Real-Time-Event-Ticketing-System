package com.example.Real_Time.Event.Ticketing.System.Security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtDecoder {

    private final jwtProperties Properties;

    public DecodedJWT decode(String token) {
        return JWT.require(Algorithm.HMAC256(Properties.getSecretKey()))
                .build()
                .verify(token);
    }
}
