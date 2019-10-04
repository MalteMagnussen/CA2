/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import entities.Person;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Camilla
 */
public class SearchFacadeTest {
    
    public SearchFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetSearchFacade() {
        System.out.println("getSearchFacade");
        EntityManagerFactory _emf = null;
        SearchFacade expResult = null;
        SearchFacade result = SearchFacade.getSearchFacade(_emf);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddPerson() {
        System.out.println("addPerson");
        PersonDTO_IN pDTO = null;
        SearchFacade instance = null;
        Person expResult = null;
        Person result = instance.addPerson(pDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllPersonDTO_OUT() {
        System.out.println("getAllPersonDTO_OUT");
        SearchFacade instance = null;
        List<PersonDTO_OUT> expResult = null;
        List<PersonDTO_OUT> result = instance.getAllPersonDTO_OUT();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPersonDTO_OUT_ByLastname() throws Exception {
        System.out.println("getPersonDTO_OUT_ByLastname");
        String lastname = "";
        SearchFacade instance = null;
        List<PersonDTO_OUT> expResult = null;
        List<PersonDTO_OUT> result = instance.getPersonDTO_OUT_ByLastname(lastname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
