package net.bifewagyu.authenticationJWT.services;


import net.bifewagyu.authenticationJWT.apiConfig.ConfigJwt;
import net.bifewagyu.authenticationJWT.dtos.register.request.admin.JwtRequestAdminRegister;
import net.bifewagyu.authenticationJWT.dtos.register.response.admin.JwtResponseAdminRegister;
import net.bifewagyu.authenticationJWT.model.AdminJwt;
import net.bifewagyu.authenticationJWT.repository.JwtAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ServicesAdminJwt {

    @Autowired
    private final JwtAdminRepository jwtAdminRepository;
    @Autowired
    private final ConfigJwt configJwt;

    @Autowired
    private final AuthenticationManager authenticationManager;


    public ServicesAdminJwt(JwtAdminRepository jwtAdminRepository, ConfigJwt configJwt, AuthenticationManager authenticationManager) {
        this.jwtAdminRepository = jwtAdminRepository;
        this.configJwt = configJwt;
        this.authenticationManager = authenticationManager;
    }


    //REGISTER ADMIN
    public JwtResponseAdminRegister registerAdmin(JwtRequestAdminRegister jwtRequestAdminRegister) {

        AdminJwt adminLoginJwt = new AdminJwt();

        String senhaEncoder = configJwt.passwordEncoder().encode(jwtRequestAdminRegister.password());


        adminLoginJwt.setUsername(jwtRequestAdminRegister.username());
        adminLoginJwt.setEmail(jwtRequestAdminRegister.email());
        adminLoginJwt.setPassword(senhaEncoder);

        AdminJwt salvar = jwtAdminRepository.save(adminLoginJwt);


        return new JwtResponseAdminRegister(salvar.getUsername(), salvar.getEmail(), salvar.getPassword());

    }

    // LOGIN ADMIN
    public JwtResponseAdminRegister loginAdmin(JwtRequestAdminRegister jwtRequestAdminRegister) {

     UsernamePasswordAuthenticationToken UserTokenLogin = new UsernamePasswordAuthenticationToken(jwtRequestAdminRegister.username(), jwtRequestAdminRegister.password());

    Authentication authentication = this.authenticationManager.authenticate(UserTokenLogin);

    AdminJwt adminLoginJwt = (AdminJwt) authentication.getPrincipal();


    return new JwtResponseAdminRegister(adminLoginJwt.getUsername(), adminLoginJwt.getEmail(), adminLoginJwt.getPassword());

    }



}
