Feature: Get Database Connection Test

@01-Cust-CreateDBConnection
@SeedDataForCustomerTest 
Scenario: Setup valid jdbc connection 
	Given the jdbc connection for connecting to Customer db as : 
		| username  | password  | url  |                      
		| DG_BOA_CUSTOMERS  | BOA_CUSTOMERS   | jdbc:oracle:thin:@10.184.155.219:1521/orcl.in.oracle.com |		
		
	Then execute sql statements for CustomerAPITest event from "src/cucumberTest/resources/data/customerSeedData.sql"
	Then close the jdbc connection for CustomerAPITest event
