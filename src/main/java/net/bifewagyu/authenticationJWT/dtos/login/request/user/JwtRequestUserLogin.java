package net.bifewagyu.authenticationJWT.dtos.login.request;

public record JwtRequestUserLogin(String username, String email,
                                  String password) {
}
