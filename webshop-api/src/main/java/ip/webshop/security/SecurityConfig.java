package ip.webshop.security;

import ip.webshop.filter.CustomAuthenticationFilter;
import ip.webshop.filter.CustomAuthorizationFilter;
import ip.webshop.service.implementation.UserServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.*;

import org.springframework.beans.factory.annotation.Autowired;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    UserServiceImpl userDetailsService;
    @Autowired
    AuthenticationConfiguration authConfig;
    
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
     
        return authProvider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
      return authConfig.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager(authConfig));
        customAuthenticationFilter.setFilterProcessesUrl("/api/user/login");
        http.csrf().disable();
        //http.cors().disable();
        http.sessionManagement(management -> management.sessionCreationPolicy(STATELESS));
        http.authorizeHttpRequests(requests -> requests.antMatchers("/api/user/login/**","/api/support/**","/api/order/**","/api/category/**","/api/comment/**", "/api/products/**", "/api/products/home/**","/api/user/token/refresh/**","/api/user/seller/**","/api/user/register/**","/api/user/pin/**","/api/user/update/**","/api/user/info/**","/api/files/**").permitAll());
        
        http.authorizeHttpRequests().antMatchers(POST, "/api/user/register/**","/api/order/**","/api/products/**","/api/user/pin/**","/api/files/**","/api/comment/**","/api/user/update/**").permitAll();
        http.authorizeHttpRequests().antMatchers(GET,"/api/order/**","/api/category/**","/api/order/mrki02/**","/api/comment/**","/api/user/token/refresh/**").permitAll();
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}