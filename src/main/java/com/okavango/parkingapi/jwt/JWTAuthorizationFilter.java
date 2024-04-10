package com.okavango.parkingapi.jwt;

import com.okavango.parkingapi.exceptions.ExceptionObject;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j

public class JWTAuthorizationFilter extends OncePerRequestFilter {


    @Autowired
    private  JWTUserDetailService userDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader(JWTUtils.JWT_AUTHORIZATION);

        if(token == null || !token.startsWith(JWTUtils.JWT_BEARER)){
            log.info("JWT token is null, empty or not inicialized with Bearer");
            filterChain.doFilter(request, response);
            return;
        }

        if(!JWTUtils.isTokenValid(token)){
            log.warn("JWT token is not valid or expided");
            filterChain.doFilter(request, response);
            return;
        }

        String username = JWTUtils.getUserNameFromToke(token);
        toAuthentication(request, username);
        filterChain.doFilter(request, response);
    }

    private void toAuthentication(HttpServletRequest request, String username) {
        UserDetails details = userDetailService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.authenticated(details, null , details.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
