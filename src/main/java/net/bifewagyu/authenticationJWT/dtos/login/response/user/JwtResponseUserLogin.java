package net.bifewagyu.authenticationJWT.dtos.login.response;

public record JwtResponseUserLogin(Long id, String username, String email, String password) {
}
