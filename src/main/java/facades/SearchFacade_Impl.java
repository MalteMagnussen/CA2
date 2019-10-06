package facades;

import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import entities.Hobby;
import entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author
 */
public class SearchFacade_Impl implements ISearchFacade {

    private static SearchFacade_Impl instance;
    private static EntityManagerFactory emf;

    private SearchFacade_Impl() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static SearchFacade_Impl getSearchFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SearchFacade_Impl();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public PersonDTO_OUT addPerson(PersonDTO_IN pDTO) {
        Person p = new Person(pDTO.getEmail(), pDTO.getFirstName(), pDTO.getLastName(), null);
        EntityManager em = getEntityManager();
        if (p.getEmail() == null || p.getFirstName() == null || p.getLastName() == null ||
            p.getEmail().trim().equals("") || p.getFirstName().trim().equals("") ||
            p.getLastName().trim().equals("")) {
            throw new WebApplicationException("Missing input", 400);
        }
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            //Needs to be refactored
            p.addHobby(new Hobby("None", ""));
            PersonDTO_OUT pOUT = new PersonDTO_OUT(p);
            return pOUT;
        } catch (Exception ex) {
            System.out.println("Failed to persist object");
            //ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<PersonDTO_OUT> getAllPersonDTO_OUT() {
        EntityManager em = getEntityManager();
        try {
            List<Person> persons = em.createNamedQuery("Person.getAll").getResultList();
            if (persons.isEmpty()) {
                throw new WebApplicationException("No persons in database", 400);
            }
            List<PersonDTO_OUT> result = new ArrayList<>();
            persons.forEach((person) -> {
                result.add(new PersonDTO_OUT(person));
            });
            return result;
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), 400);
        } finally {
            em.close();
        }
    }

    @Override
    public List<PersonDTO_OUT> getPersonDTO_OUT_ByHobby(String hobbyName) {
        EntityManager em = getEntityManager();
        try {
            List<Person> persons = em.createNamedQuery("Person.getPersonsByHobby").setParameter("name", hobbyName).getResultList();
            if (persons.isEmpty()) {
                throw new WebApplicationException("No persons with hobby in database", 400);
            }
            List<PersonDTO_OUT> result = new ArrayList<>();
            persons.forEach((person) -> {
                result.add(new PersonDTO_OUT(person));
            });
            return result;
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), 400);
        } finally {
            em.close();
        }
    }

    @Override
    public long getCountPersonByHobby(String hobbyName) {
        EntityManager em = getEntityManager();
        try {
            Long result = (long) em.createNamedQuery("Person.countPersonsByHobby").setParameter("name", hobbyName).getSingleResult();
            if (result == 0) {
                throw new WebApplicationException("No persons with hobby in database", 400);
            }
            return result;
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), 400);
        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO_OUT addPersonWithHobbies(PersonDTO_IN personDTO) {
        Person person = new Person(personDTO.getEmail(), personDTO.getFirstName(), personDTO.getLastName());
        List<Hobby> hobbies = personDTO.getHobbies();
        EntityManager em = getEntityManager();
        if (person.getEmail() == null || person.getFirstName() == null ||
            person.getLastName() == null || hobbies == null ||
            hobbies.isEmpty() || person.getEmail().trim().equals("") || 
            person.getFirstName().trim().equals("") || person.getLastName().trim().equals("")) {
            throw new WebApplicationException("Missing input", 400);
        }
        try {
            em.getTransaction().begin();

            hobbies.forEach((hobby) -> {
                Hobby mergeHobby = em.merge(hobby);
                person.addHobby(mergeHobby);
            });
            em.persist(person);
            em.getTransaction().commit();
            PersonDTO_OUT pOUT = new PersonDTO_OUT(person);
            return pOUT;
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), 400);
        } finally {
            em.close();
        }
    }

    @Override
    public List<PersonDTO_OUT> getPersonByFullName(String name) {
        EntityManager em = getEntityManager();
        String[] names = name.split(" ");
        try {
            Query query = em.createNamedQuery("Person.getPersonsByFullName");
            query.setParameter("firstName", names[0]);
            query.setParameter("lastName", names[1]);
            List<Person> results = query.getResultList();
            if (results.isEmpty()) {
                throw new WebApplicationException("No persons with that name in database", 400);
            }
            List<PersonDTO_OUT> personDTOs = new ArrayList<>();
            for (Person person : results) {
                personDTOs.add(new PersonDTO_OUT(person));
            }
            return personDTOs;
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), 400);
        } finally {
            em.close();
        }
    }

}
