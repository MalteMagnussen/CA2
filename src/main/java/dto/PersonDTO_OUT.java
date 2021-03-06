package dto;

import entities.Hobby;
import entities.Person;
import entities.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(name = "Person")
public class PersonDTO_OUT {

    @Schema(description = "Unique Identifier", example = "1")
    private Integer id;
    @Schema(description = "First Name", required = true, example = "Johnny")
    private String firstName;
    @Schema(description = "Last Name", required = true, example = "Reimar")
    private String lastName;
    @Schema(description = "Email", required = true, example = "Johnny@Reimar.dk")
    private String email;
    @Schema(example = "[{\"name\":\"Lacrosse\",\"description\":\"Rich people game\"}]")
    private List<HobbyDTO_OUT> hobbies = new ArrayList();
    @Schema(example = "[{\"number\":\"12345678\",\"description\":\"work\"}]")
    private List<PhoneDTO_OUT> phones = new ArrayList();
    @Schema(example = "{\"street\":\"Vejnavn\",\"additionalInfo\":\"Nr. 12, 1tv\",\"cityInfo\":{\"zipCode\":\"2800\",\"city\":\"Lyngby\"}}")
    private AddressDTO_OUT address;

    public PersonDTO_OUT() {
    }

    public PersonDTO_OUT(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        if (person.getHobbies() != null) {
            for (Hobby h : person.getHobbies()) {
                this.hobbies.add(new HobbyDTO_OUT(h));
            }
        }
        if (person.getPhones() != null) {
            for (Phone p : person.getPhones()) {
                this.phones.add(new PhoneDTO_OUT(p));
            }
        }
        if (person.getAddress() != null) {
            this.address = new AddressDTO_OUT(person.getAddress());
        }
    }

    public PersonDTO_OUT(PersonDTO_IN person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        if (person.getHobbies() != null) {
            for (HobbyDTO_IN h : person.getHobbies()) {
                this.hobbies.add(new HobbyDTO_OUT(h));
            }
        }
        if (person.getPhones() != null) {
            for (PhoneDTO_IN p : person.getPhones()) {
                this.phones.add(new PhoneDTO_OUT(p));
            }
        }
        if (person.getAddress() != null) {
            this.address = new AddressDTO_OUT(person.getAddress());
        }
    }

    public PersonDTO_OUT(String email, String firstName, String lastName, List<Hobby> hobbies) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        if (hobbies != null) {
            for (Hobby h : hobbies) {
                this.hobbies.add(new HobbyDTO_OUT(h));
            }
        }
    }

    public PersonDTO_OUT(String email, String firstName, String lastName,
            List<HobbyDTO_OUT> hobbies, List<PhoneDTO_OUT> phones, AddressDTO_OUT address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        if (hobbies != null) {
            for (HobbyDTO_OUT h : hobbies) {
                this.hobbies.add(h);
            }
        }
        if (phones != null) {
            for (PhoneDTO_OUT p : phones) {
                this.phones.add(p);
            }
        }
        if (address != null) {
            this.address = address;
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

    public List<PhoneDTO_OUT> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO_OUT> phones) {
        this.phones = phones;
    }

    public AddressDTO_OUT getAddress() {
        return address;
    }

    public void setAddress(AddressDTO_OUT address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.firstName);
        hash = 97 * hash + Objects.hashCode(this.lastName);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.hobbies);
        hash = 97 * hash + Objects.hashCode(this.phones);
        hash = 97 * hash + Objects.hashCode(this.address);
        return hash;
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
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.hobbies, other.hobbies)) {
            return false;
        }
        if (!Objects.equals(this.phones, other.phones)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonDTO_OUT{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", hobbies=" + hobbies + ", phones=" + phones + ", address=" + address + '}';
    }

}
