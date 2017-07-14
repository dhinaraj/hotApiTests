package org.plus65.UsefulFunctions;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;
import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.config.RestAssuredConfig;

import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static com.jayway.restassured.config.RestAssuredConfig.config;
import com.jayway.restassured.config.SSLConfig;

import base.TestLogger;
import base.TestBaseSetup;

import org.apache.commons.io.output.WriterOutputStream;
import org.apache.http.HttpStatus;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;


import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.lang.reflect.Method;


public class MultiplePartimerJobApply  extends TestBaseSetup{
	
  private String bearerCode = null;	
  public static final String LINE_SEPARATOR = System.getProperty("line.separator");
  
  TestLogger testLog= new TestLogger();
  
  private String partimerEmailList[] = new String[] { "dailytestjob+service1+pt1@gmail.com",
			"dailytestjob+service1+pt2@gmail.com", "dailytestjob+service1+pt3@gmail.com", "dailytestjob+service1+pt4@gmail.com",
			"dailytestjob+service1+pt5@gmail.com", "dailytestjob+service1+pt6@gmail.com", "dailytestjob+service1+pt7@gmail.com",
			"dailytestjob+service1+pt8@gmail.com", "dailytestjob+service1+pt9@gmail.com", "dailytestjob+service1+pt10@gmail.com",
			"dailytestjob+service1+pt11@gmail.com", "dailytestjob+service1+pt12@gmail.com", "dailytestjob+service1+pt13@gmail.com",
			"dailytestjob+service1+pt14@gmail.com", "dailytestjob+service1+pt15@gmail.com", "dailytestjob+service1+pt16@gmail.com",
			"dailytestjob+service1+pt17@gmail.com", "dailytestjob+service1+pt18@gmail.com", "dailytestjob+service1+pt19@gmail.com",
			"dailytestjob+service1+pt20@gmail.com", "dailytestjob+service1+pt21@gmail.com", "dailytestjob+service1+pt22@gmail.com",
			"dailytestjob+service1+pt23@gmail.com", "dailytestjob+service1+pt24@gmail.com", "dailytestjob+service1+pt25@gmail.com",
			"dailytestjob+service1+pt26@gmail.com", "dailytestjob+service1+pt27@gmail.com", "dailytestjob+service1+pt28@gmail.com",
			"dailytestjob+service1+pt29@gmail.com", "dailytestjob+service1+pt30@gmail.com", "dailytestjob+service1+pt31@gmail.com",
			"dailytestjob+service1+pt32@gmail.com", "dailytestjob+service1+pt33@gmail.com", "dailytestjob+service1+pt34@gmail.com",
			"dailytestjob+service1+pt35@gmail.com", "dailytestjob+service1+pt36@gmail.com", "dailytestjob+service1+pt37@gmail.com",
			"dailytestjob+service1+pt38@gmail.com", "dailytestjob+service1+pt39@gmail.com", "dailytestjob+service1+pt40@gmail.com",
			"dailytestjob+service1+pt41@gmail.com", "dailytestjob+service1+pt42@gmail.com", "dailytestjob+service1+pt43@gmail.com",
			"dailytestjob+service1+pt44@gmail.com", "dailytestjob+service1+pt45@gmail.com", "dailytestjob+service1+pt46@gmail.com",
			"dailytestjob+service1+pt47@gmail.com", "dailytestjob+service1+pt48@gmail.com", "dailytestjob+service1+pt49@gmail.com",
			"dailytestjob+service1+pt50@gmail.com", "dailytestjob+service1+pt51@gmail.com", "dailytestjob+service1+pt52@gmail.com",
			"dailytestjob+service1+pt53@gmail.com", "dailytestjob+service1+pt54@gmail.com", "dailytestjob+service1+pt55@gmail.com",
			"dailytestjob+service1+pt56@gmail.com", "dailytestjob+service1+pt57@gmail.com", "dailytestjob+service1+pt58@gmail.com",
			"dailytestjob+service1+pt59@gmail.com", "dailytestjob+service1+pt60@gmail.com" };
  
  
  
  @BeforeClass
  public void setUp() {
	RestAssured.config = config().logConfig(LogConfig.logConfig().enablePrettyPrinting(true)).sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
	RestAssured.useRelaxedHTTPSValidation();
    RestAssured.baseURI = "https://staging-api.arrowmepro.com";
  }
	
	@Test
	public void applyJobs() throws FileNotFoundException {
		
		 for (int i = 0; i <= 30; i++) {
			 String partTimerEmail = partimerEmailList[i];
			 System.out.println("Beginning to Apply for job created by:  for Partimer: " +(i + 1));
		
		
		TestLogger.logStep("Post oauth/token");
		bearerCode = given().config(TestLogger.logConfig()).contentType("application/json")
				.body("{\"grant_type\":\"password\", \"client_id\":\"1\",\"client_secret\":\"WUxNdGi5ptoNVtQ8u3HtT4JoU4wTeK1qX6ZN8uCq\",\"username\":\""+partTimerEmail+"\",\"password\":\"123qwe\"}")
				.log().all().when().post("/oauth/token").then().extract().jsonPath().get("access_token");
		
		TestLogger.logStep("Bearer Token = " + bearerCode);

		TestLogger.logStep("Post /ptt/v1/job/all/2061/apply");
		given().config(TestLogger.logConfig()).contentType("application/json")
				.accept("application/json").header("Authorization", "Bearer " + this.bearerCode).log().all().when()
				.post("/ptt/v1/job/all/2063/apply").then().log().body().assertThat()
				.statusCode(HttpStatus.SC_OK);
		
		 }
		
	}
  
	
  

}