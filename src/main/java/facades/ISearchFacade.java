package facades;

import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import entities.Person;
import java.util.List;

/**
 *
 * @author Camilla
 */
public interface ISearchFacade {

    public Person addPerson(PersonDTO_IN personDTO);

    public List<PersonDTO_OUT> getAllPersonDTO_OUT();

    public List<PersonDTO_OUT> getPersonDTO_OUT_ByHobby(String hobbyName);

    public long getCountPersonByHobby(String hobbyName);

    public Person addPersonWithHobbies(PersonDTO_IN personDTO);
    
    public List<PersonDTO_OUT> getPersonByFullName(String name);
}
