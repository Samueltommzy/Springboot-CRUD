package demo.firstdemo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//This is a configuration class
@Configuration
//Allow users to use transaction management in application
@EnableTransactionManagement
//Indicates location of repository classes
@EnableJpaRepositories("demo.firstdemo.repository")
//location of property file
@PropertySource("classpath:database.properties")

public class DataConfig {
    private final String DRIVER = "DRIVER";
    private final String URL  = "URL";
    private final String USERNAME = "USERNAME";
    private final String PASSWORD = "PASSWORD";
    private final String SHOW_SQL = "hibernate.SHOW_SQL";
    private final String DIALECT = "hibernate.DIALECT";

    @Autowired
    Environment environment;

    

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        System.out.println(environment.getProperty(URL));
        System.out.println(environment.getProperty(USERNAME));
        System.out.println(environment.getProperty(PASSWORD));

        LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
        lfb.setDataSource(dataSource());
        // System.out.println("Finished datasource settings");
        lfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        // System.out.println("persistence was done");
        lfb.setPackagesToScan("demo.firstdemo.model");
        // System.out.println("Got package to scan");
        lfb.setJpaProperties(hibernateProps());
        // System.out.println("Set JPA props");
        return lfb;
    }

    @Bean
    DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(environment.getProperty(URL));
        ds.setUsername(environment.getProperty(USERNAME));
        ds.setPassword(environment.getProperty(PASSWORD));
        ds.setDriverClassName(environment.getProperty(DRIVER));
        
        return ds;
    }
    Properties hibernateProps(){
        Properties props = new Properties();
        props.setProperty(DIALECT,environment.getProperty(DIALECT));
        props.setProperty(SHOW_SQL, environment.getProperty(SHOW_SQL));
        return props;
    }

    @Bean
    JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}
