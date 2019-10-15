package rest;

import dto.PersonDTO_OUT;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
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
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
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
    private static Person person4 = new Person();
    private static Phone phone1 = new Phone();
    private static Phone phone2 = new Phone();
    private static Phone phone3 = new Phone();
    private static Phone phone4 = new Phone();
    private static Phone phone5 = new Phone();
    private static CityInfo city1 = new CityInfo();
    private static CityInfo city2 = new CityInfo();
    private static CityInfo city3 = new CityInfo();
    private static Address address1 = new Address();
    private static Address address2 = new Address();
    private static Address address3 = new Address();
    private static ArrayList<Hobby> hobbies1 = new ArrayList();
    private static ArrayList<Hobby> hobbies2 = new ArrayList();
    private static ArrayList<Hobby> hobbies3 = new ArrayList();
    private static ArrayList<Hobby> hobbies4 = new ArrayList();

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
        Hobby h1 = new Hobby("MHW", "Monster Hunter World");
        Hobby h2 = new Hobby("WF", "Warframe");
        Hobby h3 = new Hobby("Origami", "The art of folding paper");
        Hobby h4 = new Hobby("Living", "Eating and sleeping");
        h1.setId(1);
        h2.setId(2);
        h3.setId(3);
        h4.setId(4);
        hobbies1.add(h1);
        hobbies1.add(h2);
        hobbies2.add(h1);
        hobbies2.add(h3);
        hobbies3.add(h4);
        hobbies4.add(h1);

        //Phones
        phone1 = new Phone(13371337, "Cellphone");
        phone2 = new Phone(13376887, "Cellphone");
        phone3 = new Phone(12345678, "Home");
        phone4 = new Phone(98765432, "Cellphone");
        phone5 = new Phone(88888888, "Cellphone");

        //City info
        city1 = new CityInfo("2800", "Lyngby");
        city2 = new CityInfo("8000", "Århus");
        city3 = new CityInfo("9990", "Skagen");

        //Addresses
        address1 = new Address("Klampenborgvej", "Nr. 16, 1. sal tv.", city1);
        address2 = new Address("Busgaden", "Under broen", city2);
        address3 = new Address("Savvej", "Ingen info", city3);

        //Persons
        person1 = new Person("the@king.com", "Johnny", "Ringo", hobbies1);
        person2 = new Person("dimwit@email.com", "Dimwit", "DaBoi", hobbies2);
        person3 = new Person("george@pres.com", "George", "Washington", hobbies3);
        person4 = new Person("legendary@weare.com", "Iza", "Evelynn", hobbies4);

        //Add persons to phones
        phone1.setPerson(person1);
        phone2.setPerson(person1);
        phone3.setPerson(person3);
        phone4.setPerson(person2);
        phone5.setPerson(person4);
        
        //Add phones to person
        person1.addPhone(phone1);
        person1.addPhone(phone2);
        person3.addPhone(phone3);
        person2.addPhone(phone4);
        person4.addPhone(phone5);
        
        person1.setAddress(address1);
        person2.setAddress(address1);
        person3.setAddress(address2);
        person4.setAddress(address3);
        
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) throws Exception
    {
        if (testInfo.getTags().contains("skipBeforeEach")){
            return; //do not perform SetUp
        } //else set it up (https://stackoverflow.com/a/49694288)
            
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(person1);
            em.persist(city1);
            em.persist(phone1);
            em.persist(phone2);
            em.persist(address1);
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.persist(person2);
            em.persist(phone4);
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.persist(person3);
            em.persist(phone3);
            em.persist(city2);
            em.persist(address2);
            em.persist(city3);
            em.persist(address3);
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
            em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNamedQuery("CityInfo.deleteAllRows").executeUpdate();
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

    @Test
    public void testFailAddPerson_400()
    {
        String payload = "{\n"
        + "  \"firstName\": \"Johnny\",\n"
        + "  \"lastName\": \"Ringo\"\n"
        + "}";

        given()
        .contentType("application/json")
        .accept("application/json")
        .body(payload)
        .post("/search/create/person")
        .then()
        .assertThat()
        .statusCode(HttpStatus.BAD_REQUEST_400.getStatusCode())
        .body("message", equalTo("Missing input"));
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
    public void testFailGetPersonByFullName_404()
    {
        given()
        .contentType("application/json")
        .accept("application/json")
        .get("/search/person/Johnny Dingo")
        .then()
        .assertThat()
        .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode())
        .body("message", equalTo("No persons with that name in database"));
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
    
     @Test
    public void testGetPersonInfoByPhone() {
        //Arrange
        PersonDTO_OUT expResult = new PersonDTO_OUT(person1);
        long phone = expResult.getPhones().get(1).getNumber();
        PersonDTO_OUT result;
        
        //Act
         result = get("/search/phone?phone={phone}", phone). then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .extract()
                .as(PersonDTO_OUT.class);

        //Assert
        assertThat((result), equalTo(expResult));
    }
    
    @Test
    public void testGetPersonInfoByPhone_Exception1() {
        long phone =  -1; //bad input
        
        given()
                .get("/search/phone?phone={phone}", phone).then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST_400.getStatusCode()).
                body("code", equalTo(400)).
                body("message", equalTo("Bad phone input"));
    }
    
    @Test
    public void testGetPersonInfoByPhone_Exception2() {
        long phone = 25252525; //good input but not found
        
        given()
                .get("/search/phone?phone={phone}", phone).then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode()).
                body("code", equalTo(404)).
                body("message", equalTo("No user with that phone number exists"));
    }
    
    @Test
    public void testGetPersonsByCity(){
        //Arrange
        List<PersonDTO_OUT> expResult = new ArrayList();
        expResult.add(new PersonDTO_OUT(person1)); //both from city1
        expResult.add(new PersonDTO_OUT(person2)); //both from city1
        String zip = city1.getZipCode(); //2800
        String city = city1.getCity(); //Lyngby
        List<PersonDTO_OUT> result;
        
        //Act
         result = get("search/city?zip={zip}&city={city}", zip, city). then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .extract().body()
                .jsonPath().getList(".", PersonDTO_OUT.class); //https://stackoverflow.com/a/53006523

        //Assert
        assertThat((result), equalTo(expResult));
    }
    
    @Test
    public void testGetPersonsByCity_Exception1(){
        //Arrange
        String zip = ""; //bad input
        String city = ""; 
        
        given()
                .get("search/city?zip={zip}&city={city}", zip, city).then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST_400.getStatusCode()).
                body("code", equalTo(400)).
                body("message", equalTo("Missing Input"));
    }
    
    @Test
    public void testGetPersonsByCity_Exception2(){
        //Arrange
        String zip = "123"; //Incorrect input
        String city = "NoCity"; 
        
        given()
                .get("search/city?zip={zip}&city={city}", zip, city).then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode()).
                body("code", equalTo(404)).
                body("message", equalTo("No Persons lives in that city."));
    }
    
    @Test 
    public void testGetAllZipCodes(){
        //Arrange
        List<Integer> expResult = new ArrayList();
        expResult.add(Integer.valueOf(city1.getZipCode())); //2800
        expResult.add(Integer.valueOf(city2.getZipCode())); //8000
        expResult.add(Integer.valueOf(city3.getZipCode())); //9900
        List<Integer> result;
        
        //Act
        result = get("/search/zip/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .extract().body()
                .jsonPath().getList("", int.class); //could probably be Integer.class but I'm scared to change anything now that it works.

        //Assert
////        System.out.println(result.getClass());
////        System.out.println(expResult.getClass());
////        System.out.println(expResult.get(0).getClass());
////        System.out.println(result.get(0).getClass());
////        System.out.println(result);
////        System.out.println(expResult);
        //assertThat((result), equalTo(expResult));
        //assertEquals(expResult, result);
        //assertThat(expResult, Matchers.containsInAnyOrder(result.get(0), result.get(1), result.get(2)));
        assertThat("ARE THEY EQUAL", result, containsInAnyOrder(expResult.toArray()));
    }
    
    /**
     * Simulates an empty db
     */
    @Tag("skipBeforeEach")
    @Test public void testGetAllZipCodes_Exception1(){
        //Assert
        given().
                get("/search/zip/").then().
                statusCode(HttpStatus.BAD_REQUEST_400.getStatusCode()).
                assertThat().
                body("code", equalTo(400)).
                body("message", equalTo("No cities in the database."));
    }
    
    @Test
    public void testFailGetPersonsByHobby_NoPersonsWithHobby()
    {
        given()
        .contentType("application/json")
        .accept("application/json")
        .get("/search/hobby/?hobby=LoL")
        .then()
        .assertThat()
        .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode())
        .body("message", equalTo("No persons with hobby in database"));
    }
    
    @Test
    public void testGetPersonsByHobby()
    {
        given()
        .contentType("application/json")
        .accept("application/json")
        .get("/search/hobby/?hobby=MHW")
        .then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("[0].firstName", equalTo("Johnny"))
        .body("[1].firstName", equalTo("Dimwit"))
        .body("[0].lastName", equalTo("Ringo"))
        .body("[1].lastName", equalTo("DaBoi"))
        .body("[0].email", equalTo("the@king.com"))
        .body("[0].hobbies[0].description", equalTo("Monster Hunter World"))
        .body("[0].hobbies[0].name", equalTo("MHW"))
        .body("[1].hobbies[1].name", equalTo("Origami"))
        .body("[0].hobbies[1].description", equalTo("Warframe"))
        .body("[0].address.street", equalTo("Klampenborgvej"))
        .body("[0].address.cityInfo.city", equalTo("Lyngby"))
        .body("[0].address.cityInfo.zipCode", equalTo("2800"))
        .body("[0].phones[0].number", equalTo(13371337))
        .body("[1].phones[0].description", equalTo("Cellphone"))
        .body("[1].phones[0].number", equalTo(98765432));
    }
    
    @Test
    public void testGetAllPersons()
    {
        given()
        .contentType("application/json")
        .accept("application/json")
        .get("/search/allpersons")
        .then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("[0].firstName", equalTo("Johnny"))
        .body("[1].firstName", equalTo("Dimwit"))
        .body("[2].firstName", equalTo("George"))
        .body("[0].lastName", equalTo("Ringo"))
        .body("[1].lastName", equalTo("DaBoi"))
        .body("[2].lastName", equalTo("Washington"))
        .body("[0].email", equalTo("the@king.com"))
        .body("[0].hobbies[0].description", equalTo("Monster Hunter World"))
        .body("[0].hobbies[0].name", equalTo("MHW"))
        .body("[1].hobbies[1].name", equalTo("Origami"))
        .body("[2].hobbies[0].name", equalTo("Living"))
        .body("[0].hobbies[1].description", equalTo("Warframe"))
        .body("[0].address.street", equalTo("Klampenborgvej"))
        .body("[0].address.cityInfo.city", equalTo("Lyngby"))
        .body("[0].address.cityInfo.zipCode", equalTo("2800"))
        .body("[0].phones[0].number", equalTo(13371337))
        .body("[1].phones[0].description", equalTo("Cellphone"))
        .body("[1].phones[0].number", equalTo(98765432));
    }
    
    @Test
    public void testPersonsCountByHobby()
    {
        given()
        .contentType("application/json")
        .accept("application/json")
        .get("/search/hobby/MHW/count")
        .then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("count", equalTo(2));
    }
    
    @Test
    public void testFailAddPersonWithEverything_MissingInput()
    {
        given()
        .contentType("application/json")
        .accept("application/json")
        .body(new Person())
        .post("/search/create-all")
        .then()
        .assertThat()
        .statusCode(HttpStatus.BAD_REQUEST_400.getStatusCode())
        .body("message", equalTo("Missing Input"));
    }
    
    // TODO - Update following refactor of the method. 
//    @Test
//    public void testAddPersonWithEverything()
//    {
//        given()
//        .contentType("application/json")
//        .accept("application/json")
//        .body(person4)
//        .post("/search/create-with-hobby/person")
//        .then()
//        .assertThat()
//        .statusCode(HttpStatus.OK_200.getStatusCode())
//        .body("firstName", equalTo("Iza"))
//        .body("lastName", equalTo("Evelynn"))
//        .body("email", equalTo("legendary@weare.com"))
//        .body("hobbies[0].name", equalTo("MHW"))
//        .body("hobbies[0].description", equalTo("Monster Hunter World"));
//    }
}
