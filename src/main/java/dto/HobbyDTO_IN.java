package dto;

import entities.Hobby;
import entities.Person;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(name = "Hobby")
public class HobbyDTO_IN {
    
    @Schema(required = true,example = "Lacrosse")
    private String name;
    @Schema(required = true,example = "Rich People Game")
    private String description;
    @Schema(example="None available")
    private List<Person> persons  = new ArrayList();

    public HobbyDTO_IN() {
    }

    public HobbyDTO_IN(Hobby hobby) {
        this.name = hobby.getName();
        this.description = hobby.getDescription();
        this.persons = hobby.getPersons();
    }

    public HobbyDTO_IN(String name, String description, List<Person> persons) {
        this.name = name;
        this.description = description;
        this.persons = persons;
    }
    
    public void addPerson(Person p){
        this.persons.add(p);
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

    @Override
    public int hashCode() {
        int hash = 7;
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
        final HobbyDTO_IN other = (HobbyDTO_IN) obj;
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
    
}
