package oracle.fsgbu.plato.customer.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the CUSTOMER database table.
 * 
 */
@Entity
@Table(name = "CUSTOMER")
@NamedQueries({ @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
				@NamedQuery(name = "Customer.findLatest", query = "SELECT c FROM Customer c WHERE c.id = (SELECT MAX(cc.id) FROM Customer cc)") })
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CUSTOMER_ID_GENERATOR", sequenceName = "CUSTOMER_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_ID_GENERATOR")
	private Long id;

	@Lob
	@Column(name = "EXTENSION", columnDefinition = "CLOB")
	private String extension;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "AGE")
	private long age;

	@Column(name = "SEX")
	private String sex;

	@Column(name = "COUNTY")
	private String county;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	private String phone;

	public Customer() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", extension=" + extension + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", age=" + age + ", sex=" + sex + ", county=" + county + ", country=" + country + ", email=" + email
				+ ", phone=" + phone + "]";
	}

}