/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

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
    
    
}
