package oracle.fsgbu.plato.customer.web;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiParam;
import oracle.fsgbu.plato.logger.PlatoLogger;
import oracle.fsgbu.plato.customer.api.CustomerApi;
import oracle.fsgbu.plato.customer.api.model.CustomerModel;
import oracle.fsgbu.plato.customer.api.model.CustomerCollection;
import oracle.fsgbu.plato.customer.api.model.InlineResponse201;
import oracle.fsgbu.plato.customer.service.CustomerService;
import oracle.fsgbu.plato.customer.util.CustomerUtils;

@RestController
public class CustomerController implements CustomerApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
  private static final PlatoLogger LOGGER1 = new PlatoLogger(CustomerController.class);
  @Autowired
  private CustomerService customerService;

  @Override
  public ResponseEntity<InlineResponse201> addCustomer(@ApiParam(
      value = "Request Body that contains data required for creating a new customer",
      required = true) @Valid @RequestBody CustomerModel customer) {
    LOGGER.debug("Request received for Create Customer: {}", customer);
    LOGGER1.info("Inside the addCustomer method");
    customerService.create(CustomerUtils.convertVOToEntity(customer));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<CustomerCollection> getAllCustomers() {
    LOGGER.info("Request received to get the Customer details. ");
    LOGGER1.info("Inside the getAllCustomer method");
    ResponseEntity<CustomerCollection> responseDetail;
    final List<CustomerModel> customerList = customerService.getCustomer();
    responseDetail = ok(new CustomerCollection().customerList(customerList));
    return responseDetail;
  }

  @Override
  public ResponseEntity<CustomerModel> getCustomerById(@ApiParam(
      value = "Id of the user that needs to be fetched",
      required = true) @PathVariable("id") String id) {
    LOGGER.info("Request received to get the Customer details for id id=[{}].", id);
    LOGGER1.info("Inside the getCustomerById method");
    ResponseEntity<CustomerModel> responseDetail;
    final CustomerModel customer = customerService.getCustomer(id);
    if (customer == null) {
      responseDetail = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      responseDetail = ok(customer);
    }
    return responseDetail;
  }

  @Override
  public ResponseEntity<CustomerModel> updateCustomer(
      @ApiParam(
          value = "id of the customer that need to be updated",
          required = true) @PathVariable("id") String id,
      @ApiParam(
          value = "Request Body that contains data required for updating an existing customer",
          required = true) @Valid @RequestBody CustomerModel customer) {
    LOGGER.debug("Request received for update Customer: {}", customer);
    LOGGER1.info("Inside the updateCustomer method");
    ResponseEntity<CustomerModel> responseDetail;
    final CustomerModel responseCustomer = CustomerUtils
        .convertEntityToVO(customerService.update(CustomerUtils.convertVOToEntity(customer)));
    if (responseCustomer == null) {
      responseDetail = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      responseDetail = ok(responseCustomer);
    }
    return responseDetail;
  }

  @Override
  public ResponseEntity<Void> deleteCustomer(@ApiParam(
      value = "Id of the user that needs to be fetched",
      required = true) @PathVariable("id") String id) {
    LOGGER.debug("Request received for delete Customer: {}", id);
    customerService.removeByKey(CustomerUtils.convertStringToLong(id));
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

}
