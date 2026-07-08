package net.bifewagyu.authenticationJWT.services;


import net.bifewagyu.authenticationJWT.apiConfig.ConfigJwt;
import net.bifewagyu.authenticationJWT.dtos.register.request.user.JwtRequestUserRegister;
import net.bifewagyu.authenticationJWT.dtos.register.response.user.JwtResponseUserRegister;
import net.bifewagyu.authenticationJWT.model.UserJwt;
import net.bifewagyu.authenticationJWT.repository.JwtRepositoryUser;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesUserJwt {


    @Autowired
    private final JwtRepositoryUser jwtRepositoryUser;

    @Autowired
    private final ConfigJwt configJwt;

    public ServicesUserJwt(JwtRepositoryUser jwtRepositoryUser, ConfigJwt configJwt) {
        this.jwtRepositoryUser = jwtRepositoryUser;
        this.configJwt = configJwt;
    }


    // REGISTRO DE USUARIO
    public JwtResponseUserRegister registerUser(JwtRequestUserRegister jwtRequestUserRegister) {

        UserJwt userJwt = new UserJwt();

        String salvarSenha = configJwt.passwordEncoder().encode(jwtRequestUserRegister.password());



        userJwt.setEmail(jwtRequestUserRegister.email());
        userJwt.setUsername(jwtRequestUserRegister.username());
        userJwt.setPassword(salvarSenha);

        UserJwt salvarUser = jwtRepositoryUser.save(userJwt);


        return new JwtResponseUserRegister( salvarUser.getId(), salvarUser.getUsername(), salvarUser.getPassword(), salvarUser.getEmail());



    }


}
