package org.distributed.ransaction.multiple.db.config.jpa;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Berezhnov Andrey on 2024-04-03 20:39
 * @see <a href="#">Andrey at andrew.my@yahoo.com</a>
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.distributed.ransaction.multiple.db.repository.mariadb",
        entityManagerFactoryRef = "mariadbEntityManagerFactory", transactionManagerRef = "mariadbPlatformTransactionManager")
public class MariaDBSqlConfig {

    @Bean
    @ConfigurationProperties("spring.mariadb.datasource")
    public DataSourceProperties mariadbDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource mariadbDataSource(@Qualifier("mariadbDataSourceProperties") DataSourceProperties mariadbDataSourceProperties) {
        return mariadbDataSourceProperties
                .initializeDataSourceBuilder()
                .build();
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mariadbEntityManagerFactory(@Qualifier("mariadbDataSource") DataSource mariadbDataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("org.distributed.ransaction.multiple.db.entities.mariadb");
        factory.setDataSource(mariadbDataSource);
        factory.setJpaProperties(properties());
        factory.setPersistenceUnitName("mariadb");

        return factory;
    }

    @Bean
    public PlatformTransactionManager mariadbPlatformTransactionManager(@Qualifier("mariadbEntityManagerFactory") EntityManagerFactory mariadbEntityManagerFactory) {
        return new JpaTransactionManager(mariadbEntityManagerFactory);
    }

}
