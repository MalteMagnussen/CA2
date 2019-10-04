package dto;

import entities.Person;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@Schema(name = "Person")
public class PersonDTO_OUT {
    private Integer id;
    private String email, fName, lName;
    
    public PersonDTO_OUT()
    {}

    public PersonDTO_OUT(Integer id, String email, String fName, String lName)
    {
        this.id = id;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
    }
    
    public PersonDTO_OUT(Person person)
    {
        this.id = person.getId();
        this.email = person.getEmail();
        this.fName = person.getFirstName();
        this.lName = person.getLastName();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getfName()
    {
        return fName;
    }

    public void setfName(String fName)
    {
        this.fName = fName;
    }

    public String getlName()
    {
        return lName;
    }

    public void setlName(String lName)
    {
        this.lName = lName;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.fName);
        hash = 97 * hash + Objects.hashCode(this.lName);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final PersonDTO_OUT other = (PersonDTO_OUT) obj;
        if (!Objects.equals(this.email, other.email))
        {
            return false;
        }
        if (!Objects.equals(this.fName, other.fName))
        {
            return false;
        }
        if (!Objects.equals(this.lName, other.lName))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "PersonDTO_OUT{" + "id=" + id + ", email=" + email + ", fName=" + fName + ", lName=" + lName + '}';
    }
}
