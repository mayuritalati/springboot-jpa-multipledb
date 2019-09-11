package com.mayuri.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mayuri.user.data.UserEntity;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "userEntityManager", 
		transactionManagerRef = "userTransactionManager", 
		basePackages = "com.mayuri.repositroy"
)
public class UserDBConfig {

	@Autowired
	Environment env;
	@Bean
	@ConfigurationProperties(prefix = "spring.user.datasource")
	public DataSource postgresqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		Map map =  Properties();
	    dataSource.setDriverClassName((String) map.get("spring.user.datasource.driver-class-name"));
	    dataSource.setUrl((String) map.get("spring.user.datasource.url"));
	    dataSource.setUsername((String) map.get("spring.user.datasource.username"));
	    dataSource.setPassword((String) map.get("spring.user.datasource.password"));

	    return dataSource;
	}

	@Bean(name = "userEntityManager")
	public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
					.dataSource(postgresqlDataSource())
					.properties(hibernateProperties())
					.packages(UserEntity.class)
					.persistenceUnit("userPU")
					.build();
	}

	@Bean(name = "userTransactionManager")
	public PlatformTransactionManager postgresqlTransactionManager(@Qualifier("userEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	private HashMap<String, Object> hibernateProperties() {

		Resource resource = new ClassPathResource("hibernate.properties");
		HashMap<String, Object> map  = new HashMap<String, Object>();;
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			
			properties.entrySet().forEach(e->{
				map.put((String) e.getKey(), e.getValue());
			});
		} catch (IOException e) {
			return new HashMap<String, Object>();
		}
		return map;
	}
	
	private HashMap<String, Object> Properties() {

		Resource resource = new ClassPathResource("application.properties");
		HashMap<String, Object> map  = new HashMap<String, Object>();;
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			
			properties.entrySet().forEach(e->{
				map.put((String) e.getKey(), e.getValue());
			});
		} catch (IOException e) {
			return new HashMap<String, Object>();
		}
		return map;
	}
}
