package facades;

import dto.PersonDTO_OUT;
import entities.Hobby;
import entities.Person;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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

    //@Test
    public void testAddPerson() {
        
    }

    @Test
    public void testGetAllPersonDTO_OUT() {
        ArrayList<PersonDTO_OUT> exp = new ArrayList();
        exp.add(new PersonDTO_OUT(new Person("rigmor@email.dk", "Rigmor", "Noggenfogger", hobbies1)));
        exp.add(new PersonDTO_OUT(new Person("boris@email.dk", "Boris", "Ragnaros", hobbies2)));
        exp.add(new PersonDTO_OUT(new Person("zacharias@email.dk", "Zacharias", "Onyxia", hobbies3)));
        assertEquals(exp, facade.getAllPersonDTO_OUT());
    }

//    @Test
    public void testGetPersonDTO_OUT_ByLastname() throws Exception {
        
    }

}