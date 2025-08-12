package com.omi.Blog.security;

import com.omi.Blog.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = extractToken(request);
            if(token != null){
                UserDetails userDetails = authenticationService.validateToken(token);

                UsernamePasswordAuthenticationToken Authorization = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(Authorization);

                // * now we will give user a ID instead of calling it every time with  its email
                if(userDetails instanceof BlogUserDetails){
                    request.setAttribute("userId" , ((BlogUserDetails) userDetails).getUser().getId());
                }
            }
        } catch (Exception e) {
            log.warn("Invalid AuthToken");
        }

        filterChain.doFilter(request,response);

    }

    // ? this is using request coz the request will contain the JWT!!!
    private String extractToken (HttpServletRequest request){
        // ! used to extract the authorization header (request related to auth)
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
