package net.bifewagyu.authenticationJWT.repository;


import net.bifewagyu.authenticationJWT.model.AdminJwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtAdminRepository extends JpaRepository<AdminJwt, Long> {

    UserDetails findByEmail(String Email);



}
