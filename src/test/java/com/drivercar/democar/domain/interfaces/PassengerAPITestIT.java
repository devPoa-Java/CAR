package com.drivercar.democar.domain.interfaces;


import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PassengerAPITestIT {
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setup() {
		RestAssured.baseURI = "https://localhost:" + port;
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.authentication = basic("admin", "password");
	}
	
	@Test
	public void testCreatePassenger() {
		String createPassengerJSON = "{\"name\":\"Teste\"}";
		given()
			.contentType(io.restassured.http.ContentType.JSON)
			.body(createPassengerJSON)
			.post("/passengers")
			.then()
			.statusCode(200)
			.body("id", notNullValue())
			.body("name", equalTo("Teste"));
		
	}
		

}
