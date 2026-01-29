package com.dev.filmlog.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

            String jwt;
            String username;

            String path = request.getRequestURI();
            if (path.startsWith("/api/v1/auth/") ||
                    path.equals("/api/v1/company/register") ||
                    path.equals("/api/v1/users/add") ||
                    path.equals("/api/v1/payments/callback")) {
                filterChain.doFilter(request, response);
                return;
            }

            jwt = extractJwtFromCookie(request);

            if(jwt == null){
                jwt = extractJwtFromHeader(request);
            }
            if (jwt == null) {
                filterChain.doFilter(request, response);
                return;
            }

            try {
                username = jwtService.extractUsername(jwt);
            } catch (Exception e) {
                filterChain.doFilter(request, response);
                return;
            }

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if(jwtService.isTokenValid(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        filterChain.doFilter(request, response);
    }

    private String extractJwtFromCookie(HttpServletRequest request){
        if(request.getCookies() != null){
            for(Cookie cookie :  request.getCookies()){
                if("jwt".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private String extractJwtFromHeader(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);

        }
        return null;
    }
}
