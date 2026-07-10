package net.bifewagyu.authenticationJWT.apiConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.bifewagyu.authenticationJWT.repository.JwtAdminRepository;
import net.bifewagyu.authenticationJWT.services.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class WebSecurityJwt extends OncePerRequestFilter {



    private final JwtTokenService tokenService;
    private final JwtAdminRepository jwtAdminRepository;

    @Autowired
    public WebSecurityJwt(TokenService tokenService, JwtTokenService tokenService1, JwtAdminRepository jwtAdminRepository) {
        this.tokenService = tokenService1;
        this.jwtAdminRepository = jwtAdminRepository;
    }


    public String recoverToken(HttpServletRequest req) {

        var login = req.getHeader("Authorization");
        if (login == null || !login.startsWith("Bearer ")) return null;
            return login.replace("Bearer ", "");
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.recoverToken(request);

        if (token != null) {
            var login = tokenService.validarToken(token);

            UserDetails userDetails = jwtAdminRepository.findByEmail(login);

            if (userDetails != null) {
                var authorities = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authorities);
            }
        }

        filterChain.doFilter(request, response);
    }

    }
