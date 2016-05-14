package com.jdx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Fichero de configuracion de la Seguridad web para JDX
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("admin").password("nimda").roles("ADMIN");
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
       
      //http.csrf();
    }
}