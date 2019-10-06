/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dto.HobbyDTO_IN;
import dto.HobbyDTO_OUT;
import dto.MovieInfo;
import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author
 */
public class SearchResourceTest
{
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    //Read this line from a settings-file  since used several places
    //private static final String TEST_DB = "jdbc:mysql://localhost:3307/CA1_test";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    
    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }
    
    public SearchResourceTest()
    {
    }
    
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);

        httpServer = startServer();

        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;

        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        httpServer.shutdownNow();
    }
    

    /**
     * Test of addPerson method, of class SearchResource.
     */
//    @Test
//    public void testAddPerson()
//    {
//        String payload = "{\n" +
//"      \"fName\": \"Barack\",\n" +
//"      \"lName\": \"Obama\",\n" +
//"      \"phone\": \"98775433\"}";
//        
//        given()
//        .contentType("application/json")
//        .body(payload)
//        .post("/person/create")
//        .then()
//        .assertThat()
//        .statusCode(HttpStatus.OK_200.getStatusCode())
//        .body("fName", equalTo("Barack"))
//        .body("lName", equalTo("Obama"))
//        .body("phone", equalTo("98775433"));
//    }

    
}
