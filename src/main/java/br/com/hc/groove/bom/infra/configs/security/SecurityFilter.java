package br.com.hc.groove.bom.infra.configs.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.hc.groove.bom.domain.repositories.UsuarioRepository;
import br.com.hc.groove.bom.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = getJWTToken(request);

        if (tokenJWT != null) {
           UserDetails authUser = usuarioRepository.findByEmail(tokenService.getSubject(tokenJWT)); 
           SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities()));
        }

        filterChain.doFilter(request, response);
    }

    private String getJWTToken(HttpServletRequest request) {
        String tokenJWT = request.getHeader("Authorization");
        return tokenJWT != null ? tokenJWT.replace("Bearer ", "") : null;
    }
    
}
