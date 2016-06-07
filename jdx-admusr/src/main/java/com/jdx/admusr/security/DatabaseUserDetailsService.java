package com.jdx.admusr.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdx.admusr.model.UserEntity;
import com.jdx.admusr.service.interfaces.FunctionalityService;
import com.jdx.admusr.service.interfaces.UserService;

/**
 * Service for security purpose, load an user and his granted authorities
 * 
 */
@Service("customUserDetailsService")
public class DatabaseUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Autowired
	FunctionalityService funcService;

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
		//Sino se produce ninguna excepción obtenemos los derechos de bbdd
		CustomUserDetails userCtxt = new CustomUserDetails();

		userCtxt.setName(user.getUser());
		userCtxt.setUsername(user.getUser());
		userCtxt.setPassword(user.getPassword());
		Collection<GrantedAuthority> gas = new HashSet<GrantedAuthority>();
		userCtxt.setAuthorities(gas);
		// Map the rights
		Set<String> rights = funcService.getRights(username);
		userCtxt.setRights(rights);
		
    	return userCtxt;
	}

}
