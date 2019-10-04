package facades;

import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import entities.Hobby;
import entities.Person;
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

/**
 *
 * @author Camilla
 */

public class SearchFacadeTest {

    private static EntityManagerFactory emf;
    private static SearchFacade facade;

    private static ArrayList<Person> testPersons = new ArrayList();
    private static ArrayList<Hobby> hobbies1 = new ArrayList();
    private static ArrayList<Hobby> hobbies2 = new ArrayList();
    private static ArrayList<Hobby> hobbies3 = new ArrayList();

    public SearchFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = SearchFacade.getSearchFacade(emf);
        
        EntityManager em = emf.createEntityManager();

        hobbies1.add(new Hobby("WoW", "Det der spil"));
        hobbies1.add(new Hobby("Fisk", "Kun havfisk"));
        hobbies2.add(new Hobby("Papers, Please!", "Glory to Arstotzka!"));
        hobbies2.add(new Hobby("WoW", "Det der spil"));
        hobbies3.add(new Hobby("Frimærker", "Samlerobjekt"));

        testPersons.add(new Person("rigmor@email.dk", "Rigmor", "Noggenfogger", hobbies1));
        testPersons.add(new Person("boris@email.dk", "Boris", "Ragnaros", hobbies2));
        testPersons.add(new Person("zacharias@email.dk", "Zacharias", "Onyxia", hobbies3));
        
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
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testAddPerson() {
        ArrayList<Hobby> addHobbies = new ArrayList();
        Person exp = new Person("testADD@email.dk", "testADD", "Deathwing", addHobbies);
        PersonDTO_IN addTESTpersonDTO = new PersonDTO_IN("testADD@email.dk", "testADD", "Deathwing");
        assertEquals(exp, facade.addPerson(addTESTpersonDTO));
    }

    @Test
    public void testzGetAllPersonDTO_OUT() {
        ArrayList<PersonDTO_OUT> exp = new ArrayList();
        exp.add(new PersonDTO_OUT(new Person("rigmor@email.dk", "Rigmor", "Noggenfogger", hobbies1)));
        exp.add(new PersonDTO_OUT(new Person("boris@email.dk", "Boris", "Ragnaros", hobbies2)));
        exp.add(new PersonDTO_OUT(new Person("zacharias@email.dk", "Zacharias", "Onyxia", hobbies3)));
        System.out.println("EXP " + exp.size());
        System.out.println("RES " + facade.getAllPersonDTO_OUT().size());
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
}
