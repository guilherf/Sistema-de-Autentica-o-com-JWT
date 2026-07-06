package net.bifewagyu.authenticationJWT.dtos;

public record JwtRequest(String username,
                         String email,
                         String password) {
}
