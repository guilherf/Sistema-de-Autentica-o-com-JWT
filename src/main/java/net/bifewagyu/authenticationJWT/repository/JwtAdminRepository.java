package net.bifewagyu.authenticationJWT.repository;


import net.bifewagyu.authenticationJWT.model.AdminJwt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtAdminRepository extends JpaRepository<AdminJwt, Long> {
}
