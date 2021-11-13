package com.drivercar.democar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PassengerAPITestIT {
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setup() {
		RestAssured.port = port;
	}
	
	@Test
	public void testCreatePassenger() {
		String createPassengerJSON = "{\"nome\":\"Sandro M dos Santos\"}";
		given()
			.contentType(io.restassured.http.ContentType.JSON)
			.body(createPassengerJSON)
			.post("/passengers")
			.then()
			.statusCode(200)
			.body("id", notNullValue())
			.body("nome", equalTo("Sandro M dos Santos"));
		
		
	}
		

}
