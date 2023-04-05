package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ExcelReader;
import io.restassured.path.xml.XmlPath;

import static io.restassured.RestAssured.get ;

public class TestsXmlAPIWithExcel {



	@DataProvider(name = "test_data")

	public Object [][] getExcelData() throws InvalidFormatException, IOException

	{
		ExcelReader read = new ExcelReader();
		return read.getExcelData("sheet1");

	}



	@Test (dataProvider = "test_data")

	public void dataDrivenTestCase (String customer_id , String first_Name , String last_Name ,
			String street, String city , String state , String zip_Code , String phone_Number , String ssn)

	{
		String xml = get("https://parabank.parasoft.com/parabank/services/bank/customers/" + customer_id)
				.andReturn().asString() ;

		XmlPath xmlPath = new XmlPath(xml).setRootPath("customer") ;

		String cust_id = xmlPath.getString("id") ;
		String fn = xmlPath.getString("firstName") ;
		String ln = xmlPath.getString("lastName") ;
		String strt = xmlPath.getString("address.street") ;
		String cty = xmlPath.getString("address.city") ;
		String stt = xmlPath.getString("address.state") ;
		String zip = xmlPath.getString("address.zipCode") ;
		String pn = xmlPath.getString("phoneNumber") ;
		String sn = xmlPath.getString("ssn") ;	

		assertEquals(cust_id , customer_id);
		assertEquals(fn , first_Name );
		assertEquals(ln , last_Name);
		assertEquals(strt , street );
		assertEquals(cty , city);
		assertEquals(stt, state);
		assertEquals(zip , zip_Code );
		assertEquals(pn , phone_Number );
		assertEquals(sn , ssn);

	}


}
