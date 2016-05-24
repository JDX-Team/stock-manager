package com.jdx.admusr.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdx.admusr.model.UserEntity;
import com.jdx.admusr.service.interfaces.UserService;

/**
 * Service for security purpose, load an user and his granted authorities
 * 
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;

	/**
	 * Load the user looking for the username,
	 * 
	 * @param username the user name to search
	 * 
	 * @return user found user
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userService.find(username);

		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUser(), user.getPassword(), true, true, true, true,
				new ArrayList<GrantedAuthority>()/* getGrantedAuthorities(user) */);
	}

}
