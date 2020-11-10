Feature: Get Customer By Id

@02-Cust-getCustomerByIdAPITest
Scenario: Get Customer by Id 
	Given the customer id "2" is sent to customer controller to get customer 
	Then I should get a response entity of customer id as "2" and 
	Then I should get status as "OK" and response code as "200"

