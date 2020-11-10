package oracle.fsgbu.plato.customer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import oracle.fsgbu.plato.core.persistence.service.GenericServiceImpl;
import oracle.fsgbu.plato.customer.api.model.CustomerModel;
import oracle.fsgbu.plato.customer.persistence.CustomerRepository;
import oracle.fsgbu.plato.customer.util.CustomerUtils;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<oracle.fsgbu.plato.customer.domain.entity.Customer, Long>
		implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super(customerRepository);
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional("PLATO_TX_MANAGER")
	public List<CustomerModel> getCustomer() {
		LOGGER.info("START getCustomer().");
		List<CustomerModel> customers = CustomerUtils.convertEntityListToVOList(getAll());
		LOGGER.info("End getCustomer().");
		return customers;
	}

	@Override
	@Transactional("PLATO_TX_MANAGER")
	public CustomerModel getCustomer(String id) {
		LOGGER.info("START getCustomer({}).", id);
		CustomerModel customer = CustomerUtils
				.convertEntityToVO(customerRepository.find(CustomerUtils.convertStringToLong(id)));
		LOGGER.info("End getCustomer({}) :: {} ", id, customer);
		return customer;
	}

	@Override
	@Transactional("PLATO_TX_MANAGER")
	public void createCustomer(CustomerModel customerModel) {
		LOGGER.info("START createCustomer({}).");
		create(CustomerUtils.convertVOToEntity(customerModel));
		LOGGER.info("End createCustomer({}).");
	}
}
