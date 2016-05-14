package com.jdx.test.config;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
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
						"com.jdx.common.repository",
						"com.jdx.test",
						"com.jdx.common.exception"
					})
public class InMemoryJpaDatabaseConfiguration {
	
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	private Environment env;

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
	    dataSource.setUrl("jdbc:hsqldb:mem:test");
	    dataSource.setUsername("sa");
	    dataSource.setPassword("");
	 
	    return dataSource;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	/**
	 * Fill database with test data
	 */
	@Bean
	public DatabasePopulator databasePopulator(DataSource dataSource) {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setContinueOnError(true);
		populator.setIgnoreFailedDrops(true);

		String databasetype = "hsql";

		populator.addScript(new ClassPathResource("db/" + databasetype + "/create-db.sql"));
		populator.addScript(new ClassPathResource("db/" + databasetype + "/insert-data.sql"));
		try {
			populator.populate(dataSource.getConnection());
		} catch (SQLException e) {
			System.err.println("DatabaseSourceConfig.databasePopulator(): " + e);
		}
		
		return populator;
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
        vendorAdapter.setDatabase(Database.HSQL);
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
