package dto;

import entities.Hobby;
import entities.Person;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(name = "Person")
public class PersonDTO_IN {
    
    @Schema(name = "ID", required = true, example = "1")
    private Integer id;
    @Schema(name = "First Name", required = true, example = "Johnny")
    private String firstName;
    @Schema(name = "Last Name", required = true, example = "Reimar")
    private String lastName;
    @Schema(name = "Email", required = true, example = "Johnny@Reimar.dk")
    private String email;
    @Schema(example="None available")
    private List<Hobby> hobbies = new ArrayList();

    public PersonDTO_IN() {
    }

    public PersonDTO_IN(Person person) {
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.hobbies = person.getHobbies();
    }

    public PersonDTO_IN(String email, String firstName, String lastName, List<Hobby> hobbies) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
    }

    public PersonDTO_IN(String email, String firstname, String lastname) {
        this.email = email;
        this.firstName = firstname;
        this.lastName = lastname;
    }
    
    public void addHobby(Hobby h){
        this.hobbies.add(h);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonDTO_IN other = (PersonDTO_IN) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.hobbies, other.hobbies)) {
            return false;
        }
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.firstName);
        hash = 83 * hash + Objects.hashCode(this.lastName);
        hash = 83 * hash + Objects.hashCode(this.email);
        hash = 83 * hash + Objects.hashCode(this.hobbies);
        return hash;
    }
    
}
