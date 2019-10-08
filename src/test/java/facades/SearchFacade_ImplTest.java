package facades;

import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import org.junit.jupiter.api.AfterAll;
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
    
    private static ArrayList<Phone> phones1 = new ArrayList();
    private static ArrayList<Phone> phones2 = new ArrayList();
    private static ArrayList<Phone> phones3 = new ArrayList();
    
    private static Person person1 = new Person("rigmor@email.dk", "Rigmor", "Noggenfogger", hobbies1);
    private static Person person2 = new Person("boris@email.dk", "Boris", "Ragnaros", hobbies2);
    private static Person person3 = new Person("zacharias@email.dk", "Zacharias", "Onyxia", hobbies3);
    
    public SearchFacade_ImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = SearchFacade_Impl.getSearchFacade(emf);

        // String email, String firstName, String lastName, List<Hobby> hobbies, List<Phone> phones, Address address)
        
        
        hobbies1.add(new Hobby("WoW", "Det der spil"));
        hobbies1.add(new Hobby("Fisk", "Kun havfisk"));
        hobbies2.add(new Hobby("Papers, Please!", "Glory to Arstotzka!"));
        hobbies2.add(new Hobby("WoW", "Det der spil"));
        hobbies3.add(new Hobby("Frimærker", "Samlerobjekt"));
        
        phones1.add(new Phone(123456, "Home", person1));
        phones1.add(new Phone(987456, "Mobile", person1));
        phones2.add(new Phone(654789, "Home", person2));
        phones2.add(new Phone(365478, "Work", person2));
        phones3.add(new Phone(456789, "Mobile", person3));
        
        person1.addPhone(phones1.get(0));
        person1.addPhone(phones1.get(1));
        person2.addPhone(phones2.get(0));
        person2.addPhone(phones2.get(1));
        person3.addPhone(phones3.get(0));

        testPersons.add(person1);
        testPersons.add(person2);
        testPersons.add(person3);
    }

//    @AfterEach
//    public void tearDown() throws Exception {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
//            em.getTransaction().commit();
//
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
//    }

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
     
    }
    
    @Test
    public void testGetPersonsInCity() {
        
    }
	
	@Test
    public void testGetCities() {
        
    }
	
	@Test
    public void testGetCityByName() {
        
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