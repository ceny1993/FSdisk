package com.ceny.config.database;

import com.ceny.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chensongkui on 2017/3/21.
 */
@Deprecated
@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
   // List<Customer> findAllByFisrtName(String firstName);
}
