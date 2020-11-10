package oracle.fsgbu.customer.test.steps;

import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import oracle.fsgbu.plato.customer.api.model.CustomerModel;

public class GetCustomerByIdImpl {
	
	private final RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<CustomerModel> customerEntity = null;
	CustomerModel customerObj = null;

    public UriComponentsBuilder getBaseUriForCustomerService() {
	    final String url = "http://" + "localhost" + ":" + "7001" + "/customer-service-1.0/" + "customer";
	    return UriComponentsBuilder.fromHttpUrl(url);
	}
    
    public HttpEntity<?> getHttpEntityForGetRequest() {
          HttpHeaders headers = new HttpHeaders();
          headers.setContentType(MediaType.APPLICATION_JSON);
          headers.set("appId", "CUSTSRV001");
          headers.set("tenantId", "BOA001");
          return new HttpEntity<Object>(headers);
    }

	@Given("^the customer id \"([^\"]*)\" is sent to customer controller to get customer$")
	public void the_customer_id_is_sent_to_customer_controller_to_get_customer(String customerId) throws Throwable {
		customerEntity = restTemplate.exchange(getBaseUriForCustomerService().toUriString() + "/" + customerId,
                HttpMethod.GET, getHttpEntityForGetRequest(), CustomerModel.class);
	}

	@Then("^I should get a response entity of customer id as \"([^\"]*)\" and$")
	public void i_should_get_a_response_entity_of_customer_id_as_and(String customerId) throws Throwable {
		customerObj = customerEntity.getBody();
		Assert.assertEquals(customerObj.getId().longValue(), Long.parseLong(customerId));
	}

	@Then("^I should get status as \"([^\"]*)\" and response code as \"([^\"]*)\"$")
	public void i_should_get_status_as_and_response_code_as(String status, String statusCode) throws Throwable {
		Assert.assertEquals(customerEntity.getStatusCode().name(), status);
		Assert.assertEquals(customerEntity.getStatusCodeValue(), Integer.parseInt(statusCode));
	}
}
