/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

public class SearchResourceTest
{

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api/";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    private static Person person1 = new Person();
    private static Person person2 = new Person();
    private static Person person3 = new Person();
    private static Phone phone1 = new Phone();
    private static Phone phone2 = new Phone();
    private static Phone phone3 = new Phone();
    private static Phone phone4 = new Phone();
    private static CityInfo city1 = new CityInfo();
    private static CityInfo city2 = new CityInfo();
    private static Address address1 = new Address();
    private static Address address2 = new Address();
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
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        httpServer = startServer();

        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;

        RestAssured.defaultParser = Parser.JSON;

        //Hobbies
        hobbies1.add(new Hobby("MHW", "Monster Hunter World"));
        hobbies1.add(new Hobby("WF", "Warframe"));
        hobbies2.add(new Hobby("Origami", "The art of folding paper"));
        hobbies2.add(new Hobby("MHW", "Monster Hunter World"));
        hobbies3.add(new Hobby("Living", "Eating and sleeping"));

        //Phones
        phone1 = new Phone(13371337, "Cellphone");
        phone2 = new Phone(13376887, "Cellphone");
        phone3 = new Phone(12345678, "Home");
        phone4 = new Phone(98765432, "Cellphone");

        //City info
        city1 = new CityInfo("2800", "Lyngby");
        city2 = new CityInfo("8000", "Århus");

        //Addresses
        address1 = new Address("Klampenborgvej", "Nr. 16, 1. sal tv.", city1);
        address2 = new Address("Busgaden", "Under broen", city2);

        //Persons
        person1 = new Person("the@king.com", "Johnny", "Ringo", hobbies1);
        person2 = new Person("dimwit@email.com", "Dimwit", "DaBoi", hobbies2);
        person3 = new Person("george@pres.com", "George", "Washington", hobbies3);

        person1.addPhone(phone1);
        person1.addPhone(phone2);
        person3.addPhone(phone3);
        person2.addPhone(phone4);
        person1.setAddress(address1);
        person2.setAddress(address1);
        person3.setAddress(address2);
        
    }

    @BeforeEach
    public void setUp() throws Exception
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(person1);
            em.persist(person2);
            em.persist(person3);
            em.persist(phone1);
            em.persist(phone2);
            em.persist(phone3);
            em.persist(phone4);
            em.persist(city1);
            em.persist(city2);
            em.persist(address1);
            em.persist(address2);
            em.getTransaction().commit();
        } catch (Exception e)
        {
            em.getTransaction().rollback();
        } finally
        {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() throws Exception
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.getTransaction().commit();

        } catch (Exception e)
        {
            em.getTransaction().rollback();
        } finally
        {
            em.close();
        }
    }

    @AfterAll
    public static void closeTestServer()
    {
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    /**
     * Test of addPerson method, of class SearchResource.
     */
    @Test
    public void testAddPerson()
    {
        String payload = "{\n"
        + "  \"firstName\": \"Johnny\",\n"
        + "  \"lastName\": \"Ringo\",\n"
        + "  \"email\": \"the@king.com\"\n"
        + "}";

        given()
        .contentType("application/json")
        .accept("application/json")
        .body(payload)
        .post("/search/create/person")
        .then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("firstName", equalTo("Johnny"))
        .body("lastName", equalTo("Ringo"))
        .body("email", equalTo("the@king.com"));
    }

    @Test
    public void testGetPersonByFullName()
    {
        given()
        .contentType("application/json")
        .accept("application/json")
        .get("/search/person/Johnny Ringo")
        .then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("[0].firstName", equalTo("Johnny"))
        .body("[0].lastName", equalTo("Ringo"))
        .body("[0].email", equalTo("the@king.com"))
        .body("[0].hobbies[0].description", equalTo("Monster Hunter World"))
        .body("[0].hobbies[0].name", equalTo("MHW"))
        .body("[0].hobbies[1].description", equalTo("Warframe"))
        .body("[0].address.street", equalTo("Klampenborgvej"))
        .body("[0].address.cityInfo.city", equalTo("Lyngby"))
        .body("[0].address.cityInfo.zipCode", equalTo("2800"))
        .body("[0].phones[0].number", equalTo(13371337));
    }
}
