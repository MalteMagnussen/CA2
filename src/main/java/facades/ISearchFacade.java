package facades;

import dto.CityInfoDTO_IN;
import dto.CityInfoDTO_OUT;
import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import java.util.List;

/* 
Implement a simple SPA which as a minimum must have the ability to use some 
of your GET endpoints and at least one POST endpoint. 
Consider pages like: 
 */
public interface ISearchFacade {

    /**
     * Add a Person to the Database.
     *
     * @param personDTO - PersonDTO_IN
     * @return PersonDTO_OUT
     */
    public PersonDTO_OUT addPerson(PersonDTO_IN personDTO);

    /**
     * Get all Persons in the Database.
     *
     * @return List of PersonDTO_OUT
     */
    public List<PersonDTO_OUT> getAllPersonDTO_OUT();

    /**
     * Get all persons with a given hobby
     *
     * @param hobbyName - Name of the given hobby
     * @return List of PersonDTO_OUT
     */
    public List<PersonDTO_OUT> getPersonDTO_OUT_ByHobby(String hobbyName);

    /**
     * Get the count of people with a given hobby
     *
     * @param hobbyName - Name of the given hobby
     * @return long - simple long type
     */
    public long getCountPersonByHobby(String hobbyName);

    /**
     * Create a Person (with hobbies, phone, address etc.)
     *
     * @param personDTO - Containing all the info you want to persist.
     * @return PersonDTO_OUT
     */
    public PersonDTO_OUT addPersonWithHobbies(PersonDTO_IN personDTO);

    /**
     * Get a Person by their Full Name.
     *
     * @param name - Full Name of the person.
     * @return PersonDTO_OUT
     */
    public List<PersonDTO_OUT> getPersonByFullName(String name);

    /**
     * Get all persons living in a given city (i.e. 2800 Lyngby)
     *
     * @param city
     * @return List of PersonDTO_OUT
     */
    public List<PersonDTO_OUT> getPersonsInCity(CityInfoDTO_IN city);

    /**
     * Get a list of all zip codes in Denmark
     *
     * @return List of Integers.
     */
    public List<Integer> getZipcodes();

    /**
     * Get all cities.
     *
     * @return a List of Cities.
     */
    public List<CityInfoDTO_OUT> getCities();

    /**
     * Get City by Name
     *
     * @param name of the city.
     * @return List of CityDTO with the given name.
     */
    public List<CityInfoDTO_OUT> getCityByName(String name);

    /**
     * Get City by ZipCode
     *
     * @param zip of the city.
     * @return List of CityDTO with the given ZipCode.
     */
    public List<CityInfoDTO_OUT> getCityByZipCode(String zip);

}
