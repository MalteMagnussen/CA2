package entities;

import dto.PersonDTO_IN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "Person.getAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.getByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Person.deleteAllRows", query = "DELETE FROM Person"),
    @NamedQuery(name = "Person.getPersonByID", query = "SELECT p FROM Person p WHERE p.id = :id"),
    @NamedQuery(name = "Person.getPersonsByHobby", query = "SELECT p FROM Person p JOIN p.hobbies h WHERE h.name = :name"),
    @NamedQuery(name = "Person.getPersonsByFullName", query = "SELECT p FROM Person p WHERE p.firstName = :firstName AND p.lastName = :lastName"),
    @NamedQuery(name = "Person.countPersonsByHobby", query = "SELECT count(p) FROM Person p JOIN p.hobbies h WHERE h.name = :name"),
    @NamedQuery(name = "Person.getPersonByPhoneNumber", query = "SELECT z FROM Person z WHERE z.id = (SELECT p.person.id FROM Phone p WHERE p.number = :phone)")})

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email, firstName, lastName;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hobby_id")
    private List<Hobby> hobbies = new ArrayList();

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "person",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    
    @JoinColumn(name = "phone_id")
    private List<Phone> phones = new ArrayList();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;
    
    public Person() {
    }

    public Person(String email, String firstName, String lastName, List<Hobby> hobbies) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
        this.phones = new ArrayList();
    }

    public Person(String email, String firstName, String lastName, List<Hobby> hobbies, List<Phone> phones, Address address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
        this.phones = phones;
        this.address = address;
    }

    
    
    public Person(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = new ArrayList();
    }

    public Person(PersonDTO_IN person) {
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phones = new ArrayList();
    }
    
    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
    
    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void addHobby(Hobby hobby) {
        this.hobbies.add(hobby);
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
        final Person other = (Person) obj;
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
    public String toString() {
        return "Person{" + "id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", hobbies=" + hobbies + '}';
    }

}
