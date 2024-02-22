package com.adsforgood.projectify.config;

import com.adsforgood.projectify.security.UserService;
import com.adsforgood.projectify.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String email;

        if (authHeader.isEmpty() || authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
        }
        jwt = authHeader.substring(7);
        email = jwtService.extractUserName(jwt);

        if(!email.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(email);

            if(jwtService.isTokenValid(jwt, userDetails)){
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        filterChain.doFilter(request, response);
    }

}
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        User user = new User();
//
//        try{
//            user = new ObjectMapper().readValue(request.getReader(), User.class);
//        }catch (IOException e){}
//
//        UsernamePasswordAuthenticationToken emailPAT = new UsernamePasswordAuthenticationToken(
//                user.getEmail(),
//                user.getPassword(),
//                Collections.emptyList()
//        );
//
//        return getAuthenticationManager().authenticate(emailPAT);
//    }
//
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        UserDetailImpl userDetail = (UserDetailImpl) authResult.getPrincipal();
//        String token = TokenUtils.createToken(userDetail.getName(),userDetail.getUsername());
//        response.addHeader("Authorization", "Bearer " + token);
//        response.getWriter().flush();
//        super.successfulAuthentication(request, response, chain, authResult);
//    }
