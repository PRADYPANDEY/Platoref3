package oracle.fsgbu.plato.customer.service;

import java.util.List;

import oracle.fsgbu.plato.core.persistence.service.GenericService;
import oracle.fsgbu.plato.customer.api.model.CustomerModel;

public interface CustomerService extends GenericService<oracle.fsgbu.plato.customer.domain.entity.Customer, Long> {

	public void createCustomer(CustomerModel customerModel);

	public List<CustomerModel> getCustomer();

	public CustomerModel getCustomer(String id);
}
