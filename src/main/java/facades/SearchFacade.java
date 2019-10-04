/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
    
//    public Person addPerson(PersonDTO pDTO)
//    {
//        Person p
//        EntityManager em = getEntityManager();
//        try
//        {
//            em.getTransaction().begin();
//            em.persist(p);
//            em.getTransaction().commit();
//            return p;
//        } catch (Exception ex)
//        {
//            System.out.println("Failed to persist object");
//            //ex.printStackTrace();
//            return null;
//        } finally
//        {
//            em.close();
//        }
//    }
    
}
