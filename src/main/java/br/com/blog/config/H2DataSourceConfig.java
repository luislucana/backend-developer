package br.com.blog.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "h2EntityManagerFactory", 
		transactionManagerRef = "h2TransactionManager",
		basePackages = "br.com.blog.persistence.h2.repository")
public class H2DataSourceConfig {

	@Bean(name = "h2DataSource")
	@ConfigurationProperties(prefix = "h2.datasource")
	public HikariDataSource mysqlDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(name = "h2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("h2DataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("br.com.devlog.springbootmultipledatabases.db.h2.model")
				.persistenceUnit("h2PU").build();
	}

	@Bean(name = "h2TransactionManager")
	public PlatformTransactionManager mysqlTransactionManager(
			@Qualifier("h2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}