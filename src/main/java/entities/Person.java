/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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

/**
 *
 * @author
 */
@Entity
public class Person implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email, firstName, lastName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hobby_id")
    private List<Hobby> hobbies;

    public Person()
    {}
    
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

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public List<Hobby> getHobbies()
    {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies)
    {
        this.hobbies = hobbies;
    }

    public void addHobby(Hobby hobby)
    {
        this.hobbies.add(hobby);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.firstName);
        hash = 59 * hash + Objects.hashCode(this.lastName);
        hash = 59 * hash + Objects.hashCode(this.hobbies);
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
        final Person other = (Person) obj;
        if (!Objects.equals(this.email, other.email))
        {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName))
        {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName))
        {
            return false;
        }
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (!Objects.equals(this.hobbies, other.hobbies))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Person{" + "id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", hobbies=" + hobbies + '}';
    }

    
    
}