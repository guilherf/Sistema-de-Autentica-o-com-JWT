package net.bifewagyu.authenticationJWT.repository;

import net.bifewagyu.authenticationJWT.model.UserLoginJwt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtRepository extends JpaRepository<UserLoginJwt, Long> {




}
