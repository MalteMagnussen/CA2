package dto;

import entities.Hobby;
import entities.Person;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(name = "Person")
public class PersonDTO_OUT {

    @Schema(name = "ID", example = "1")
    private Integer id;
    @Schema(name = "First Name", example = "Johnny")
    private String firstName;
    @Schema(name = "Last Name", example = "Reimar")
    private String lastName;
    @Schema(name = "Email", example = "Johnny@Reimar.dk")
    private String email;
    @Schema(example = "None available")
    private List<HobbyDTO_OUT> hobbies = new ArrayList();

    public PersonDTO_OUT() {
    }

    public PersonDTO_OUT(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        for (Hobby h : person.getHobbies()) {
            this.hobbies.add(new HobbyDTO_OUT(h));
        }
    }

    public PersonDTO_OUT(PersonDTO_IN person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        for (Hobby h : person.getHobbies()) {
            this.hobbies.add(new HobbyDTO_OUT(h));
        }
    }

    public PersonDTO_OUT(String email, String firstName, String lastName, List<Hobby> hobbies) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        for (Hobby h : hobbies) {
            this.hobbies.add(new HobbyDTO_OUT(h));
        }
    }

    public void addHobby(HobbyDTO_OUT h) {
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

    public List<HobbyDTO_OUT> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<HobbyDTO_OUT> hobbies) {
        this.hobbies = hobbies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        final PersonDTO_OUT other = (PersonDTO_OUT) obj;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.firstName);
        hash = 61 * hash + Objects.hashCode(this.lastName);
        hash = 61 * hash + Objects.hashCode(this.email);
        hash = 61 * hash + Objects.hashCode(this.hobbies);
        return hash;
    }

    @Override
    public String toString() {
        return "PersonDTO_OUT{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", hobbies=" + hobbies + '}';
    }

}
