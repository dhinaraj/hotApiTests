package org.plus65;


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


public class PartTimer  extends TestBaseSetup{
	
  private String bearerCode = null;	
  public static final String LINE_SEPARATOR = System.getProperty("line.separator");
  
  TestLogger testLog= new TestLogger();
  
  @BeforeClass
  public void setUp() {
	RestAssured.config = config().logConfig(LogConfig.logConfig().enablePrettyPrinting(true)).sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
	RestAssured.useRelaxedHTTPSValidation();
    RestAssured.baseURI = "https://staging-api.arrowmepro.com";
   

    
  }

  
	@Test
	public void getAccountDetails() throws FileNotFoundException {
		
		TestLogger.logStep("Post oauth/token");
		bearerCode = given().config(TestLogger.logConfig()).contentType("application/json")
				.body("{\"grant_type\":\"password\", \"client_id\":\"1\",\"client_secret\":\"WUxNdGi5ptoNVtQ8u3HtT4JoU4wTeK1qX6ZN8uCq\",\"username\":\"chuckcwh+131@gmail.com\",\"password\":\"1234qwer\"}")
				.log().all().when().post("/oauth/token").then().extract().jsonPath().get("access_token");
		
		TestLogger.logStep("Bearer Token = " + bearerCode);

		TestLogger.logStep("Get account/full");
		boolean isWaiverAccepted = given().config(TestLogger.logConfig()).contentType("application/json")
				.accept("application/json").header("Authorization", "Bearer " + this.bearerCode).log().all().when()
				.get("/ptt/v1/account/full").then().contentType(ContentType.JSON).log().body().assertThat()
				.statusCode(HttpStatus.SC_OK).extract().jsonPath().get("data.isWaiverAccepted");
		Assert.assertFalse(isWaiverAccepted);
	}
	
	@Test
	public void getAccountDetails2() throws FileNotFoundException {
		
		TestLogger.logStep("Post oauth/token");
		bearerCode = given().config(TestLogger.logConfig()).contentType("application/json")
				.body("{\"grant_type\":\"password\", \"client_id\":\"1\",\"client_secret\":\"WUxNdGi5ptoNVtQ8u3HtT4JoU4wTeK1qX6ZN8uCq\",\"username\":\"chuckcwh+131@gmail.com\",\"password\":\"1234qwer\"}")
				.log().all().when().post("/oauth/token").then().extract().jsonPath().get("access_token");
		
		TestLogger.logStep("Bearer Token = " + bearerCode);

		TestLogger.logStep("Get account/full");
		boolean isWaiverAccepted = given().config(TestLogger.logConfig()).contentType("application/json")
				.accept("application/json").header("Authorization", "Bearer " + this.bearerCode).log().all().when()
				.get("/ptt/v1/account/full").then().contentType(ContentType.JSON).log().body().assertThat()
				.statusCode(HttpStatus.SC_OK).extract().jsonPath().get("data.isWaiverAccepted");
		Assert.assertFalse(isWaiverAccepted);
	}
  
	
  
	
  

 
  
  

/*
  @Test
  public void testGetRequest() {
    given().
        contentType("application/json").
    when().
        get("/users").
    then().
        assertThat().
        body("userId", hasItems(2, 3, 4)).log();
  }

  @Test
  public void testSimpleGetRequest() {
    given().

    when().
        get("/users/1").
    then().
        assertThat().
        body("userId", equalTo(1)).
        body("userName", equalTo("Robert")).
        body("employer", equalTo("facebook")).
        body("location.state", equalTo("California")).
        body("location.city", equalTo("San Jose"));
  }


  @Test
  public void testPostRequest() {

    given().
        contentType("application/json").
        body("[{\"userName\":\"Andy\",\"employer\":\"Google\",\"location\":{\"state\":\"California\",\"city\":\"Mountain View\"}}]").
    when().
        post("/users").
    then().
        assertThat().
        body("userName", hasItems("Andy"));
  }

  @Test
  public void testPutRequest() {
    int userId = 1;

    given().
        contentType("application/json").
    when().
        body("{\"userName\":\"Taylor\"}").
            put("/users/" + userId).
    then().
        statusCode(HttpStatus.SC_OK).
        body("userName", equalTo("Taylor"));

  }

  @Test
  public void testDeleteRequest() {
    int userId = 7;
    given().

        when().
        delete("/users/" + userId).
    then().
        statusCode(HttpStatus.SC_OK);
  }

  @Test
  public void testResponseData() {
    Response response =
        given().
            contentType(ContentType.JSON).
        when().
            get("/users/5").
        then().
            extract().response();

    String userName = response.path("userName");
    String userCity = response.path("location.city");

    Assert.assertTrue(userName.equals("Steve"));
    Assert.assertTrue(userCity.equals("San Francisco"));
  }

  @Test
  public void testUsingJsonPath() {
    String json = get("/users/5").asString();

    JsonPath jsonPath = new JsonPath(json).setRoot("location");
    String state = jsonPath.getString("state");
    String city = jsonPath.getString("city");
    Assert.assertTrue(state.equals("California"));
    Assert.assertTrue(city.equals("San Francisco"));

  }*/
}