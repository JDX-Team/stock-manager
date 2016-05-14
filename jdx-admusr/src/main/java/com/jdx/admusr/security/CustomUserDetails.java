package com.jdx.admusr.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = -1033616207066910086L;
	protected String name;
	protected String password;
	protected String username;
	protected Collection<? extends GrantedAuthority> Authorities;
	protected Set<String> rights;
	
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Authorities = authorities;
	}

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.Authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * @return the rights
	 */
	public Set<String> getRights() {
		return rights;
	}

	/**
	 * @param rights the rights to set
	 */
	public void setRights(Set<String> rights) {
		this.rights = rights;
	}

	

}
