package com.jdx.stockmanager.backend.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration for database conection using jpa
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(
		basePackages={
						"com.jdx.**.repository",
						"com.jdx.test",
						"com.jdx.common.exception"
					})
public class JpaDatabaseConfiguration {
	
	@Autowired
	ApplicationContext applicationContext;

	@Value("${datasource.driver}")
	private String driverClassName;
	
	@Value("${datasource.url}")
	private String url;
	
	@Value("${datasource.username}")
	private String username;
	
	@Value("${datasource.password}")
	private String password;

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(driverClassName);
	    dataSource.setUrl(url);
	    dataSource.setUsername(username);
	    dataSource.setPassword(password);
	 
	    return dataSource;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

    /**
     * Configuration of the LocalContainerEntityManagerFactoryBean 
     * @param dataSource the datasource provided by the DataSourceFactoryBean
     * @return an instance of a LocalContainerEntityManagerFactoryBean 
     */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource) {
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(new String[] {
				"com.jdx.common.model",
				"com.jdx.test.common.model",
				"org.springframework.data.jpa.convert.threeten" });

		em.setJpaVendorAdapter(jpaVendorAdapter());
		return em;
	}
    
    /**
     * Configures the JpaVendorAdapter specified 
     * 
     * @return an instance of HibernateJpaVendorAdapter 
     */
   private JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(false);
        return vendorAdapter;
    }
    
    /**
     * Configures the PlatformTransactionManager
     * @param emf the configured entityManagerFactory
     * @return new PlatformTransactionManager
     */
    @Bean(name="transactionManager")
    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(emf);
        return jpaTransactionManager;
    }
}
