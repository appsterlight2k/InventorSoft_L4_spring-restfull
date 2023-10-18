package co.inventorsoft.academy.spring.restfull.dao;

import co.inventorsoft.academy.spring.restfull.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
