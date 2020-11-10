package oracle.fsgbu.plato.customer.persistence;

import org.springframework.stereotype.Repository;
import oracle.fsgbu.plato.core.persistence.dao.GenericRepositoryImpl;
import oracle.fsgbu.plato.customer.domain.entity.Customer;

@Repository
public class CustomerRepositoryImpl extends GenericRepositoryImpl<Customer, Long> implements CustomerRepository {

}
