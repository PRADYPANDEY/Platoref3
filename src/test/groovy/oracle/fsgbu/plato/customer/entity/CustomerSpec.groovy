package oracle.fsgbu.plato.customer.entity

import oracle.fsgbu.plato.customer.domain.entity.Customer
import spock.lang.Specification
import spock.lang.Unroll

class CustomerSpec extends Specification {

	@Unroll("#id getters should return \"#id\", \"#extension\", \"#firstName\", \"#lastName\", \"#age\", \"#county\", \"#country\", \"#email\", \"#phone\" ")
	def "test customer accessors"() {

		given:
		def customer = new Customer()

		when:
		customer.setId(id)
		customer.setExtension(extension)
		customer.setFirstName(firstName)
		customer.setLastName(lastName)
		customer.setAge(age)
		customer.setCounty(county)
		customer.setCountry(country)
		customer.setEmail(email)
		customer.setPhone(phone)
		
		then:
		customer.getId() == id
		customer.getExtension() == extension
		customer.getFirstName() == firstName
		customer.getLastName() == lastName
		customer.getAge() == age
		customer.getCounty() == county
		customer.getCountry() == country
		customer.getEmail() == email
		customer.getPhone() == phone
		
		customer.toString() != null
		
		where:
		id   |  extension  |  firstName    |    lastName     |    age    |   county      |   country   |        email                 | phone         
		1L   |    "4111"   |    "Kaila"    |    "Bernard"    |    38L    |    "CA"       |     "US"    | 	"kaila.nernard@citi.com"  |     "+18976235412"       
		2L   |    "4112"   |    "Andy"     |    "Moore"      |    32L    |    "TX"       |     "US"    |    "andy.moore@boa.com"      |     "+19872345672"
		3L   |    "4113"   |    "Victoria" |    "Bernard"    |    20L    |    "LIVERPOOL"|     "UK"    |    "victoria.b@rbs.com"      |     "+445687239845"
	}
	
	def "Use Spy to test Customer::toString() with partial mocking"() {
		
		given:

		def customerSpy = Spy(Customer)

		customerSpy.getId() >> 1L
		customerSpy.getExtension() >> "4111"
		customerSpy.getFirstName() >> "Kaila"
		customerSpy.getLastName() >> "Bernard"
		customerSpy.getAge() >> 38L
		customerSpy.getCounty() >> "CA"
		customerSpy.getCountry() >> "US"
		customerSpy.getEmail() >> "kaila.nernard@citi.com"
		customerSpy.getPhone() >> "+18976235412"
		
		when:
		def s = customerSpy.toString()

		then:
		println s
	}
}