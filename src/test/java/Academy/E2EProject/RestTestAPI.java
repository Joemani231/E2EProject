

/*
 *     1)  Testcase to validate the response of the Rest API 
 *     
 *     2)  The following acceptance criteria is validated
 *         
 *         Acceptance Criteria:

 *         Name = "Carbon credits"

 *         CanRelist = true

 *         The Promotions element with Name = "Gallery" has a Description that contains the text "2x larger image"
 *         
 *     3)  The testcase can be run using testng.xml
 *     
 *     4)  Logs are in the folder:logs folder 
 *     5)  Extent Reports are generated in the folder: reports folder
 *     
 *   
 */

package Academy.E2EProject;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.base;


public class RestTestAPI extends base {
	static ExtentTest test;
	static ExtentReports report;
	public static Logger log = LogManager.getLogger(base.class.getName());
	
	/*
	 * Testcase to validate the response of the Rest API 
	 */
	@Test(dataProvider="getData")
	public void getMethodValidate(String Name, String Relist, String promotionsName, String promotionsDescription) throws IOException {
		
		//getting the base uri from properties file
		
		Boolean b=false;
		Boolean relist =false;
		Boolean bRelist = false;
		//String baseURI = getbaseURI();
		//System.out.println(baseURI);
		RestAssured.baseURI = "https://api.tmsandbox.co.nz/v1/Categories/6327";
		RequestSpecification httpRequest = RestAssured.given();
		 Response response=httpRequest.request(Method.GET,"/Details.json?catalogue=false");
		 
		log.info("Received GET response");
		//RestAssured.baseURI =baseURI;
		
		
		
		String responseBody = response.getBody().asPrettyString();
		System.out.println("Response body is:"+ responseBody);
		
		try {
		if(!(responseBody.isEmpty())) {
			log.info("Response body is not null");
			
		}
		}catch(Exception e) {
			log.error("Response body is null");
		}
		
		try {
		int statuscode = response.getStatusCode();
		System.out.println("Status code:"+statuscode);
		Assert.assertEquals(statuscode, 200);
		
		if(statuscode == 200) {
			log.info("Success code valid");
			
		}
		}catch(Exception e) {
			
			log.error("Success code not valid");
		}
		
		try {
		String name= response.jsonPath().getString("Name");
		System.out.println(name);
		Assert.assertEquals(name, Name);
		if(name.equals(Name)) {
			
			log.info("Name is valid");
		}else {
			
			log.error("Name is not valid");
		}
		
		if(Relist.equals("true")) {
			bRelist=true;
		}
		relist = response.jsonPath().getBoolean("CanRelist");
		System.out.println(relist);
		Assert.assertEquals(relist, bRelist);
		if(relist == bRelist) {
			log.info("CanRelist is valid");
			
		}else {
			log.error("CanRelist is not valid");
			
		}
		
		ArrayList<String>  promolist = response.jsonPath().get("Promotions");
		Object[] obj = promolist.toArray();
		
		for(int i=0;i<obj.length;i++) {
			if(obj[i].toString().contains(promotionsName)&&obj[i].toString().contains(promotionsDescription)){
				b = true;
				//Assert.assertTrue(b);
				break;
			}
		}
		
		if(b == true) {
			log.info("Promotions name and Description are valid");
			
			Assert.assertTrue(true, "The promotion name and description are valid");
		}else {
			log.error("Promotions name and description are not valid");
			
			Assert.assertTrue(b, "Promotions name and description are not valid");
			
		}
		
		
		
		}catch(Exception e) {
			log.error("Payload validation not successfull");
			
		}
		/*String pomoList= response.jsonPath().getString("Promotions[1].Name");
		String description = response.jsonPath().getString("Promotions[1].Description");
		System.out.println(pomoList);
		System.out.println(description);
		Assert.assertEquals(pomoList, "Gallery");
		Assert.assertEquals(description, "Good position in category");
		if((pomoList.equals("Gallery")) && (description.equals("Good position in category"))) {
			
			System.out.println("Promotions name and Description are valid");
			
		}else {
			System.out.println("Promotions name and Description are not validated");
		}*/
		
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		String path = System.getProperty("user.dir")+ "/src/test/java/Academy/E2EProject/Rest.xlsx";
		
		int rownum = XlUtilities.getRowCount(path, "RestRequests");
		int cellnum = XlUtilities.getCellCount(path, "RestRequests", rownum);
		//String obj
		String[][] obj = new String[rownum][cellnum];
		
		for(int i=1; i<=rownum;i++) {
			for(int j=0; j<cellnum; j++) {
				
				obj[i-1][j] = XlUtilities.getCellData(path, "RestRequests", i, j);
			}
		}
			
		return obj;
		
	}
}
