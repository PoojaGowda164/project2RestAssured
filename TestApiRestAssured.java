import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.testng.Assert;   //used to validate response status

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestApiRestAssured{
	static Logger log = Logger.getLogger(TestApiRestAssured.class.getName());



	@Test
	public void postmanGetResponse() {
		log.debug("Hello, Putting get response");



		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "https://reqres.in/api/users?page=2";
		// Get the RequestSpecification of the request to be sent to the server
		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.get("");

		// Get the status code of the request.
		//If request is successful, status code will be 200
		//If request of put is successful, status code will be 201
		int statusCode = response.getStatusCode();
		System.out.println("get reposne code :"+ statusCode);

		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/,
				"Correct status code returned");
		log.info("get response code is verified");


	}
	@Test
	public void postmanPostResponse() {



		JSONObject jsonObject = new JSONObject();

		//insert key value pair to jsonObject
		jsonObject.put("id", "55");
		jsonObject.put("email", "formoern@reqres.in");
		jsonObject.put("first_name", "form");
		jsonObject.put("last_name", "oern");
		jsonObject.put("avatar","https://reqres.in/img/faces/7-image.jpg");

		RequestSpecification resp=  given().log().all().header("authorization", "Bearer 0431655cfe7ba40a791e0ce32d83ad33363348919c11627f409a3228f205e15f23")
				.accept(ContentType.JSON)
				.contentType("application/json")
				.and()
				.body(jsonObject.toString());

		Response response = resp.post("https://reqres.in/api/users?page=2"); //hit the post end point

		System.out.println("get reposne code :"+response.getStatusCode());

		// Assert that correct status code is returned.
		Assert.assertEquals(response.getStatusCode() /*actual value*/, 201 /*expected value*/,
				"Correct status code returned");
		log.info("post response code is verified");


	}
}
