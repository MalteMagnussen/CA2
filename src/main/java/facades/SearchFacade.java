/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.PersonDTO_IN;
import entities.Person;
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
    
    //Private Constructor to ensure Singleton
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
    
}
