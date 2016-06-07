package com.jdx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.jdx.admusr.security.DatabaseUserDetailsService;
import com.jdx.common.filter.CsrfHeaderFilter;

/**
 * Fichero de configuracion de la Seguridad web para JDX
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;
     
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {        
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    
    @Bean
    public UserDetailsService customUserDetailsService() {
    	return new DatabaseUserDetailsService();
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**"); // #3
	}
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {
   
      http
      	.authorizeRequests()
      	.antMatchers("/public/**").permitAll()
      	.antMatchers("/resources/**").permitAll()
      	.antMatchers("/static/**").permitAll()
      	.and().formLogin();
      
      http
      	.authorizeRequests()
     	.anyRequest().authenticated();
      
      http.authorizeRequests()
      .and()
          .logout()
              .logoutUrl("/public/logout")
              .logoutSuccessUrl("/public/logout?success")
      .and()
          .formLogin()
              .loginPage("/public/login").permitAll()
              .loginProcessingUrl("/public/login").permitAll()
              .defaultSuccessUrl("/", true)
              .failureUrl("/public/login?failure")
          .and()
              .logout().permitAll()
      .and()
          .httpBasic();
       
      http.csrf();
      
      http.addFilterBefore(new CsrfHeaderFilter(), FilterSecurityInterceptor.class);
     
    }
}