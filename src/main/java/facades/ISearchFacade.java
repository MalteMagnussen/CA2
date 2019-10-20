package facades;

import dto.CityInfoDTO_IN;
import dto.CityInfoDTO_OUT;
import dto.HobbyDTO_IN;
import dto.HobbyDTO_OUT;
import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import entities.Address;
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
     * Edit a Person.
     * @param personDTO - PersonDTO_IN
     * @return PersonDTO_OUT
     */
    public PersonDTO_OUT editPerson(PersonDTO_IN personDTO);
    
    /**
     * Delete a Person
     * @param id of the Person
     * @return PersonDTO_OUT
     */
    public PersonDTO_OUT deletePerson(Integer id);

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
    public PersonDTO_OUT addPersonWithEverything(PersonDTO_IN personDTO);

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
    
    /**
     * Get a person by their phone number. (i.e. 12345678)
     * @param phone phonenumber
     * @return person in question
     */
    public PersonDTO_OUT getPersonByPhone(long phone);
    
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
     * @return CityDTO with the given name.
     */
    public CityInfoDTO_OUT getCityByName(String name);

    /**
     * Get City by ZipCode
     *
     * @param zip of the city.
     * @return CityDTO with the given ZipCode.
     */
    public CityInfoDTO_OUT getCityByZipCode(String zip);

    /**
     * Create City.
     *
     * @param name of the city.
     * @param zipCode of the city.
     * @param addresses in the city. Can be empty.
     * @return CityInfoDTO.
     */
    public CityInfoDTO_OUT createCity(String name, String zipCode, List<Address> addresses);

    /**
     * Edit City.
     *
     * @param ID of the city.
     * @param name of the city.
     * @param zipCode of the city.
     * @param addresses in the city.
     * @return CityInfoDTO.
     */
    public CityInfoDTO_OUT editCity(Integer ID, String name, String zipCode, List<Address> addresses);

    /**
     * Delete City.
     * @param ID of the city.
     * @return success message.
     */
    public CityInfoDTO_OUT deleteCity(int ID);
    
    /**
     * Get hobby by name.
     * @param name of the hobby
     * @return HobbyDTO_OUT
     */
    public HobbyDTO_OUT getHobbyByName(String name);
    
    /**
     * Edit given hobby's description
     * @param hobbyDTO
     * @return HobbyDTO_OUT - the edited hobby
     */
    public HobbyDTO_OUT editHobby(HobbyDTO_IN hobbyDTO);
}
