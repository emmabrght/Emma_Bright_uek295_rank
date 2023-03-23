package com.example.test.domain.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
/*1. enables pre- / post-annotations: checks method before/after running it, allows the result to be altered
2. allows @secured annotation: which roles can use x method
3. allows @roleallowed annotation: jsr-250 version of @secured */
public class WebSecurityConfig {

   private final UserDetailsService userService;
   //private final PasswordEncoder passwordEncoder;

    Encoder pe = new Encoder();

  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(pe.passwordEncoder());
    provider.setUserDetailsService(userService);
    return new ProviderManager(provider);
  }

    /*filter chain which can be matched with an HTTP request */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //set security by urls
                .antMatchers("/rank/**").hasAuthority("DEFAULT")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic() //enable HTTP-Basic Auth
                //Disable CORS and CSRF protection
                .and().csrf()
                .disable().cors().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //never creates an HTTP session

        return http.build();
    }

}