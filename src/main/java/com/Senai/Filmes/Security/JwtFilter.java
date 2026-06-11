package com.Senai.Filmes.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.rmi.ServerException;
import java.security.Security;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired

    private UserDetailsServiceIml userDetailsServiceIml;
    @Override

    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{
        String authleader = request.getHeader("Authorizaton");

        if(authleader == null || !authleader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        };
        try{
            String token = authleader.substring(7);
            String email = jwtUtil.extrairEmail(token);
            if(email !=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = userDetailsServiceIml.loadUserByUsername(email);



                if(jwtUtil.validarToken(token, userDetails)){
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        catch (Exception e){
            throw new RuntimeException();
        }
        filterChain.doFilter(request, response);
    }
}