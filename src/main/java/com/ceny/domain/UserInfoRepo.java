package com.ceny.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by chensongkui on 2017/3/23.
 */
@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo,Long> {
    UserInfo findOneByUserName(String userName);
}
