package com.drivercar.democar.domain.interfaces;


import static com.drivercar.democar.domain.infrastructure.FileUtils.loadFileContents;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = WireMockConfiguration.DYNAMIC_PORT)
@ActiveProfiles("test")
public class TravelRequestAPITestIT {
	
	@Autowired
	private WireMockServer server;
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setup() {
		RestAssured.baseURI = "https://localhost:" + port;
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.authentication = basic("admin", "password");
	}
	
	@Test
	public void testFindNearbyTravelRequests() throws Exception {
		setupServer();
		String passengerId = given()
			.contentType(ContentType.JSON)
			.body(loadFileContents("/requests/passengers_api/create_new_passenger.json"))
			.post("/passengers")
			.then()
			.statusCode(200)
			.body("id", notNullValue())
			.body("name", is("Teste"))
			.extract()
			.body()
			.jsonPath().getString("id")
			;
		Map<String, String> data = new HashMap<>();
		data.put("passengerId", passengerId);
		
		Integer travelRequestId = given()
				.contentType(ContentType.JSON)
				.body(loadFileContents("/requests/travel_requests_api/create_new_request.json", data))
				.post("/travelRequests")
				.then()
				.statusCode(200)
				.body("id", notNullValue())
				.body("origin", is("Avenida Paulista, 1000"))
				.body("status", is("CREATED"))
				.body("_links.passenger.title", is("Teste"))
				.extract()
				.jsonPath()
				.get("id")
				;
	
		given()
		.get("/travelRequests/nearby?currentAddress=Avenida Paulista, 900")
		.then()
		.statusCode(200)
		.body("[0].id", is(travelRequestId))
		.body("[0].origin", is("Avenida Paulista, 1000"))
		.body("[0].destination", is("Avenida Ipiranga, 100"))
		.body("[0].status", is("CREATED"))
		;
		
	}
	
	public void setupServer() throws Exception {
		server.stubFor(get(urlPathEqualTo("/maps/api/directions/json"))
			.withQueryParam("origin", equalTo("Avenida Paulista, 900"))
		    .withQueryParam("destination", equalTo("Avenida Paulista, 1000"))
		    .withQueryParam("Key", equalTo("AIzaSyDMylMBBdxwJckJbbHIeMfcsP6C8-xTvNU"))
		    .willReturn(okJson(loadFileContents("/responses/gmaps/sample_response.json")))
		    );
	}

}
