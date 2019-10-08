package facades;

import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import java.util.List;

/* 
Implement a simple SPA which as a minimum must have the ability to use some 
of your GET endpoints and at least one POST endpoint. 
Consider pages like: 
 */
public interface ISearchFacade {

    public PersonDTO_OUT addPerson(PersonDTO_IN personDTO);

    public List<PersonDTO_OUT> getAllPersonDTO_OUT();

    /* Get all persons with a given hobby */
    public List<PersonDTO_OUT> getPersonDTO_OUT_ByHobby(String hobbyName);

    /* Get the count of people with a given hobby */
    public long getCountPersonByHobby(String hobbyName);

    /* Create a Person (with hobbies, phone, address etc.) */
    public PersonDTO_OUT addPersonWithHobbies(PersonDTO_IN personDTO);

    public List<PersonDTO_OUT> getPersonByFullName(String name);
    
    /* Get all persons living in a given city (i.e. 2800 Lyngby) */
    // public List<PersonDTO_OUT> getPersonsInCity(CityInfoDTO city);
    
    /* Get a list of all zip codes in Denmark */
    public List<Integer> getZipcodes();
    
    
}
