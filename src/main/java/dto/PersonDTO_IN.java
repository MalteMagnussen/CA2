package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@Schema(name = "Person")
public class PersonDTO_IN {
    
    private Integer id;
    private String email, fName, lName;
    
    public PersonDTO_IN()
    {}

    public PersonDTO_IN(Integer id, String email, String fName, String lName)
    {
        this.id = id;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
    }

    public PersonDTO_IN(String email, String fName, String lName) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
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
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final PersonDTO_IN other = (PersonDTO_IN) obj;
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
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "PersonDTO_IN{" + "id=" + id + ", email=" + email + ", fName=" + fName + ", lName=" + lName + '}';
    }
    
    
}
