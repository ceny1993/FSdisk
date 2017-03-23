package com.ceny.config.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by chensongkui on 2017/3/21.
 */

@Configuration
@EnableJpaRepositories(basePackages = "com.ceny.domain")
public class JpaConfig {
}
