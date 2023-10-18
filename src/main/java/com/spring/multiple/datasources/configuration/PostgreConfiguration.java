package com.spring.multiple.datasources.configuration;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration(proxyBeanMethods = false)
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgreEntityManagerFactory",
        transactionManagerRef = "postgreTransactionManager",
        basePackages = {"com.spring.multiple.datasources.repository.postgreRepo"}
)
public class PostgreConfiguration {

    @Primary
    @Bean(name = "postgreProperties")
    @ConfigurationProperties(prefix = "spring.datasource.postgre")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "postgreDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.postgre")
    public DataSource dataSource(@Qualifier("postgreProperties") DataSourceProperties dataSourceProperties){
        return dataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "postgreEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(final EntityManagerFactoryBuilder entityManagerFactoryBuilder,
                                                                                         @Qualifier("postgreDatasource") DataSource dataSource){
        return entityManagerFactoryBuilder.dataSource(dataSource)
                .packages("com.spring.multiple.datasources.model.postgreModel")
                .persistenceUnit("PostgreDb")
                .build();
    }

    @Primary
    @Bean(name = "postgreTransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("postgreEntityManagerFactory") final EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }

}
