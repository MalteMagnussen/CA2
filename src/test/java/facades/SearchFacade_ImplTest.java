package facades;

import dto.CityInfoDTO_IN;
import dto.CityInfoDTO_OUT;
import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import utils.EMF_Creator;

public class SearchFacade_ImplTest {

    private static EntityManagerFactory emf;
    private static SearchFacade_Impl facade;

    private static ArrayList<Person> testPersons = new ArrayList();
    private static ArrayList<Hobby> hobbies1 = new ArrayList();
    private static ArrayList<Hobby> hobbies2 = new ArrayList();
    private static ArrayList<Hobby> hobbies3 = new ArrayList();
    private static ArrayList<Hobby> hobbies4 = new ArrayList();

    private static ArrayList<Phone> phones1 = new ArrayList();
    private static ArrayList<Phone> phones2 = new ArrayList();
    private static ArrayList<Phone> phones3 = new ArrayList();
    private static ArrayList<Phone> phones4 = new ArrayList();

    private static Person person1 = new Person("rigmor@email.dk", "Rigmor", "Noggenfogger", hobbies1);
    private static Person person2 = new Person("boris@email.dk", "Boris", "Ragnaros", hobbies2);
    private static Person person3 = new Person("zacharias@email.dk", "Zacharias", "Onyxia", hobbies3);
    private static Person person4 = new Person("flemse@email.dk", "Flemming", "Atramedes", hobbies4);

    private static CityInfo city1 = new CityInfo("3400", "Hillerød");
    private static CityInfo city2 = new CityInfo("4000", "Roskilde");
    private static Address address1 = new Address("Kings Road 1", "By the Inn");
    private static Address address2 = new Address("Fishroad 7", "Next to the Lake");
    private static Address address3 = new Address("Fleabottom 69", "In the shitpit");

    public SearchFacade_ImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = SearchFacade_Impl.getSearchFacade(emf);

        hobbies1.add(new Hobby("WoW", "Det der spil"));
        hobbies1.add(new Hobby("Fisk", "Kun havfisk"));
        hobbies2.add(new Hobby("Papers, Please!", "Glory to Arstotzka!"));
        hobbies2.add(new Hobby("WoW", "Det der spil"));
        hobbies3.add(new Hobby("Frimærker", "Samlerobjekt"));
        hobbies4.add(new Hobby("Sightseeing", "Samlerobjekt"));

        phones1.add(new Phone(123456, "Home", person1));
        phones1.add(new Phone(987456, "Mobile", person1));
        phones2.add(new Phone(654789, "Home", person2));
        phones2.add(new Phone(365478, "Work", person2));
        phones3.add(new Phone(456789, "Mobile", person3));
        phones4.add(new Phone(333333, "Work", person4));

        person1.addPhone(phones1.get(0));
        person1.addPhone(phones1.get(1));
        person2.addPhone(phones2.get(0));
        person2.addPhone(phones2.get(1));
        person3.addPhone(phones3.get(0));
        person4.addPhone(phones4.get(0));

        address1.setCityinfo(city1);
        address2.setCityinfo(city2);
        address3.setCityinfo(city2);

        person1.setAddress(address1);
        person2.setAddress(address2);
        person3.setAddress(address3);
        person4.setAddress(address1);

        testPersons.add(person1);
        testPersons.add(person2);
        testPersons.add(person3);
        testPersons.add(person4);
    }

    @AfterEach
    public void tearDown() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNamedQuery("CityInfo.deleteAllRows").executeUpdate();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
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

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testAddPerson() {
        Person preExp = new Person("testADD@email.dk", "testADD", "Deathwing");
        PersonDTO_OUT exp = new PersonDTO_OUT(preExp);
        PersonDTO_IN addTESTpersonDTO = new PersonDTO_IN("testADD@email.dk", "testADD", "Deathwing");
        assertEquals(exp, facade.addPerson(addTESTpersonDTO));
    }

    @Test
    public void testGetAllPersonDTO_OUT() {
        ArrayList<PersonDTO_OUT> exp = new ArrayList();
        exp.add(new PersonDTO_OUT(new Person("rigmor@email.dk", "Rigmor", "Noggenfogger", hobbies1)));
        exp.add(new PersonDTO_OUT(new Person("boris@email.dk", "Boris", "Ragnaros", hobbies2)));
        exp.add(new PersonDTO_OUT(new Person("zacharias@email.dk", "Zacharias", "Onyxia", hobbies3)));
        exp.add(new PersonDTO_OUT(new Person("flemse@email.dk", "Flemming", "Atramedes", hobbies4)));
        assertEquals(exp, facade.getAllPersonDTO_OUT());
    }

    @Test
    public void testGetPersonDTO_OUT_ByHobby() throws Exception {
        ArrayList<PersonDTO_OUT> exp = new ArrayList();
        exp.add(new PersonDTO_OUT(new Person("zacharias@email.dk", "Zacharias", "Onyxia", hobbies3)));
        assertEquals(exp, facade.getPersonDTO_OUT_ByHobby("Frimærker"));
    }

    @Test
    public void testGetPersonDTO_OUT_ByHobby_FAIL() throws Exception {
        Assertions.assertThrows(WebApplicationException.class, () -> {
            facade.getPersonDTO_OUT_ByHobby("HAT");
        });
    }

    @Test
    public void testGetCountPersonByHobby() {
        assertEquals(2L, facade.getCountPersonByHobby("WoW"));
    }

    @Test
    public void testGetCountPersonByHobby_FAIL() throws Exception {
        Assertions.assertThrows(WebApplicationException.class, () -> {
            facade.getCountPersonByHobby("HAT");
        });
    }

    @Test
    public void testAddPersonWithHobbies() {
        ArrayList<Hobby> addHobbies = new ArrayList();
        addHobbies.add(new Hobby("Testhobby", "hobbytest"));
        Person preExp = new Person("testADDwithhobby@email.dk", "testADDwithhobby", "Deathwingwithhobby", addHobbies);
        PersonDTO_OUT exp = new PersonDTO_OUT(preExp);
        PersonDTO_IN addTESTpersonDTO = new PersonDTO_IN("testADDwithhobby@email.dk", "testADDwithhobby", "Deathwingwithhobby", addHobbies);
        assertEquals(exp, facade.addPersonWithHobbies(addTESTpersonDTO));
    }

    @Test
    public void testGetPersonByFullName() {
        PersonDTO_OUT expResult = new PersonDTO_OUT(new Person("rigmor@email.dk", "Rigmor", "Noggenfogger", hobbies1));
        List<PersonDTO_OUT> result = facade.getPersonByFullName("Rigmor Noggenfogger");
        assertEquals(expResult, result.get(0));
    }

    @Test
    public void testGetZipcodes() {
        List<String> exp = new ArrayList();
        exp.add("3400");
        exp.add("4000");
        assertEquals(exp, facade.getZipcodes());
    }

    @Test
    public void testGetPersonsInCity() {
        List<PersonDTO_OUT> exp = new ArrayList();
        exp.add(new PersonDTO_OUT(new Person("rigmor@email.dk", "Rigmor", "Noggenfogger", hobbies1)));
        exp.add(new PersonDTO_OUT(new Person("flemse@email.dk", "Flemming", "Atramedes", hobbies4)));
        List<PersonDTO_OUT> result = facade.getPersonsInCity(new CityInfoDTO_IN(city1));
        assertEquals(exp, result);
    }

    @Test
    public void testGetCities() {
        List<CityInfoDTO_OUT> exp = new ArrayList();
        exp.add(new CityInfoDTO_OUT(city1));
        exp.add(new CityInfoDTO_OUT(city2));
        List<CityInfoDTO_OUT> result = facade.getCities();
        assertEquals(exp, result);
    }

    @Test
    public void testGetCityByName() {
        CityInfoDTO_OUT exp = new CityInfoDTO_OUT(city1);
        CityInfoDTO_OUT result = facade.getCityByName("Hillerød");
        assertEquals(exp, result);
    }

    @Test
    public void testGetCityByZipCode() {

    }

    @Test
    public void testCreateCity() {

    }

    @Test
    public void testEditCity() {

    }

    @Test
    public void testDeleteCity() {

    }
}
