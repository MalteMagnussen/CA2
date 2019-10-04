package facades;

import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author
 */
public class SearchFacade
{
    private static SearchFacade instance;
    private static EntityManagerFactory emf;
    
    private SearchFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static SearchFacade getSearchFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SearchFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Person addPerson(PersonDTO_IN pDTO)
    {
        Person p = new Person(pDTO.getEmail(), pDTO.getfName(), pDTO.getlName(), null);
        EntityManager em = getEntityManager();
        if (p.getId() == null || p.getEmail() == null ||
                p.getFirstName() == null || p.getLastName() == null)
        {
            throw new WebApplicationException("Missing input", 400);
        }
        try
        {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } catch (Exception ex)
        {
            System.out.println("Failed to persist object");
            //ex.printStackTrace();
            return null;
        } finally
        {
            em.close();
        }
    }
    
    public List<PersonDTO_OUT> getAllPersonDTO_OUT() {
        EntityManager em = getEntityManager();
        try {
            List<Person> persons = em.createNamedQuery("Person.getAll").getResultList();
            List<PersonDTO_OUT> result = new ArrayList<>();
            persons.forEach((person) -> {
                result.add(new PersonDTO_OUT(person));
            });
            return result;
            } catch (Exception ex) {
            throw new WebApplicationException("No persons in database", 400);
        } finally {
            em.close();
        }
    }
    
    public List<PersonDTO_OUT> getPersonDTO_OUT_ByLastname(String lastname) throws Exception {
        EntityManager em = getEntityManager();
        try {
            List<Person> persons = em.createNamedQuery("Person.getByLastName").setParameter("lastName", lastname).getResultList();
            List<PersonDTO_OUT> result = new ArrayList<>();
            persons.forEach((person) -> {
                result.add(new PersonDTO_OUT(person));
            });
            return result;
        } catch (Exception ex) {
            throw new WebApplicationException("No persons in database", 400);
        } finally {
            em.close();
        }
    }
    
}