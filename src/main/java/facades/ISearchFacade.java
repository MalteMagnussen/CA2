package facades;

import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import java.util.List;

public interface ISearchFacade {

    public PersonDTO_OUT addPerson(PersonDTO_IN personDTO);

    public List<PersonDTO_OUT> getAllPersonDTO_OUT();

    public List<PersonDTO_OUT> getPersonDTO_OUT_ByHobby(String hobbyName);

    public long getCountPersonByHobby(String hobbyName);

    public PersonDTO_OUT addPersonWithHobbies(PersonDTO_IN personDTO);

    public List<PersonDTO_OUT> getPersonByFullName(String name);
}
