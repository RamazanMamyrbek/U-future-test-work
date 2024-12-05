package ru.ramazanmamyrbek.sensorapi.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.ramazanmamyrbek.sensorapi.dto.response.ErrorResponseDto;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer")) {
            token = token.substring(7);
        }
        try {
            if (token != null && jwtService.validateToken(token)) {
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtService.getUsername(token));
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    ));
                }
            }
        } catch (UsernameNotFoundException ex) {
            ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                    request.getPathInfo(),
                    HttpStatus.NOT_FOUND,
                    ex.getMessage(),
                    LocalDateTime.now()
            );
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(errorResponseDto));
            return;
        } catch (JwtException ex) {
            ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                    request.getPathInfo(),
                    HttpStatus.BAD_REQUEST,
                    ex.getMessage(),
                    LocalDateTime.now()
            );
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(errorResponseDto));
            return;
        }


        filterChain.doFilter(request, response);
    }
}
