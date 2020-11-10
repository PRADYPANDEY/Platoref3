package oracle.fsgbu.plato.customer.service

import oracle.fsgbu.plato.customer.api.model.CustomerModel
import oracle.fsgbu.plato.customer.domain.entity.Customer
import oracle.fsgbu.plato.customer.persistence.CustomerRepository
import oracle.fsgbu.plato.customer.service.CustomerServiceImpl
import oracle.fsgbu.plato.customer.api.model.CustomerCollection
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class CustomerServiceImplSpec extends Specification {

	@Shared customer = new Customer(id:1L, extension:"1234", firstName:"Kaila", lastName:"Bernard", age:38L, county:"CA", country:"US", email:"kaila.nernard@citi.com", phone:"+18976235412");
    @Shared customerModel = new CustomerModel(id:1L, firstName:"Kaila", lastName:"Bernard", age:38L, county:"CA", country:"US", email:"kaila.nernard@citi.com", phone:"+18976235412");
    
	@Unroll("Test getCustomer method")
	def "test getCustomer method"() {

		given:
		def mockedCustomerRepository = Mock(CustomerRepository);
		def customerService = new CustomerServiceImpl(mockedCustomerRepository);
		def customerList = new ArrayList<Customer>();
		customerList.add(customer);        
		mockedCustomerRepository.select() >>  customerList;

		when:
		  def response = customerService.getCustomer();

		then:
		response != null;
		response.get(0) == customerModel;
	}


	@Unroll("Test getCustomer method for the passed customer id \"#id\"")
	def "test getCustomer method with passed id "() {

		given:
		def mockedCustomerRepository = Mock(CustomerRepository);
		def customerService = new CustomerServiceImpl(mockedCustomerRepository);
		mockedCustomerRepository.find(1) >> customer;
		mockedCustomerRepository.find(2) >> null;

		expect:
		customerService.getCustomer(id) == response;
        
        where:
		id    |   response
        "1"   |   customerModel
        "2"   |   null 
	}
}
