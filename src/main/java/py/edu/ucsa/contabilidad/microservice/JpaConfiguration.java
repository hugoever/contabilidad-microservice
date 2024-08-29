package py.edu.ucsa.contabilidad.microservice;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EntityScan("py.edu.ucsa.contabilidad.api.core.entities") // Escanea las entidades en este paquete
@EnableJpaRepositories(basePackages = "py.edu.ucsa.contabilidad.api.core.entities")
@EnableTransactionManagement
@PropertySource(value="classpath:application.properties")
public class JpaConfiguration {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
		return dataSource;
		
	}
	
	@Bean 
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.POSTGRESQL);
		vendorAdapter.setShowSql(true);
		return vendorAdapter;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException{
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		bean.setPackagesToScan("py.edu.ucsa.contabilidad.api.core.entities");
		bean.setJpaVendorAdapter(jpaVendorAdapter());
		bean.setJpaProperties(jpaProperties());
		return bean;
	}
	
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("spring.jpa.properties.hibernate.hbm2ddl.auto"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("spring.jpa.properties.hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("spring.jpa.properties.hibernate.format_sql"));
		properties.put("hibernate.use_sql_comments", environment.getRequiredProperty("spring.jpa.properties.hibernate.use_sql_comments"));
		return properties;		
	}
	
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txtManager = new JpaTransactionManager();
		txtManager.setEntityManagerFactory(emf);
		return txtManager;
	}

}
