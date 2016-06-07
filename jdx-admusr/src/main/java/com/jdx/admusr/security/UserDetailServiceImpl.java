package com.jdx.admusr.security;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.ppolicy.PasswordPolicyControl;
import org.springframework.security.ldap.ppolicy.PasswordPolicyResponseControl;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

import com.jdx.admusr.service.interfaces.FunctionalityService;

public class UserDetailServiceImpl extends LdapUserDetailsMapper {

	private final Log logger = LogFactory.getLog(LdapUserDetailsMapper.class);
	private String passwordAttributeName = "userPassword";
	private String name = "cn";
	
	
	@Autowired
	FunctionalityService funcService;

	
	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
			Collection<? extends GrantedAuthority> authorities) {
		String dn = ctx.getNameInNamespace();

		logger.debug("Mapping user details from context with DN: " + dn);

		LdapUserDetailsImpl.Essence essence = new LdapUserDetailsImpl.Essence();
		essence.setDn(dn);

		Object passwordValue = ctx.getObjectAttribute(passwordAttributeName);

		if (passwordValue != null) {
			essence.setPassword(mapPassword(passwordValue));
		}

		essence.setUsername(username);

		// Map the rights
		Set<String> rights = funcService.getRights(username);
		

		// Add the supplied authorities

		for (GrantedAuthority authority : authorities) {
			essence.addAuthority(authority);
		}

		// Check for PPolicy data

		PasswordPolicyResponseControl ppolicy = (PasswordPolicyResponseControl) ctx
				.getObjectAttribute(PasswordPolicyControl.OID);

		if (ppolicy != null) {
			essence.setTimeBeforeExpiration(ppolicy.getTimeBeforeExpiration());
			essence.setGraceLoginsRemaining(ppolicy.getGraceLoginsRemaining());
		}

		//Obtenemos los atributos para sacar el nombre de persona
		Object cn = ctx.getObjectAttribute(name);
		//Ahora creamos un object de usuario
		
		CustomUserDetails user = new CustomUserDetails();
		UserDetails userLdap = essence.createUserDetails();
		
		user.setName(cn.toString());
		user.setAuthorities(userLdap.getAuthorities());
		user.setUsername(userLdap.getUsername());
		user.setPassword(userLdap.getPassword());
		user.setRights(rights);
		
		return user;

	}


}
