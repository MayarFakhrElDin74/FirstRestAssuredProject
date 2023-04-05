package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class TestsXmlAPI {



	@Test (priority = 1)

	public void validateResponseCode()

	{
		given().get("https://parabank.parasoft.com/parabank/services/bank/customers/12212")
		.then().assertThat().statusCode(200) ;
	}


	@Test (priority = 2)

	public void validateResponseData ()

	{
		given().get("https://parabank.parasoft.com/parabank/services/bank/customers/12212")
		.then().assertThat().body("Customer.id" , equalTo("12212")).and()
		.assertThat().body("Customer.firstName" , equalTo("John")).and()
		.assertThat().body("Customer.lastName" , equalTo("Smith")).and() 
		.assertThat().body("Customer.address.street" , equalTo("1431 Main St")).and() 
		.assertThat().body("Customer.address.city" , equalTo("Beverly Hills")).and() 
		.assertThat().body("Customer.address.state" , equalTo("CA")).and() 
		.assertThat().body("Customer.address.zipCode" , equalTo("90210")).and()
		.assertThat().body("Customer.phoneNumber" , equalTo("310-447-4121")).and()		
		.assertThat().body("Customer.ssn" , equalTo("622-11-9999")) ;

	}


	@Test (priority = 3)

	public void validateInvalidResponse ()

	{
		String responsedata = RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/customers/12213").asString() ;
		assertEquals(responsedata, "Could not find customer #12213") ;
	}


}
