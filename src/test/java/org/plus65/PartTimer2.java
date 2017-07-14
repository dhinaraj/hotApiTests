package org.plus65;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;

import base.TestLogger;
import base.TestBaseSetup;

import org.apache.http.HttpStatus;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;


import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.RestAssuredConfig.config;
import static org.hamcrest.Matchers.*;


public class PartTimer2  extends TestBaseSetup{
	
  private String bearerCode = null;	
  public static final String LINE_SEPARATOR = System.getProperty("line.separator");

  @BeforeClass
  public void setUp() {
	  
	RestAssured.config = config().logConfig(LogConfig.logConfig().enablePrettyPrinting(true));
	RestAssured.useRelaxedHTTPSValidation();
    RestAssured.baseURI = "https://staging-api.arrowmepro.com";
    
		bearerCode = given().contentType("application/json")
				.body("{\"grant_type\":\"password\", \"client_id\":\"1\",\"client_secret\":\"WUxNdGi5ptoNVtQ8u3HtT4JoU4wTeK1qX6ZN8uCq\",\"username\":\"chuckcwh+131@gmail.com\",\"password\":\"1234qwer\"}")
				.log().body().when().post("/oauth/token").then().extract().jsonPath().get("access_token");
		System.out.println(bearerCode);  
    
  }

  @Test
  public void testStatusCode() {
   boolean isWaiverAccepted =  given().contentType("application/json").accept("application/json").header("Authorization", "Bearer "+this.bearerCode).log().all().
    when().
        get("/ptt/v1/account/full").
    then().contentType(ContentType.JSON).log().body().
        assertThat().
        statusCode(HttpStatus.SC_OK).extract().jsonPath().get("data.isWaiverAccepted");
   System.out.println(isWaiverAccepted); 
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