package com.jdx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * Configuración para cargar la conexión a LDAP en el contexto de Spring.
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class LdapConfig {
	
	
	@Value("${infr.ldap_server.corporativo.manager.dn}")
    private String externalLdapManagerUser;

    @Value("${infr.ldap_server.corporativo.manager.password}")
    private String externalLdapManagerPassword;

    @Value("${infr.ldap_server.corporativo.url}")
    private String externalLdapUrl;

    @Value("${infr.ldap_server.corporativo.user.searchFilter}")
    private String externalLdapSearchFilter;

    @Value("${infr.ldap_server.corporativo.user.base}")
    private String externalLdapSearchBase;

	@Bean
    public LdapContextSource contextSource () {
        LdapContextSource contextSource= new LdapContextSource();
        contextSource.setUrl(externalLdapUrl);
        contextSource.setBase(externalLdapSearchBase);
        contextSource.setUserDn(externalLdapManagerUser);
        contextSource.setPassword(externalLdapManagerPassword);
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());        
    }

}

