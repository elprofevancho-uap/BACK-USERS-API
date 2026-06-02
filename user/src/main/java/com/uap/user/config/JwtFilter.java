package com.uap.user.config;

import com.uap.user.usecase.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Extraer la cabecera "Authorization"
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // 2. Validar que la cabecera exista y empiece con "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Deja pasar la petición (ej. si va a /login)
            return;
        }

        // 3. Aislar el string del token puro (quitando "Bearer ")
        String token = authHeader.substring(7);

        try {
            // 4. Validar el token y extraer el email del usuario
            String email = jwtService.extraerEmail(token);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 5. Crear la autenticación para el contexto de Spring Security
                // (Pasamos el email y una lista vacía de roles/autoridades por ahora)
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());

                // Adjuntar detalles de la petición HTTP actual
                // authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 6. Meter al usuario en el Contexto de Seguridad. A partir de aquí, está "Autenticado"
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (Exception e) {
            // Si el token expiró o la firma es inválida, el contexto no se llena
            // y Spring Security bloqueará al usuario automáticamente con un 403 Forbidden.
        }

        // Continúa con la ejecución de la petición hacia el controlador
        filterChain.doFilter(request, response);
    }
}