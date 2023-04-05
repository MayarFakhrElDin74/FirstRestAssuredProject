package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;


public class TestsJsonAPI {



	@Test (priority = 1)

	public void validateResponseCode()

	{
		given().get("https://reqres.in/api/users?page=2")
		.then().assertThat().statusCode(200) ;
	}



	@Test (priority = 2)

	public void validateResponseData ()

	{
		given().get("https://reqres.in/api/users?page=2").then()
		.assertThat().body("data[0].'email'" , equalTo("michael.lawson@reqres.in")).and()
		.assertThat().body("data[0].'first_name'" , equalTo("Michael")).and()
		.assertThat().body("data[0].'last_name'" , equalTo("Lawson")).and()
		.assertThat().body("data[0].'avatar'" , equalTo("https://reqres.in/img/faces/7-image.jpg")) ;
		
	}


	@Test (priority = 3)

	public void validateInvalidResponse ()

	{
		String response = RestAssured.get("https://reqres.in/api/users?page=1000000000000").asString() ;
		assertTrue(response.contains("server costs are appreciated!")) ;
		
	}


}
