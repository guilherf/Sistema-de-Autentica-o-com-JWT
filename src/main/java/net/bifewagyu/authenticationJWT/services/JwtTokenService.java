package net.bifewagyu.authenticationJWT.services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import net.bifewagyu.authenticationJWT.dtos.register.request.admin.JwtRequestAdminRegister;
import net.bifewagyu.authenticationJWT.model.AdminJwt;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {

    private String secretJwt = "ServiceJWTSecretAdminAndUser";
    private String issuerJwt = "ServiceJWTIssuer";


    public String gerarToken(AdminJwt adminLoginJwt) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretJwt);
            return JWT.create()
                    .withIssuer(issuerJwt)
                    .withSubject(adminLoginJwt.getEmail())
                    .withClaim("role", adminLoginJwt.getUsername())
                    .withExpiresAt(gerarDataToken())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar Token", exception);
        }


    }


    public String validarToken(String validar) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretJwt);
            return JWT.require(algorithm)
                    .withIssuer(issuerJwt)
                    .build()
                    .verify(validar)
                    .getSubject();

        }catch (JWTVerificationException exception){
            return null;

                    }
    }



    public Instant gerarDataToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }





}
