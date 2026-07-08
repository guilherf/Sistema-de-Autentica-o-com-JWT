package net.bifewagyu.authenticationJWT.services;


import net.bifewagyu.authenticationJWT.apiConfig.ConfigJwt;
import net.bifewagyu.authenticationJWT.dtos.register.request.admin.JwtRequestAdminRegister;
import net.bifewagyu.authenticationJWT.dtos.register.response.admin.JwtResponseAdminRegister;
import net.bifewagyu.authenticationJWT.model.AdminJwt;
import net.bifewagyu.authenticationJWT.repository.JwtAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesAdminJwt {

    @Autowired
    private final JwtAdminRepository jwtAdminRepository;
    @Autowired
    private final ConfigJwt configJwt;


    public ServicesAdminJwt(JwtAdminRepository jwtAdminRepository, ConfigJwt configJwt) {
        this.jwtAdminRepository = jwtAdminRepository;
        this.configJwt = configJwt;
    }


    //REGISTER ADMIN
    public JwtResponseAdminRegister registerAdmin(JwtRequestAdminRegister jwtRequestAdminRegister) {

        AdminJwt adminLoginJwt = new AdminJwt();

        String senhaEncoder = configJwt.passwordEncoder().encode(adminLoginJwt.getPassword());


        adminLoginJwt.setUsername(jwtRequestAdminRegister.username());
        adminLoginJwt.setEmail(jwtRequestAdminRegister.email());
        adminLoginJwt.setPassword(senhaEncoder);

        AdminJwt salvar = jwtAdminRepository.save(adminLoginJwt);


        return new JwtResponseAdminRegister(salvar.getUsername(), salvar.getEmail(), salvar.getPassword());

    }



}
