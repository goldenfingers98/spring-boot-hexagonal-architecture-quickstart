package hexagonal.architecture.persistence.config;
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

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        basePackages = { "hexagonal.architecture.persistence" }
)
public class PersistenceDBConfig {
    @Primary
    @Bean(name="persistenceDBProps")
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        DataSourceProperties temp = new DataSourceProperties();
        return temp;
    }

    @Primary
    @Bean(name = "persistenceDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource persistenceDataSource(@Qualifier("persistenceDBProps") DataSourceProperties properties) {
        DataSource temp = properties.initializeDataSourceBuilder().build();
        return temp;
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("persistenceDataSource") DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean temp = builder
                .dataSource(dataSource)
                .packages("hexagonal.architecture.persistence")
                .persistenceUnit("persistence")
                .build();
        return temp;
    }

    @Primary
    @Bean(name = "transactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory
                    entityManagerFactory
    ) {
        PlatformTransactionManager temp = new JpaTransactionManager(entityManagerFactory);
        return temp;
    }
}