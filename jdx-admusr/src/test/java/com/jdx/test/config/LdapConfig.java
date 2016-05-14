package com.jdx.test.config;

import java.io.IOException;

import javax.naming.NamingException;
import javax.naming.ldap.LdapName;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.ldap.test.LdapTestUtils;

/**
 * Configuración para cargar la conexión a LDAP en el contexto de Spring.
 *
 */
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
    public LdapContextSource contextSource () throws NamingException, IOException {
		
		int port = 7777;
		LdapName baseName = LdapUtils.newLdapName(externalLdapSearchBase); 
		 
        // Start an in process LDAP server 
        LdapTestUtils.startEmbeddedServer(port, baseName.toString(), "odm-test"); 
        
		LdapContextSource contextSource = new LdapContextSource();
	    contextSource.setUrl("ldap://127.0.0.1:" + port);
	    contextSource.setUserDn("");
	    contextSource.setPassword("");
	    contextSource.setPooled(false);
	    contextSource.setBase(externalLdapSearchBase);
	    contextSource.afterPropertiesSet();

	    // Clear out any old data - and load the test data
	    LdapTestUtils.cleanAndSetup(contextSource, baseName, new ClassPathResource("ldap/testdata.ldif"));
	    
	    return contextSource;
	}

    @Bean
    public LdapTemplate ldapTemplate() throws NamingException, IOException {
        return new LdapTemplate(contextSource());        
    }

}

