package com.jdx.admusr.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

import com.jdx.admusr.model.RoleEntity;
import com.jdx.admusr.model.UserEntity;
import com.jdx.admusr.service.interfaces.UserService;

/**
 * Clas que se encarga de informar los roles de un usuario
 *
 */
public class CustomLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {

	//@Value("${ldap.user.role.attr}")
	private String roleAttribute="uid";
	
	@Autowired
	private UserService userService;
	
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	
	private String defaultRolePrefix = "";

	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
		Collection<GrantedAuthority> gas = new HashSet<GrantedAuthority>();

		//Obtiene el parametro del rol de los atributos LDAP y lo contrasta con la configuracion
		String roleAttrValue = (String)userData.getObjectAttribute(roleAttribute);
		
		if(roleAttrValue == null)
		{
			return gas;
		}
		
		//Find entity on database
		UserEntity user = userService.find(roleAttrValue);
	
		//If user doesnt exist or hasn't roles throw exception
		if(user == null || user.getRoles() == null)
		{
			throw new BadCredentialsException(messages.getMessage(
					"BindAuthenticator.userWithoutPrivileges", "This user hasn't privileges for access to this application"));
		}
		
		for (RoleEntity role : user.getRoles()) {
			gas.add(new SimpleGrantedAuthority(defaultRolePrefix+role.getRol()));
		}
		return gas;
	}

	public void setDefaultRolePrefix(String defaultRolePrefix) {
		this.defaultRolePrefix = defaultRolePrefix;
	}
}
