package com.ceny.config.database;

import com.ceny.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chensongkui on 2017/3/21.
 */
@Repository
public interface CustomerRepo extends CrudRepository<Customer,Long> {
}
