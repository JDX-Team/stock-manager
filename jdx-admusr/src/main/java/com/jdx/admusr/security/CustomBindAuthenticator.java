package com.jdx.admusr.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.jdx.admusr.service.interfaces.UserService;

/**
 *  An authenticator which binds as a user.
 *
 */
public class CustomBindAuthenticator extends BindAuthenticator{
	
	private static final Log logger = LogFactory.getLog(CustomBindAuthenticator.class);
	//TODO corregir
	//@Value("${infr.ldap_server.corporativo.user.access.attr}")
	private String accessAttribute="uid";
	
	@Autowired
	private UserService userService;
	
	public CustomBindAuthenticator(BaseLdapPathContextSource contextSource) {
		super(contextSource);
		
	}

	@Override
	public DirContextOperations authenticate(Authentication authentication) {
		DirContextOperations user = null;
		Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
				"Can only process UsernamePasswordAuthenticationToken objects");

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		if (!StringUtils.hasLength(password)) {
			logger.debug("Rejecting empty password for user " + username);
			throw new BadCredentialsException(messages.getMessage(
					"BindAuthenticator.emptyPassword", "Empty Password"));
		}

		// Otherwise use the configured search object to find the user and authenticate
		// with the returned DN.
		if (user == null && getUserSearch() != null) {
			//Primero validamos. Sino es válido lanzará la excepción
			super.authenticate(authentication);
			user = getUserSearch().searchForUser(username);
		}
		
		if (user == null) {
			throw new BadCredentialsException(messages.getMessage(
					"BindAuthenticator.badCredentials", "Bad credentials"));
		}
		
		//Comprueba el atributo de acceso para ver si debemos permitirle el acceso
		String accessAttrValue = (String)user.getObjectAttribute(accessAttribute);
		
		//If user is not found on database, reject it
		if(userService.find(accessAttrValue) == null)
		{
			throw new BadCredentialsException(messages.getMessage(
					"BindAuthenticator.userWithoutPrivileges", "This user hasn't privileges for access to this application"));
		}

		return user;
	}

}
