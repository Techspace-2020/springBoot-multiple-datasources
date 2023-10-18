package com.spring.multiple.datasources.configuration;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
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
        entityManagerFactoryRef = "historyDbEntityManagerFactory",
        transactionManagerRef = "historyDbTransactionManager",
        basePackages = {"com.spring.multiple.datasources.repository.oracleRepo"}
)
public class OracleConfiguration {

    @Bean(name = "historyDb")
    @ConfigurationProperties("spring.datasource.oracle")
    public DataSourceProperties SecDataSourceProperties(){
        return new DataSourceProperties();
    }

   // @Primary
    @Bean(name = "historyDbProperties")
    @ConfigurationProperties(prefix = "spring.datasource.oracle.configuration")
    public HikariDataSource dataSource(@Qualifier("historyDb") DataSourceProperties SecDataSourceProperties){
        return SecDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

   // @Primary
    @Bean(name = "historyDbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(final EntityManagerFactoryBuilder builder,
                                                                                         @Qualifier("historyDbProperties") DataSource dataSource){
        return builder.dataSource(dataSource)
                .packages("com.spring.multiple.datasources.model.oracleModel")
                .persistenceUnit("HistoryDb")
                .build();
    }

    //@Primary
    @Bean(name = "historyDbTransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("historyDbEntityManagerFactory") final EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
