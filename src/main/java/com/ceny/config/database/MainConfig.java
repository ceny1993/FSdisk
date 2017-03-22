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

    @Bean
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

    //注意名称
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(dataSource());
        lcemfb.setJpaVendorAdapter(jpaVendorAdapter());
        lcemfb.setPackagesToScan("com.ceny.domain");

        // TODO: 2017/3/22
        //lcemfb.setJpaProperties();
        //AbstractEntityManagerFactoryBean的174行
        //会将HibernateJpaVendorAdapter的属性赋给lcemfb

        return lcemfb;
    }

    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager tmp = new JpaTransactionManager();
        tmp.setEntityManagerFactory(entityManagerFactory().getObject());
        return tmp;
    }
}
