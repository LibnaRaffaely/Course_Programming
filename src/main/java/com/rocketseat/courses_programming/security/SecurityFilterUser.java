package com.rocketseat.courses_programming.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rocketseat.courses_programming.providers.JWTUserProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilterUser extends OncePerRequestFilter {
    @Autowired
    private JWTUserProvider jwtUserProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (request.getRequestURI().startsWith("/user")) {
            if (header != null) {
                var token = this.jwtUserProvider.validateToken(header);

                if (token == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }

                request.setAttribute("user_id", token.getSubject());
                var roles = token.getClaim("roles").asList(Object.class);

                var grants = roles.stream()
                        .map(
                                role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toLowerCase()))
                        .toList();

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token.getSubject(),
                        null,
                        grants);

                SecurityContextHolder.getContext().setAuthentication(auth);

                System.out.println("------TOKEN-----");
                System.out.println(token);
            }
        }

        System.out.println(header);

        filterChain.doFilter(request, response);
    }

}
