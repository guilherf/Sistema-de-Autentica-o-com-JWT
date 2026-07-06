package net.bifewagyu.authenticationJWT.repository;


import net.bifewagyu.authenticationJWT.model.AdminLoginJwt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtAdminRepository extends JpaRepository<AdminLoginJwt, Long> {
}
