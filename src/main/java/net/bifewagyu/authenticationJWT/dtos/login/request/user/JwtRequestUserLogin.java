package net.bifewagyu.authenticationJWT.dtos.login.request.user;

public record JwtRequestUserLogin(String username, String email,
                                  String password) {
}
