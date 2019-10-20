package entities;

import dto.HobbyDTO_IN;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Hobby.deleteAllRows", query = "DELETE FROM Hobby"),
    @NamedQuery(name = "Hobby.getAll", query = "SELECT h FROM Hobby h"),
    @NamedQuery(name = "Hobby.getHobbyByID", query = "SELECT h FROM Hobby h WHERE h.id = :id"),
    @NamedQuery(name = "Hobby.getHobby", query = "SELECT h FROM Hobby h WHERE h.name = :name AND h.description = :desc"),
    @NamedQuery(name = "Hobby.getHobbyByName", query = "SELECT h FROM Hobby h WHERE h.name = :name ")})
public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name, description;

    @ManyToMany(mappedBy = "hobbies", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id")
    private List<Person> persons = new ArrayList();

    public Hobby() {
    }

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public Hobby(HobbyDTO_IN hobby) {
        this.id = hobby.getId();
        this.name = hobby.getName();
        this.description = hobby.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person) {
        this.persons.add(person);
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
        final Hobby other = (Hobby) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.persons, other.persons)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Hobby{" + "id=" + id + ", name=" + name + ", description=" + description + ", persons=" + persons + '}';
    }

    
}
