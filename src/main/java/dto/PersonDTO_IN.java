package dto;

import entities.Hobby;
import entities.Person;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Objects;

@Schema(name = "Person")
public class PersonDTO_IN {
    
    @Schema(name = "First Name", required = true, example = "Johnny")
    private String firstName;
    @Schema(name = "Last Name", required = true, example = "Reimar")
    private String lastName;
    @Schema(name = "Email", required = true, example = "Johnny@Reimar.dk")
    private String email;
    @Schema(example="None available")
    private List<Hobby> hobbies;

    public PersonDTO_IN() {
    }

    public PersonDTO_IN(Person person) {
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.hobbies = person.getHobbies();
    }

    public PersonDTO_IN(PersonDTO_OUT person) {
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
    
}
