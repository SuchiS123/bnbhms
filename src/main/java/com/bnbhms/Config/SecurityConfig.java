package com.bnbhms.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    private JwtFilter jwtFilter;
    public SecurityConfig(JwtFilter jwtFilter)
    {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
//        http.authorizeRequests().anyRequest().permitAll();

        http.authorizeHttpRequests().requestMatchers("/api/auth/sign-up","/api/auth/login"
                ,"/api/auth/owner/sign-up","/api/city/add/city","/api/country/add/country").permitAll()
                .requestMatchers("/api/v1/property/add/{country}/{city}",
                        "/api/v1/property/update/properties","/api/v1/property/update/properties","/api/v1/property/updateByCity/{city}/{country}",
                        "/api/v1/property/DeleteByCityId/{cityId}","/api/v1/property/DeleteByCityIdFindById/cityId/{CityIdNo}").hasRole("OWNER").
                requestMatchers("/api/v1/property/delete/{id}","/api/v1/property/delete-by-name/{property}").hasAnyRole("OWNER","ADMIN").
        anyRequest().authenticated();
        return http.build();
    }

}
