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
import entities.Hobby;
import entities.Person;
import facades.SearchFacade_Impl;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
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
    private static final String SERVER_URL = "http://localhost/api/";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    private static ArrayList<Person> testPersons = new ArrayList();
    private static ArrayList<Hobby> hobbies1 = new ArrayList();
    private static ArrayList<Hobby> hobbies2 = new ArrayList();
    private static ArrayList<Hobby> hobbies3 = new ArrayList();
    
    static HttpServer startServer()
    {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    public SearchResourceTest()
    {
    }

    @BeforeAll
    public static void setUpClass()
    {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);

        httpServer = startServer();

        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;

        RestAssured.defaultParser = Parser.JSON;
        
        hobbies1.add(new Hobby("MHW", "Monster Hunter World"));
        hobbies1.add(new Hobby("WF", "Warframe"));
        hobbies2.add(new Hobby("Origami", "The art of folding paper"));
        hobbies2.add(new Hobby("MHW", "Monster Hunter World"));
        hobbies3.add(new Hobby("Living", "Eating and sleeping"));

        testPersons.add(new Person("the@king.com", "Johnny", "Ringo", hobbies1));
        testPersons.add(new Person("dimwit@email.com", "Dimwit", "DaBoi", hobbies2));
        testPersons.add(new Person("george@pres.com", "George", "Washington", hobbies3));
    }

    @BeforeEach
    public void setUp() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            for (Person p : testPersons) {
                em.getTransaction().begin();
                em.persist(p);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    @AfterEach
    public void tearDown() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    @AfterAll
    public static void closeTestServer()
    {
        httpServer.shutdownNow();
    }
    




    /**
     * Test of addPerson method, of class SearchResource.
     */
//    @Test
//    public void testAddPerson()
//    {
//    String payload = "{\n"
//            + "  \"firstName\": \"Johnny\",\n"
//            + "  \"lastName\": \"Ringo\",\n"
//            + "  \"email\": \"the@king.com\",\n"
//            + "}";
//
//        given()
//        .contentType("application/json")
//        .accept("application/json")
//        .body(payload)
//        .post("/create/person")
//        .then()
//        .assertThat()
//        .statusCode(HttpStatus.OK_200.getStatusCode())
//        .body("firstName", equalTo("Johnny"))
//        .body("lastName", equalTo("Ringo"))
//        .body("email", equalTo("the@king.com"));
//    }
}
