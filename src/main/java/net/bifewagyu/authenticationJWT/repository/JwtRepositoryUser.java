package net.bifewagyu.authenticationJWT.repository;

import net.bifewagyu.authenticationJWT.model.UserJwt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtRepositoryUser extends JpaRepository<UserJwt, Long> {




}
