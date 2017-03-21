package com.ceny.config.database;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * Created by chensongkui on 2017/3/21.
 */
@Configuration
public class MainConfig {
    @Bean("datasource")
    public BasicDataSource dataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/fsdisk?useUnicode=true&characterEncoding=UTF-8");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("csk789..");
        basicDataSource.setInitialSize(5);
        basicDataSource.setMaxIdle(5);
        basicDataSource.setMaxIdle(5);
        return basicDataSource;
    }

    // TODO: 2017/3/21
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(org.springframework.orm.jpa.vendor.Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        //org.hibernate.dialect.MySQL5Dialect
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        return adapter;
    }

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean lcemfb(){
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(dataSource());
        lcemfb.setJpaVendorAdapter(jpaVendorAdapter());
        lcemfb.setPackagesToScan("com.ceny.domain");
        return lcemfb;
    }

    @Bean("transactionManager")
    public JpaTransactionManager jpaTransactionManager(){
        JpaTransactionManager tmp = new JpaTransactionManager();
        tmp.setEntityManagerFactory(lcemfb().getObject());
        return tmp;
    }
}
