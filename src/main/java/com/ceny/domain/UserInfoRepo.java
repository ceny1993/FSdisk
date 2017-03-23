package com.ceny.domain;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by chensongkui on 2017/3/23.
 */
@Configuration
@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo,Long> {
}
