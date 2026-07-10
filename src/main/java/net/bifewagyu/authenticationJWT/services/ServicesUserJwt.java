package net.bifewagyu.authenticationJWT.services;


import net.bifewagyu.authenticationJWT.apiConfig.ConfigJwt;
import net.bifewagyu.authenticationJWT.dtos.register.request.admin.JwtRequestAdminRegister;
import net.bifewagyu.authenticationJWT.dtos.register.request.user.JwtRequestUserRegister;
import net.bifewagyu.authenticationJWT.dtos.register.response.user.JwtResponseUserRegister;
import net.bifewagyu.authenticationJWT.model.UserJwt;
import net.bifewagyu.authenticationJWT.repository.JwtRepositoryUser;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ServicesUserJwt {


    @Autowired
    private final JwtRepositoryUser jwtRepositoryUser;

    @Autowired
    private final ConfigJwt configJwt;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public ServicesUserJwt(JwtRepositoryUser jwtRepositoryUser, ConfigJwt configJwt, AuthenticationManager authenticationManager) {
        this.jwtRepositoryUser = jwtRepositoryUser;
        this.configJwt = configJwt;
        this.authenticationManager = authenticationManager;
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




    public JwtResponseUserRegister loginUser(JwtRequestUserRegister jwtUserRegister) {

        UsernamePasswordAuthenticationToken userLogin = new UsernamePasswordAuthenticationToken(jwtUserRegister.email(), jwtUserRegister.password());


        Authentication authentication = authenticationManager.authenticate(userLogin);

        UserJwt userLoginUser = (UserJwt) authentication.getPrincipal();


        return new JwtResponseUserRegister(userLoginUser.getId(), userLoginUser.getUsername(), userLoginUser.getPassword(), userLoginUser.getEmail());







    }


}
