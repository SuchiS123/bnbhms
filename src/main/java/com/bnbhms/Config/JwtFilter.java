package com.bnbhms.Config;

import com.bnbhms.Entity.User;
import com.bnbhms.Repository.UserRepository;
import com.bnbhms.UserService.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private UserRepository userRepository;
    public JwtFilter(JwtService jwtService,UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {

        String JwtToken = request.getHeader("Authorization");
        if(JwtToken!=null && JwtToken.startsWith("Bearer "))
        {
            String token = JwtToken.substring(8, JwtToken.length() - 1);
            String username = jwtService.getUsername(token);

            Optional<User> opUsername = userRepository.findByUsername(username);

            if(opUsername.isPresent())
            {
                User user = opUsername.get();
                UsernamePasswordAuthenticationToken authenticationToken=new
                        UsernamePasswordAuthenticationToken(user,null, Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
                authenticationToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }
        filterChain.doFilter(request, response);

        }

    }
