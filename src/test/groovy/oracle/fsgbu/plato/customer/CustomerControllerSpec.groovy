package oracle.fsgbu.plato.customer

import oracle.fsgbu.plato.customer.api.model.CustomerModel
import oracle.fsgbu.plato.customer.domain.entity.Customer
import oracle.fsgbu.plato.customer.service.CustomerService
import oracle.fsgbu.plato.customer.util.CustomerUtils
import oracle.fsgbu.plato.customer.web.CustomerController
import oracle.fsgbu.plato.customer.api.model.CustomerCollection
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class CustomerControllerSpec extends Specification {

  @Shared customer = new Customer(id:1L, extension:"1234", firstName:"Kaila", lastName:"Bernard", age:38L, sex: "M", county:"CA", country:"US", email:"kaila.nernard@citi.com", phone:"+18976235412");
  @Shared customerModel = new CustomerModel(id:1L, firstName:"Kaila", lastName:"Bernard", age:38L, sex: "M", county:"CA", country:"US", email:"kaila.nernard@citi.com", phone:"+18976235412");

  @Unroll("Test getAllCustomers method")
  def "test getAllCustomers method"() {

    given:
    def mockCustomerService = Mock(CustomerService);
    CustomerController customerController = new CustomerController(customerService: mockCustomerService);
    mockCustomerService.getCustomer() >> new ArrayList<Customer>();

    when:
    def response = customerController.getAllCustomers();
    println response;

    then:
    response != null;
    response.getStatusCodeValue() == 200;
    response.getBody().getCustomerList().size() == 0;
  }


  @Unroll("Test getCustomer method for the passed customer id \"#id\"")
  def "test getCustomer method with passed id "() {

    given:
    def mockCustomerService = Mock(CustomerService);
    CustomerController customerController = new CustomerController(customerService: mockCustomerService);
    mockCustomerService.getCustomer("1") >> customerModel
    mockCustomerService.getCustomer("2") >> null

    when:
    def response = customerController.getCustomerById(id);

    then:
    response != null;
    if(response.getBody() != null) {
      response.getStatusCodeValue() == 200;
    }else {
      response.getStatusCodeValue() == 404;
    }
    where:
    id << ["1", "2"]
  }

  @Unroll("Test updateCustomer method for the passed customer id \"#id\" and \"#customerObj\"")
  def "test getCustomer method with passed "() {

    given:
    def mockCustomerService = Mock(CustomerService);
    CustomerController customerController = new CustomerController(customerService: mockCustomerService);
    //def temp = CustomerUtils.convertVOToEntity(customerModel)
    mockCustomerService.update(_) >> customer

    when:
    def response = customerController.updateCustomer(id, customerObj);

    then:
    response != null;
    if(response.getBody() != null) {
      response.getStatusCodeValue() == 200;
    } else {
      response.getStatusCodeValue() == 404;
    }

    where:
    id << ["1", "2"]
    customerObj << [customerModel, null] 

  }
}
